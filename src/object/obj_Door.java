package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class obj_Door extends S_Object{

	public obj_Door() {
		name = "Door";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
