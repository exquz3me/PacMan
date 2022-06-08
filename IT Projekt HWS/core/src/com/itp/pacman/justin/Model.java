package com.itp.pacman.justin;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Model extends JPanel implements ActionListener {

	private Dimension d;
    private boolean inGame = false;

    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 16;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int PACMAN_SPEED = 6;
    private int[] dx, dy;

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy;

    private final short levelData[] = {										//0=blau, 1=links, 2=oben, 4=rechts, 8=unten, 16=punkt, 32=schwarz,
    	19, 26, 26, 18, 26, 26, 26, 22,  0, 19, 26, 26, 26, 18, 22,	19,		//64=links schwarz, 128=oben schwarz, 256=rechts schwarz, 512=oben weiß
    	21,  0,  0, 21,  0,  0,  0, 21,  0, 21,  0,  0,  0, 17, 20, 19,
        17, 26, 26, 16, 26, 18, 26, 24, 26, 24, 26, 18, 26, 16, 20, 19,
        21,  0,  0, 21,  0, 21,  0,  0,  0,  0,  0, 21,  0, 17, 20, 19,
        25, 26, 26, 20,  0, 25, 26, 22,  0, 19, 26, 28,  0, 17, 20, 19,
         0,  0,  0, 21,  0,  0,  0, 21,  0, 21,  0,  0,  0, 17, 20, 19,
        32, 32,  0, 21,  0, 19, 26, 24, 26, 24, 26, 22,  0, 17, 20, 19,
         0,  0,  0, 21,  0, 21, 19, 18, 37, 18, 22, 21,  0, 17, 20, 19,
        27, 26, 26, 16, 26, 20, 17, 16, 45, 16, 20, 17, 26, 16, 20, 19,
         0,  0,  0, 21,  0, 21, 25, 24, 26, 24, 28, 21,  0, 17, 20, 19,
        32, 32,  0, 21,  0, 25, 26, 18, 26, 18, 26, 28,  0, 17, 20, 19,
         0,  0,  0, 21,  0,  0,  0, 21,  0, 21,  0,  0,  0, 17, 20, 19,
        19, 26, 26, 20,  0, 19, 26, 28,  0, 25, 26, 22,  0, 17, 20, 19,
        21,  0,  0, 21,  0, 21,  0,  0,  0,  0,  0, 21,  0, 17, 20, 19,
        25, 26, 26, 24, 26, 24, 26, 16, 26, 26, 26, 24, 26, 24, 28, 19,
        19, 26, 26, 18, 26, 26, 26, 22,  0, 19, 26, 26, 26, 18, 22, 19
        };
  
    private short[] screenData;
    private Timer timer;

    public Model() {
        initVariables();		
        addKeyListener(new TAdapter());
        setFocusable(true);		
    }

       private void initVariables() {

        screenData = new short[N_BLOCKS * N_BLOCKS];
        d = new Dimension(400, 400);
        dx = new int[4];
        dy = new int[4];
    }
       
    private void movePacman() {

        int pos; //screen relative position
        short ch; //tile in array

        	//if the x position is divible by the block size -> 
        	//AND if the the y position is divisible by the block size -> 
        	// => if the player is in the middle of the block
        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE); 
            //get the x position in the array + 16 * y position / 16
            ch = screenData[pos]; //returns the tile based on screen pos
            
 
            if ((ch & 16) != 0) {						//00010000 & 00010000	->  00010000    00001000 & 00010000 = 0
            	/*	if ch has a shared bit with 16
            	 00010000 & 00010000	16
            	 00010001 & 00010000	17
            	 00010010 & 00010000	18
            	 00010011 & 00010000	19
            	 00010100 & 00010000	20
            	 00010101 & 00010000	21
            	 00010110 & 00010000	22
            	 00010111 & 00010000    23
            	 00011000 & 00010000	24
            	 00011001 & 00010000	25
            	 00011010 & 00010000	26
            	 00011011 & 00010000	27
            	 00011100 & 00010000	28
            	 00011101 & 00010000	29
            	 00011110 & 00010000	30
            	 00011111 & 00010000	31
            	 00100000 & 00010000	32     !!!
            	 ...
            	 00111111 & 00010000	-> 64  !!!
            	 01111111 & 00010000	-> 128 !!!
            	 11111111 & 00010000	-> 0   !!!
            	*/
                screenData[pos] = (short) (ch & 15);	// 00010000 & 00001111	-> 00000000		first 4 bits	-> 16 possible numbers
                										// 00010000 & 00001111	-> 00000000
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }
            }

            // Check for standstill
            
            
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        } 
        
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
    }

    private void drawMaze(Graphics2D g2d) {					//elemente werden gezeichnet

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(new Color(0,72,251));									//blau
                g2d.setStroke(new BasicStroke(5));
                
                if ((levelData[i] == 0)) { 
                	g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                 }

                //if screenData shares a bit with 1	-> 00000001
                if ((screenData[i] & 1) != 0) { 									//1=links, 2=oben, 4=rechts, 8=unten
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);						//64=links schwarz, 128=oben schwarz, 256=rechts schwarz
                }

                if ((screenData[i] & 2) != 0) { 
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) { 
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) { 
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) { 
                    g2d.setColor(new Color(255,255,255));						
                    g2d.fillOval(x + 10, y + 10, 6, 6);
               }
                
                if ((screenData[i] & 32) != 0) { 
                    g2d.setColor(new Color(255,255,255));
               } 
                
                if ((screenData[i] & 64) != 0) { 
                	g2d.setColor(Color.black);										//schwarz
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }
                
                if ((screenData[i] & 128) != 0) { 
                	g2d.setColor(Color.black);
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }
                
                if ((screenData[i] & 256) != 0) { 
                	g2d.setColor(Color.black);
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }
                
                if ((screenData[i] & 512) != 0) { 
                	g2d.setColor(Color.white);
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                i++;
            }
        }
    }

    //steuerrung
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inGame = false;
                } 
            } else {
                if (key == KeyEvent.VK_SPACE) {
                    inGame = true;
                }
            }
        }
    }
}
