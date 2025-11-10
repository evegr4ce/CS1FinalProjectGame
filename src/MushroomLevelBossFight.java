import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MushroomLevelBossFight extends Game{

	public MushroomLevelBossFight() {
		super();
	}
	
	@Override
	public void setBackground() {
		BackgroundPanel backgroundPanel = new BackgroundPanel("game_background_2.png");
        backgroundPanel.setLayout(null); 
		setContentPane(backgroundPanel);
	}
	
	 public static void main(String[] args) {
		 SwingUtilities.invokeLater(() -> {
		        MushroomLevelBossFight mushroomLevelBossFight = new MushroomLevelBossFight();
		        mushroomLevelBossFight.setTitle("Boss fight Test");
		        mushroomLevelBossFight.setSize(800, 600);
		        mushroomLevelBossFight.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        mushroomLevelBossFight.setLayout(null);
		        mushroomLevelBossFight.setVisible(true);

		        mushroomLevelBossFight.setBackground();
		    });

	 }
}
