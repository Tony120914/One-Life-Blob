package game_objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import images.SpriteSheet;
import main.Handler;
import main.Main;

public class Snail extends GameObject {
	
	private BufferedImage snail_idle, snail_dead;
	private BufferedImage[] walk = new BufferedImage[2*12];
	private int row_idle = 103, col_idle = 119, width_idle = 44, height_idle = 30;
	private int tickCounter = 0;
	private GameObject player;
	private boolean idle = true, go_left = false, go_right = false, dead = false;
	private int aggro_range = Main.WIDTH/4;
	private GameObject gameObjectCollision;
	private Graphics2D g2d;
	private double distanceX, distanceY, distanceXY;

	public Snail(double x, double y, ID id, Handler handler) {
		super(x, y, id, handler);
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
		
		this.x = Main.clamp(this.x, 0, Main.WIDTH - this.width_idle - 5);
		this.y = Main.clamp(this.y, 0, Main.HEIGHT - this.height_idle - 25);
		
		distanceX = this.x + this.width_idle/2 - player.getX() - 66/2;
		distanceY = this.y - this.height_idle/2 - player.getY() - 46/2;
		distanceXY = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		
		if (this.dead) {
			this.velX = 0;
			this.velY = 0;
		}
		else {
			if (distanceXY > aggro_range) {
				idle = true;
				go_left = false; go_right = false;
				this.velX = 0;
				this.velY = 0;
			} else idle = false;
			
			if (distanceX > 0 && idle == false) { //go left
				go_left = true; go_right = false;
				this.velX = -0.5;
			}
			else if (distanceX < 0 && idle == false) { //go right
				go_right = true; go_left = false;
				this.velX = +0.5;
			}
			else if (distanceX == 0 && idle == false) { //do nothing on x when aligned
				this.velX = 0; go_right = true;
			}
			if (distanceY >= 0 && idle == false) {  //go up
				this.velY = -0.5;
			}
			else if (distanceY < 0 && idle == false) { //go down
				this.velY = +0.5;
			}
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
			g.drawImage(snail_dead, (int) this.x, (int) this.y, null);
		}
		else if (idle)
			g.drawImage(snail_idle, (int) this.x, (int) this.y, null);
		else if (go_left){
			g.drawImage(walk[tickCounter], (int) this.x, (int) this.y, null);
		}
		else if (go_right) {
			g2d.drawImage(walk[tickCounter], (int) this.x + walk[tickCounter].getWidth(), (int) this.y, -walk[tickCounter].getWidth(), walk[tickCounter].getHeight(), null);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, width_idle, height_idle);
	}
	
	@Override
	public void constructGameObject() {
		snail_idle = loader.loadImage("/Kenney/Base pack/Enemies/enemies_spritesheet.png");
		SpriteSheet ss = new SpriteSheet(snail_idle);
		snail_idle = ss.grabImage(row_idle, col_idle, width_idle, height_idle);
		snail_dead = ss.grabImage(148, 118, 44, 30);

		int s = 0;
		while (s != walk.length) {
			if (s < walk.length/2) {
				walk[s] = ss.grabImage(143, 34, 54, 31);
			}
			else {
				walk[s] = ss.grabImage(67, 87, 57, 31);
			}
			s++;
		}
		
	}
	
	public void collision() {
		for (int i = 0; i < this.handler.objects.size(); i++) {
			gameObjectCollision = this.handler.objects.get(i);
			if (gameObjectCollision.getId() == ID.Hazard) {
				if (this.getBounds().intersects(gameObjectCollision.getBounds())) {
					dead = true;
				}
			}
		}
	}
	
	public boolean getDead() {
		return this.dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
