package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Tile.TileHandler;
import entity.Player;
import object.S_Object;

public class GamePanel extends JPanel implements Runnable {
	
	final int originalTileSize = 16; // 16x16
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixel
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixel
	
	//World
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 40;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	int FPS = 60;
	
	TileHandler tileH = new TileHandler(this);
	InputKey keyI = new InputKey();
	Thread gameThread;
	public UI ui = new UI(this);
	public CollisionCheck cCheck = new CollisionCheck(this);
	public Asset aSetter = new Asset(this);
	public Player player = new Player(this, keyI);
	public S_Object obj[] = new S_Object[10];
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyI);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	// Game Loop
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
//				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
	
	public void update() {
		player.update();
		
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;

	    //tile
	    tileH.draw(g2);
	    
	    // obj
	    for(int i=0; i<obj.length; i++) {
	    	if(obj[i] != null) {
	    		obj[i].draw(g2, this);
	    	}
	    }
	    
	    // player
	    player.draw(g2);
	    
	    //UI
	    ui.draw(g2);

	    g2.dispose();
	}
}
