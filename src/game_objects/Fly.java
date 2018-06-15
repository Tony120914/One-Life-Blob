package game_objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import images.SpriteSheet;
import main.Handler;
import main.Main;

public class Fly extends GameObject{
	
	private BufferedImage[] walk = new BufferedImage[2*12];
	private BufferedImage flyDead;
	private int width = 75, height = 36;
	private int tickCounter = 0;
	private GameObject player;
	private boolean dead = false;
	private Graphics2D g2d;

	public Fly(double x, double y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		int temp = (int) Math.round(Math.random());
		if (temp == 0) temp = -1;
		else temp = 1;
		this.velX = 2.5 * temp;
		this.velY = 2.5 * temp;
		this.handler = handler;
		constructGameObject();
		
		for (int h = 0; h < handler.objects.size(); h++) {
			if (handler.objects.get(h).getId() == ID.Player)
				player = handler.objects.get(h);
		}
	}

	@Override
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
		
		this.x = Main.clamp(this.x, 0, Main.WIDTH - this.width - 5);
		this.y = Main.clamp(this.y, 0, Main.HEIGHT - this.height - 25);
		
		if (this.dead) {
			this.velX = 0;
			this.velY = 0;
		}
		else {
			if (this.x <= 0 || this.x >= Main.WIDTH - width - 10) this.velX *= -1;
			if (this.y <= 0 || this.y >= Main.HEIGHT - height - 25) this.velY *= -1;
		}
		
		// Timer for walking animation
		while (true) {
			tickCounter++;
			if (tickCounter == walk.length) {
				tickCounter = 0;
			}
			break;
		}
		
		collision();
	}

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D)g;
		if (dead) {
			g.drawImage(flyDead, (int) this.x, (int) this.y, null);
		}
		else if (this.velX < 0){ // left
			g.drawImage(walk[tickCounter], (int) this.x, (int) this.y, null);
		}
		else if (this.velX > 0) { // right
			g2d.drawImage(walk[tickCounter], (int) this.x + walk[tickCounter].getWidth(), (int) this.y, -walk[tickCounter].getWidth(), walk[tickCounter].getHeight(), null);
		}
		
	}

	@Override
	public void constructGameObject() {
		BufferedImage spriteSheet = loader.loadImage("/Kenney/Base pack/Enemies/enemies_spritesheet.png");
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		flyDead = ss.grabImage(143, 0, 59, 33);

		int s = 0;
		while (s != walk.length) {
			if (s < walk.length/2) {
				walk[s] = ss.grabImage(0, 32, 72, 36);
			}
			else {
				walk[s] = ss.grabImage(0, 0, 75, 31);
			}
			s++;
		}		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, width, height);
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean getDead() {
		return this.dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
