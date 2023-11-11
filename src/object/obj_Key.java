package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class obj_Key extends S_Object {
	
	public obj_Key() {
		name = "Key";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
