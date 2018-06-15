package game_objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Handler;

public class SignRight extends GameObject{
	
	private BufferedImage signRight;
	private int width = 70;
	private int height = 70;

	public SignRight(double x, double y, ID id, Handler handler) {
		super(x, y, id, handler);
		constructGameObject();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(signRight, (int) x, (int) y, null);
	}

	@Override
	public void constructGameObject() {
		signRight = loader.loadImage("/Kenney/Base pack/Tiles/signRight.png");
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
