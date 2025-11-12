import javax.swing.JFrame;

public class GameRunner {
	public GameRunner() {
		
	}

	public static void main(String[] args) {
		
		Hero h = null;
		
		TitleScreen game = new TitleScreen();
		game.run();
		
		switch(game.getCharacter()) {
			case 1:
				h = new Hero("hero1.png", 100, 300, 5, "myHero");
				break;
			case 2:
				h = new Hero("hero2.png", 100, 300, 7, "myHero");
				break;
			case 3:
				h = new Hero("hero3.png", 100, 300, 3, "myHero");
				break;
		}
		
		IslandScreen island = new IslandScreen();
		island.run();
		
		switch(island.getChosenIsland()) {
			case 1:
				MushroomLevelBossFight level1 = new MushroomLevelBossFight();
				
				Enemy mushroomBoss = new Enemy("mushroomBoss.png", 200, 100, 10, 50);
				mushroomBoss.activate();
				
				level1.addHero(h);
				level1.addEnemy(mushroomBoss);
				level1.run();
				
				break;
			case 2:
				CrystalLevelBossFight level2 = new CrystalLevelBossFight();
				
				Enemy crystalBoss = new Enemy("crystalBoss.png", 200, 100, 10, 50);
				crystalBoss.activate();
				
				level2.addHero(h);
				level2.addEnemy(crystalBoss);
				
				level2.run();
				
				break;
			case 3:
				IceLevelBossFight level3 = new IceLevelBossFight();
				
				Enemy iceBoss = new Enemy("iceBoss.png", 200, 100, 10, 50);
				iceBoss.activate();
				
				level3.addHero(h);
				level3.addEnemy(iceBoss);
				
				level3.run();
				
				break;
			default:
				System.exit(0);
		}
	}
}