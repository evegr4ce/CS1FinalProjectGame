import java.awt.*;

import javax.swing.*;

public class IceLevel extends Game {

	public IceLevel() {
		super();
		
		Obstacle mazePanel = new Obstacle(21, 15);
		mazePanel.setVisible(true);
		add(mazePanel);
	}

	@Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("game_background_1.png");
	}
}
