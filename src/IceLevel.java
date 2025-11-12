import java.awt.*;

import javax.swing.*;

public class IceLevel extends Game {

	public IceLevel() {
		super();
		
		Obstacle maze = new Obstacle(21, 15, "grass.png", "wall.png", this);
		maze.setBounds(60, 50, maze.getPreferredSize().width, maze.getPreferredSize().height);
		
		add(maze);
	}

	@Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("game_background_2.png");
	}
	
	
}
