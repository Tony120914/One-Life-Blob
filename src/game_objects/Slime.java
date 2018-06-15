package game_objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game_states.GAMESTATE;
import game_states.GameStateChooser;
import images.SpriteSheet;
import main.Handler;
import main.Main;

public class Slime extends GameObject{
	
	private BufferedImage[] walk = new BufferedImage[2*12];
	private BufferedImage slimeDead;
	private int width = 75, height = 36;
	private int tickCounter = 0;
	private GameObject player;
	private GameObject gameObjectCollision;
	private boolean dead = false;
	private Graphics2D g2d;
	
	private boolean reversed = false;
	private boolean triggered = false;
	private boolean escape = false;

	public Slime(double x, double y, ID id, Handler handler) {
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
		
		this.x = Main.clamp(this.x, 0, Main.WIDTH - this.width - 5);
		this.y = Main.clamp(this.y, 0, Main.HEIGHT - this.height - 25);
		
		if (this.dead) {
			this.velX = 0;
			this.velY = 0;
		}
		else {
			tutorial4_mechanic();
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
			g.drawImage(slimeDead, (int) this.x, (int) this.y + 16, null);
		}
		else if (this.velX < 0){ // left
			g.drawImage(walk[tickCounter], (int) this.x, (int) this.y, null);
		}
		else if (this.velX >= 0) { // right
			g2d.drawImage(walk[tickCounter], (int) this.x + walk[tickCounter].getWidth(), (int) this.y, -walk[tickCounter].getWidth(), walk[tickCounter].getHeight(), null);
		}
		
	}

	@Override
	public void constructGameObject() {
		BufferedImage spriteSheet = loader.loadImage("/Kenney/Base pack/Enemies/enemies_spritesheet.png");
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		slimeDead = ss.grabImage(0, 112, 59, 12);

		int s = 0;
		while (s != walk.length) {
			if (s < walk.length/2) {
				walk[s] = ss.grabImage(52, 125, 50, 28);
			}
			else {
				walk[s] = ss.grabImage(0, 125, 51, 26);
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
		for (int i = 0; i < this.handler.objects.size(); i++) {
			gameObjectCollision = this.handler.objects.get(i);
			if (gameObjectCollision.getId() == ID.Hazard) {
				if (this.getBounds().intersects(gameObjectCollision.getBounds())) {
					dead = true;
				}
			}
		}
	}
	
	public void tutorial4_mechanic() {
		if (GameStateChooser.gameState == GAMESTATE.Tutorial4) {
			if (escape) {
				if (player.getX() == this.x) this.velX = 0;
				else if ((this.x <= 640 || this.x >= 750) && this.y <= 230) this.velX = 0; 
				else if (player.getX() > this.x) this.velX = 1;
				else this.velX = -1;
				
				if (player.getY() == this.y) this.velY = 0;
				else if (player.getY() > this.y) this.velY = 1;
				else this.velY = -1;
			}
			else {
				if (triggered) {
					//at switch
					if ((this.x >= 673 && this.x <= 677 ) && //675 
							(this.y >= 98 && this.y <= 102)) { //100
						this.velX = 0;
						this.velY = 0;
						this.triggered = false;
						this.escape = true;
					}
					else { //sprint to switch
						if (this.x < 640 + 35) this.velX = 2;
						else if (this.x == 640 + 35) this.velX = 0;
						else this.velX = -2;
						
						if (this.y < 60 + 40) this.velY = 2;
						else if (this.y == 640 + 35) this.velY = 0;
						else this.velY = -2;
					}
				}
				else { //not triggered
					if (this.x == 640 && this.y == 175) {
						this.velX = 1; //starting point
						reversed = false;
					}
					else if (this.x == 740 && this.y == 175) { //middle point
						if (reversed) {
							this.velX = -1;
							this.velY = 0;
						}
						else if (!reversed) {
							this.velX = 0;
							this.velY = -1;
						}
					}
					else if (this.x == 740 && this.y == 100) { //end point
						this.velY = 1;
						reversed = true;
					}
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
	
	public boolean getTriggered() {
		return this.triggered;
	}
	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}
	
	public boolean getEscape() {
		return this.escape;
	}
	public void setEscape(boolean escape) {
		this.escape = escape;
	}
	
	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}

}
