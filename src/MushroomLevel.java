import java.awt.*;

import javax.swing.*;

public class MushroomLevel extends Game {

	public MushroomLevel() {
		super();
	}

	@Override
	public void setBackground() {
		BackgroundPanel backgroundPanel = new BackgroundPanel("islandscreen.jpg");
	}
}
