import java.awt.Dimension;
import java.awt.event.KeyEvent;

import view.Canvas;

/**
 * 
 * @author Leonard
 *
 */

public class Assembly {
	private static final int NEW_ASSEMBLY = KeyEvent.VK_N;
	private static Canvas myView;
	
	public Assembly(Canvas canvas){
		myView = canvas;
	}
	public void update (double elapsedTime, Dimension bounds){
		int key = myView.getLastKeyPressed();
		if(key == NEW_ASSEMBLY){
			//create a new assembly
			myView.loadModel();
		}
	}
}
