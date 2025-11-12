import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MushroomLevelBossFight extends Game {

	private static Game currentLevel;
	
	public MushroomLevelBossFight() {
		super();
		currentLevel = this;
	}
	
	public static Game getCurrentLevel() {
        return currentLevel;
    }
	
	@Override
	public BackgroundPanel setBackground() {
		return new BackgroundPanel("game_background_2.png");

	 }
}
