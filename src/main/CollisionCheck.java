package main;

import entity.Entity;

public class CollisionCheck {
	
	GamePanel gp;
	
	public CollisionCheck (GamePanel gp) {
		this.gp = gp;
	}
	
	public void checktile(Entity entity) {
		
		int entityLeft = entity.worldX + entity.solidArea.x;
		int entityRight = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTop = entity.worldY + entity.solidArea.y;
		int entityBottom = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeft/gp.tileSize;
		int entityRightCol = entityRight/gp.tileSize;
		int entityTopRow = entityTop/gp.tileSize;
		int entityBottomRow = entityBottom/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTop - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileH.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileH.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileH.tile[tileNum1].collision == true || gp.tileH.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottom + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileH.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileH.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileH.tile[tileNum1].collision == true || gp.tileH.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeft - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileH.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileH.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileH.tile[tileNum1].collision == true || gp.tileH.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRight + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileH.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileH.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileH.tile[tileNum1].collision == true || gp.tileH.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for(int i=0; i<gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				
				//Get entity position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				//Get the obj
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "up": 
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
//						System.out.println("up collision");
					}
					break;
				case "down": 
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
//						System.out.println("down collision");
					}
					break;
				case "left": 
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
//						System.out.println("left collision");
					}
					break;
				case "right": 
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
//						System.out.println("right collision");
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
}
