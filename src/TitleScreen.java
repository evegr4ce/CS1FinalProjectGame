
public class TitleScreen extends Game {
	
	public TitleScreen() {
		super();
	}

	@Override
	public void setBackground() {
		BackgroundPanel backgroundPanel = new BackgroundPanel("titlescreen.jpg");
        setContentPane(backgroundPanel);
	}
}
