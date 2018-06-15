package game_objects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import images.SpriteSheet;
import main.Handler;
import main.Keyboard;
import main.Main;
import sounds.SoundMusicPlayer;
import states.STATE;

public class Player extends GameObject{

	private BufferedImage player_idle;
	private BufferedImage[] walk = new BufferedImage[11*3];
	private int row_idle = 0, col_idle = 196, width_idle = 66, height_idle = 92;
	private int tickCounter = 0;
	public static boolean deathSignal = false;
	public static boolean faceLeft = false;
	private Graphics2D g2d;
	private GameObject gameObjectCollision;
	
	public Player(double x, double y, ID id, Handler handler) {
		super(x, y, id, handler);
		this.handler = handler;
		constructGameObject();
	}

	@Override
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
		
		//clamp screen borders
		this.x = Main.clamp(this.x, 0, Main.WIDTH - this.width_idle - 5);
		this.y = Main.clamp(this.y, 0, Main.HEIGHT - this.height_idle - 25);
		
		// Timer for walking animation
		if (Keyboard.keyPressed[0] || Keyboard.keyPressed[1] || Keyboard.keyPressed[2] || Keyboard.keyPressed[3]) { 
			while (true) {
				tickCounter++;
				if (tickCounter == walk.length) {
					tickCounter = 0;
				}
				break;
			}
		}
		
		walkingSound();
		deathSound();
		
		collision();
	}
	

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D)g;
		if (!(Keyboard.keyPressed[0] || Keyboard.keyPressed[1] || Keyboard.keyPressed[2] || Keyboard.keyPressed[3])) { // idle 
			g.drawImage(player_idle, (int) this.x, (int) this.y, null);
		}
		else if ((Keyboard.keyPressed[3] || (Keyboard.keyPressed[0] && !Keyboard.keyPressed[1]) || (Keyboard.keyPressed[2] && !Keyboard.keyPressed[1])) && !faceLeft) { // right, up, or down
			g.drawImage(walk[tickCounter], (int) this.x, (int) this.y, null);
		}
		else if ((Keyboard.keyPressed[1] && faceLeft) || (Keyboard.keyPressed[1] && !Keyboard.keyPressed[3]) || faceLeft) { // just left
			// flipped image
			g2d.drawImage(walk[tickCounter], (int) this.x + walk[tickCounter].getWidth(), (int) this.y, -walk[tickCounter].getWidth(), walk[tickCounter].getHeight(), null);
		}
		
	}
	
	@Override
	public void constructGameObject() {
		player_idle = loader.loadImage("/Kenney/Base pack/Player/p1_spritesheet.png");
		SpriteSheet ss = new SpriteSheet(player_idle);
		player_idle = ss.grabImage(row_idle, col_idle, width_idle, height_idle);
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
		while (s != walk.length) {
			walk[s] = playerSprites[s/3];
			s++;
			
		}

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, width_idle, height_idle);
	}
	
	public void collision() {
		for (int i = 0; i < this.handler.objects.size(); i++) {
			gameObjectCollision = this.handler.objects.get(i);
			if (gameObjectCollision.getId() == ID.Hazard || gameObjectCollision.getId() == ID.Snail || gameObjectCollision.getId() == ID.Fly || gameObjectCollision.getId() == ID.Slime) {
				if (this.getBounds().intersects(gameObjectCollision.getBounds())) {
					deathSignal = true;
				}
			}
			else if (gameObjectCollision.getId() == ID.Obstacle) {
				if (this.getBounds().intersects(gameObjectCollision.getBounds())) {
					this.x -= this.velX;
					this.y -= this.velY;
//					if (this.velX > 0) this.x = gameObjectCollision.getX() - gameObjectCollision.getWidth() + 3;
//					else if (this.velX < 0) this.x = gameObjectCollision.getX() + gameObjectCollision.getWidth();
//					if (this.velY > 0) this.y = gameObjectCollision.getY() - gameObjectCollision.getHeight()*2;
//					else if (this.velY < 0) this.y = gameObjectCollision.getY() + gameObjectCollision.getHeight();
				}
			}
		}
	}
	
	public void walkingSound() {
		while (Main.state == STATE.Game &&
//				!SoundMusicPlayer.getSound("walking").playing() &&
				!SoundMusicPlayer.clips.get("walking").isActive() &&
				(this.velX != 0 || this.velY != 0) && !Player.deathSignal){
//			SoundMusicPlayer.getSound("walking").play();
			SoundMusicPlayer.clips.get("walking").start();
			SoundMusicPlayer.clips.get("walking").setMicrosecondPosition(0);
			break;
		}
	}
	
	public void deathSound() {
		while (Main.state == STATE.Game &&
//				!SoundMusicPlayer.getSound("death").playing() &&
				!SoundMusicPlayer.clips.get("death").isActive() &&
				Player.deathSignal){
//			SoundMusicPlayer.getSound("death").play();
			SoundMusicPlayer.clips.get("death").start();
			SoundMusicPlayer.clips.get("death").setMicrosecondPosition(0);
			break;
		}
	}

}
