import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MushroomLevelBossFight extends Game {

	public MushroomLevelBossFight() {
		super();
	}
	
	@Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("game_background_2.png");

	 }
}
