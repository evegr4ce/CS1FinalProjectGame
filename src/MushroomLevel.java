import java.awt.*;

import javax.swing.*;

public class MushroomLevel extends Game {

	public MushroomLevel() {
		super();
	}

	@Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("islandscreen.jpg");
	}
}
