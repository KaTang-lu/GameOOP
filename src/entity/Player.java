package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.InputKey;
import main.UI;

public class Player extends Entity{
	
	GamePanel gp;
	InputKey key;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	public Player(GamePanel gp, InputKey key) {
		this.gp = gp;
		this.key = key;
		
		screenX = gp.screenWidth/2;
		screenY = gp.screenHeight/2;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 5;
		worldY = gp.tileSize * 5;
		speed = 4;
		direction = "right";
	}
	
	public void getPlayerImage() {
		
		try {
			left = ImageIO.read(getClass().getResourceAsStream("/player/snake_left.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/player/snake_right.png"));
			up = ImageIO.read(getClass().getResourceAsStream("/player/snake_left.png"));
			down = ImageIO.read(getClass().getResourceAsStream("/player/snake_right.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(key.upPressed == true) {
			direction = "up";
		}
		else if(key.downPressed == true) {
			direction = "down";
		}
		else if(key.leftPressed == true) {
			direction = "left";
		}
		else if(key.rightPressed == true) {
			direction = "right";
		}

		if(!key.upPressed && !key.downPressed && !key.leftPressed && !key.rightPressed) {
			direction = "stand";
		}
		
		// check tile collision
		collisionOn = false;
		gp.cCheck.checktile(this);
		
		// check obj collision
		int objIndex = gp.cCheck.checkObject(this, true);
		pickUp(objIndex);
		
		// if collision false, can move
		if(collisionOn == false) {
			switch(direction) {
			case "up": worldY = worldY - speed;
				break;
			case "down": worldY = worldY + speed;
				break;
			case "left": worldX = worldX - speed;
				break;
			case "right": worldX = worldX + speed;
				break;
			}
		}
	}
	
	public void pickUp(int i) {
		if(i != 999) {
			String objectname = gp.obj[i].name;
			
			switch(objectname) {
			case "Key":
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You find a key!!");
				break;
			case "Door":
				if(hasKey > 3) {
					gp.obj[i] = null;
					hasKey--;
					gp.ui.gameFinish = true;
				}	
				else {
					gp.ui.showMessage("You need a key");
				}
				break;
			}
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "left":
			image = left;
			break;
		case "right":
			image = right;
			break;
		case "up":
			image = up;
			break;
		case "down":
			image = down;
			break;
		}
		
		if(direction == "stand") image = right;
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
