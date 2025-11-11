import java.awt.*;

import javax.swing.*;

public class IceLevel extends Game {

	public IceLevel() {
		super();
	}

	@Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("game_background_1.png");
	}
}
