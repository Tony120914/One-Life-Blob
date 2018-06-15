package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game_objects.Player;
import images.BufferedImageLoader;
import images.SpriteSheet;
import main.Main;

public class HUD {
	private BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage heartFull;
	private BufferedImage heartEmpty;
	private BufferedImage zero;
	private BufferedImage one;
	
	private BufferedImage[] heartAnimation = new BufferedImage[1*50];
	private BufferedImage[] numberAnimation = new BufferedImage[1*50];
	private BufferedImage[] deathAnimation = new BufferedImage[12*2];
	
	private int tickCounter_heart = 0;
	private int tickCounter_death = 0;
	long currentTimeMillis = System.currentTimeMillis();
	
	public HUD () {
		constructHeartFullToEmptyToFull();
		constructDeath();
	}
	
	public void tick(){
		while (true) {
			tickCounter_heart++;
			if (Player.deathSignal) {
				tickCounter_death++;
			}
			
			if (tickCounter_heart == Math.min(heartAnimation.length, numberAnimation.length)) {
				tickCounter_heart = 0;
			}
			if (tickCounter_death == deathAnimation.length) {
				tickCounter_death = deathAnimation.length - 1;
			}
			break;
		}
	}
	
	public void render(Graphics g) {

		if (Player.deathSignal) {
			g.drawImage(deathAnimation[tickCounter_death], 0, 0, null);
			
			g.drawImage(heartAnimation[tickCounter_heart], 10, 10, null);
			g.drawImage(numberAnimation[tickCounter_heart], 22, 12, null);
			
			if (currentTimeMillis + 1000 <= System.currentTimeMillis()) {
				Player.deathSignal = false;
				tickCounter_death = 0;
			}
		}
		else {
			currentTimeMillis = System.currentTimeMillis();
			g.drawImage(heartFull, 10, 10, null);
			g.drawImage(one, 20, 13, null);
		}
	}
	
	public void constructHeartFullToEmptyToFull() {
		heartFull = loader.loadImage("/Kenney/Base pack/HUD/hud_heartFull.png");
		heartEmpty = loader.loadImage("/Kenney/Base pack/HUD/hud_heartEmpty.png");
		zero = loader.loadImage("/Kenney/Base pack/HUD/hud_0.png");
		one = loader.loadImage("/Kenney/Base pack/HUD/hud_1.png");
		
		int s = 0;
		while (s != heartAnimation.length) {
			heartAnimation[s] = heartEmpty;
			s++;
		}
		
		s = 0;
		while (s != heartAnimation.length) {
			numberAnimation[s] = zero;
			s++;
		}
	}
	
	public void constructDeath() {
		BufferedImage[] deathSprites = new BufferedImage[12];
		for (int i = 1; i <= 12; i++) {
			deathSprites[i-1] = loader.loadImage("/death_animations/death_animation" + i + ".png");
		}
		int s = 0;
		while (s != deathAnimation.length) {
			deathAnimation[s] = deathSprites[s/2];
			s++;
		}
	}

}
