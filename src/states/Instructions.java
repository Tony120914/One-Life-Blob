package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import images.BufferedImageLoader;
import images.SpriteSheet;
import main.Handler;
import main.Main;
import sounds.SoundMusicPlayer;

public class Instructions extends MouseAdapter{
	
	private Main main;
	private Handler handler;
	private BufferedImage menuImage;
	private int buttonWidth = 426, buttonHeight = 100;
	private int backX = Main.WIDTH/3, backY = Main.HEIGHT/4 + 125*3;
	private Font font = new Font("Monospaced", 1, 33);
	private Font fontBack = new Font("Monospaced", 1, 50);
	
	public Instructions(Main main, Handler handler) {
		this.main = main;
		this.handler = handler;
		construct();
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		// Menu background
		g.drawImage(menuImage, 0, 0, null);
		
		// instruction text

		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("• WASD to move", 500 , 200);
		g.drawString("• ESC to pause", 500 , 300);
		
		// Back
		g.setFont(fontBack);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Back", backX + 150, backY + 65);
		g.drawRoundRect(backX, backY, buttonWidth, buttonHeight, 50, 50);
	}
	
	public void mousePressed(MouseEvent e) {
		if (Main.state == STATE.Instructions) {
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
