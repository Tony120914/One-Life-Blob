package game_objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Handler;

public class Rock extends GameObject{
	
	private BufferedImage rock;
	int width = 70, height = 45;

	public Rock(double x, double y, ID id, Handler handler) {
		super(x, y, id, handler);
		constructGameObject();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(rock, (int) x, (int) y, null);
	}

	@Override
	public void constructGameObject() {
		rock = loader.loadImage("/Kenney/Base pack/Items/rock.png");
		rock = rock.getSubimage(0, 25, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, width, height);
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
}
