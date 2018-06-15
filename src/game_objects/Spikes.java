package game_objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import images.SpriteSheet;
import main.Handler;

public class Spikes extends GameObject{
	private BufferedImage spikes;
	private int width = 70;
	private int height = 35;

	public Spikes(double x, double y, ID id, Handler handler) {
		super(x, y, id, handler);
		constructGameObject();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(spikes, (int) x, (int) y, null);
	}

	@Override
	public void constructGameObject() {
		spikes = loader.loadImage("/Kenney/Base pack/Items/spikes.png");
		SpriteSheet ss = new SpriteSheet(spikes);
		spikes = ss.grabImage(0, 35, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, width, height);
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub
		
	}
}
