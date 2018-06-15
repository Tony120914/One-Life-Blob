package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import images.BufferedImageLoader;
import images.SpriteSheet;
import main.Handler;
import main.Main;
import sounds.SoundMusicPlayer;

public class Menu extends MouseAdapter{
	
	private Main main;
	private Handler handler;
	
	private Graphics2D g2d;
	private BufferedImage menuImage;
	private int buttonWidth = 426, buttonHeight = 100;
	private int startX = Main.WIDTH/3, startY = Main.HEIGHT/4;
	private int instructionsX = startX, instructionsY = startY + 125;
	private int creditsX = instructionsX, creditsY = instructionsY + 125;
	private int quitX = creditsX, quitY = creditsY + 125;
	private Font fontTitle = new Font("Monospaced", 1, 75);
	private Font fontStart = new Font("Monospaced", 1, 50);
	private Font fontInstructions = new Font("Monospaced", 1, 50);
	private Font fontCredits = new Font("Monospaced", 1, 50);
	private Font fontQuit = new Font("Monospaced", 1, 50);
	private BufferedImageLoader loader = new BufferedImageLoader();
	private SpriteSheet ss = new SpriteSheet(null);
	
	private int tickPlayer = 0;
	private BufferedImage playerImage;
	private BufferedImage[] playerWalk = new BufferedImage[11*3];
	
	private int tickSnail = 0;
	private BufferedImage[] snailWalk = new BufferedImage[2*12];
	
	private int tickFly = 0;
	private BufferedImage[] flyWalk = new BufferedImage[2*12];
	
	private int tickSlime = 0;
	private BufferedImage[] slimeWalk = new BufferedImage[2*12];
	
	
	
	public Menu(Main main, Handler handler) {
		this.main = main;
		this.handler = handler;
		
		construct();
	}
	
	public void tick() {
		while (true) {
			tickPlayer++;
			if (tickPlayer == playerWalk.length) {
				tickPlayer = 0;
			}
			break;
		}
		while (true) {
			tickSnail++;
			if (tickSnail == snailWalk.length) {
				tickSnail = 0;
			}
			break;
		}
		while (true) {
			tickFly++;
			if (tickFly == flyWalk.length) {
				tickFly = 0;
			}
			break;
		}
		while (true) {
			tickSlime++;
			if (tickSlime == slimeWalk.length) {
				tickSlime = 0;
			}
			break;
		}
	}
	
	
	public void render(Graphics g) {
		g2d = (Graphics2D)g;
		menuImage = loader.loadImage("/Kenney/Mushroom expansion/Backgrounds/bg_shroom_expanded.png");
		ss = new SpriteSheet(menuImage);
		menuImage = ss.grabImage(0, 0, menuImage.getWidth(), menuImage.getHeight());
		ss = null;
		
		// Menu background
		g.drawImage(menuImage, 0, 0, null);
		
		// Title
		g.setFont(fontTitle);
		g.setColor(Color.WHITE);
		g.drawString("One-Life Blob", Main.WIDTH/4 + 20, Main.HEIGHT/6);
		
		// Start
		g.setFont(fontStart);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Start", startX + 135, startY + 65);
		g.drawRoundRect(startX, startY, buttonWidth, buttonHeight, 50, 50);
		
		// Instructions
		g.setFont(fontInstructions);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Instructions", instructionsX + 40, instructionsY + 65);
		g.drawRoundRect(instructionsX, instructionsY, buttonWidth, buttonHeight, 50, 50);
		
		// Credits
		g.setFont(fontCredits);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Credits", creditsX + 110, creditsY + 65);
		g.drawRoundRect(creditsX, creditsY, buttonWidth, buttonHeight, 50, 50);
		
		// Quit
		g.setFont(fontQuit);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Quit", quitX + 150, quitY + 65);
		g.drawRoundRect(quitX, quitY, buttonWidth, buttonHeight, 50, 50);
		
		g.drawImage(playerWalk[tickPlayer], 340, 183, null);
		g.drawImage(snailWalk[tickSnail], 875, 340, null);
		g2d.drawImage(flyWalk[tickFly], 340 + flyWalk[tickFly].getWidth(), 470, -flyWalk[tickFly].getWidth(), flyWalk[tickFly].getHeight(), null);
		g.drawImage(slimeWalk[tickSlime], 875, 590, null);
		
	}
	
	public void mousePressed(MouseEvent e) {
		if (Main.state == STATE.Menu) {
			if (main.clicked(e.getX(), e.getY(), startX, startY, buttonWidth, buttonHeight)){
//				SoundMusicPlayer.getSound("confirmEcho").play();
				SoundMusicPlayer.clips.get("confirmEcho").start();
				SoundMusicPlayer.clips.get("confirmEcho").setMicrosecondPosition(0);
				Main.state = STATE.Game;
			}
			else if (main.clicked(e.getX(), e.getY(), instructionsX, instructionsY, buttonWidth, buttonHeight)){
//				SoundMusicPlayer.getSound("confirm").play();
				SoundMusicPlayer.clips.get("confirm").start();
				SoundMusicPlayer.clips.get("confirm").setMicrosecondPosition(0);
				Main.state = STATE.Instructions;
			}
			else if (main.clicked(e.getX(), e.getY(), creditsX, creditsY, buttonWidth, buttonHeight)){
//				SoundMusicPlayer.getSound("confirm").play();
				SoundMusicPlayer.clips.get("confirm").start();
				SoundMusicPlayer.clips.get("confirm").setMicrosecondPosition(0);
				Main.state = STATE.Credits;
			}
			else if (main.clicked(e.getX(), e.getY(), quitX, quitY, buttonWidth, buttonHeight)){
//				SoundMusicPlayer.getSound("quit").play();
				SoundMusicPlayer.clips.get("quit").start();
				SoundMusicPlayer.clips.get("quit").setMicrosecondPosition(0);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.exit(1);
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void construct() {
		//Player
		playerImage = loader.loadImage("/Kenney/Base pack/Player/p1_spritesheet.png");
		SpriteSheet ss = new SpriteSheet(playerImage);
		BufferedImage[] playerSprites = new BufferedImage[11];
		playerSprites[0] = ss.grabImage(0, 0, 72, 97);
		playerSprites[1] = ss.grabImage(73, 0, 72, 97); 
		playerSprites[2] = ss.grabImage(146, 0, 72, 97);
		playerSprites[3] = ss.grabImage(0, 98, 72, 97); 
		playerSprites[4] = ss.grabImage(73, 98, 72, 97); 
		playerSprites[5] = ss.grabImage(146, 98, 72, 97); 
		playerSprites[6] = ss.grabImage(219, 0, 72, 97);
		playerSprites[7] = ss.grabImage(292, 0, 72, 97);
		playerSprites[8] = ss.grabImage(219, 98, 72, 97); 
		playerSprites[9] = ss.grabImage(365, 0, 72, 97); 
		playerSprites[10] = ss.grabImage(292, 98, 72, 97);
		int s = 0;
		while (s != playerWalk.length) {
			playerWalk[s] = playerSprites[s/3];
			s++;
		}
		
		//Snail
		BufferedImage enemiesSpiteSheet = loader.loadImage("/Kenney/Base pack/Enemies/enemies_spritesheet.png");
		ss = new SpriteSheet(enemiesSpiteSheet);
		s = 0;
		while (s != snailWalk.length) {
			if (s < snailWalk.length/2) {
				snailWalk[s] = ss.grabImage(143, 34, 54, 31);
			}
			else {
				snailWalk[s] = ss.grabImage(67, 87, 57, 31);
			}
			s++;
		}
		
		//Fly
		ss = new SpriteSheet(enemiesSpiteSheet);
		s = 0;
		while (s != flyWalk.length) {
			if (s < flyWalk.length/2) {
				flyWalk[s] = ss.grabImage(0, 32, 72, 36);
			}
			else {
				flyWalk[s] = ss.grabImage(0, 0, 75, 31);
			}
			s++;
		}	
		
		//Slime
		ss = new SpriteSheet(enemiesSpiteSheet);
		s = 0;
		while (s != slimeWalk.length) {
			if (s < slimeWalk.length/2) {
				slimeWalk[s] = ss.grabImage(52, 125, 50, 28);
			}
			else {
				slimeWalk[s] = ss.grabImage(0, 125, 51, 26);
			}
			s++;
		}	
		
	}
}
