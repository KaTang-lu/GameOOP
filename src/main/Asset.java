package main;

import object.obj_Door;
import object.obj_Key;

public class Asset {
	GamePanel gp;
	
	public Asset(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		// x & Y of key
		gp.obj[0] = new obj_Key();
		gp.obj[0].worldX = 44 * gp.tileSize;
		gp.obj[0].worldY = 24 * gp.tileSize;
		
		gp.obj[1] = new obj_Key();
		gp.obj[1].worldX = 4 * gp.tileSize;
		gp.obj[1].worldY = 38 * gp.tileSize;
		
		gp.obj[2] = new obj_Key();
		gp.obj[2].worldX = 42 * gp.tileSize;
		gp.obj[2].worldY = 3 * gp.tileSize;
		
		gp.obj[3] = new obj_Key();
		gp.obj[3].worldX = 7 * gp.tileSize;
		gp.obj[3].worldY = 20 * gp.tileSize;
		
		// Door
		gp.obj[4] = new obj_Door();
		gp.obj[4].worldX = 18 * gp.tileSize;
		gp.obj[4].worldY = 22 * gp.tileSize;
		
	}
}
