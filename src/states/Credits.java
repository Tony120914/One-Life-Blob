package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import images.BufferedImageLoader;
import images.SpriteSheet;
import main.Handler;
import main.Main;
import sounds.SoundMusicPlayer;

public class Credits extends MouseAdapter{
	
	private Main main;
	private Handler handler;
	private BufferedImage menuImage;
	private int buttonWidth = 426, buttonHeight = 100;
	private int backX = Main.WIDTH/3, backY = Main.HEIGHT/4 + 125*3;
	private Font font = new Font("Monospaced", 1, 20);
	private Font fontBack = new Font("Monospaced", 1, 50);
	
	public Credits(Main main, Handler handler) {
		this.main = main;
		this.handler = handler;
		construct();
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		// Menu background
		g.drawImage(menuImage, 0, 0, null);
		
		// credits text
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Made by: Toe Knee (with Java - Eclipse)", 100 , 100);
		g.drawString("Spritesheet owners:", 100, 200);
		g.drawString("   Kenney Vleugels (www.kenney.nl)", 100 , 250);
		g.drawString("   Robert (0x72.itch.io/16x16-dungeon-tileset)", 100 , 300);
		g.drawString("Sound and music owners:", 100, 400);
		g.drawString("   LittleRobotSoundFactory (freesound.org)", 100 , 450);
		g.drawString("   ObsydianX (https://obsydianx.itch.io/interface-sfx-pack-1)", 100, 500);
		g.drawString("   DL Sounds (https://www.dl-sounds.com/royalty-free/marimba-boy/)", 100, 550);
		
		// Back
		g.setFont(fontBack);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Back", backX + 150, backY + 65);
		g.drawRoundRect(backX, backY, buttonWidth, buttonHeight, 50, 50);
	}
	
	public void mousePressed(MouseEvent e) {
		if (Main.state == STATE.Credits) {
			if (main.clicked(e.getX(), e.getY(), backX, backY, buttonWidth, buttonHeight)){
//				SoundMusicPlayer.getSound("back").play();
				SoundMusicPlayer.clips.get("back").start();
				SoundMusicPlayer.clips.get("back").setMicrosecondPosition(0);
				Main.state = STATE.Menu;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void construct() {
		BufferedImageLoader loader = new BufferedImageLoader();
		menuImage = loader.loadImage("/Kenney/Mushroom expansion/Backgrounds/bg_shroom_expanded.png");
		SpriteSheet ss = new SpriteSheet(menuImage);
		menuImage = ss.grabImage(0, 0, menuImage.getWidth(), menuImage.getHeight());
	}

}
