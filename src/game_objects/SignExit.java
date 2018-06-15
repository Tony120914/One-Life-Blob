package game_objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Handler;

public class SignExit extends GameObject{
	private BufferedImage exit;
	private int width = 70;
	private int height = 70;
	private boolean onExit = false;
	private GameObject gameObjectCollision;

	public SignExit(double x, double y, ID id, Handler handler) {
		super(x, y, id, handler);
		constructGameObject();
	}

	@Override
	public void tick() {
		collision();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(exit, (int) x, (int) y, null);
	}

	@Override
	public void constructGameObject() {
		exit = loader.loadImage("/Kenney/Base pack/Tiles/signExit.png");
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, width, height);
	}

	@Override
	public void collision() {
		for (int i = 0; i < this.handler.objects.size(); i++) {
			gameObjectCollision = this.handler.objects.get(i);
			if (gameObjectCollision.getId() == ID.Player) {
				if (this.getBounds().intersects(gameObjectCollision.getBounds())) {
					this.onExit = true;
				}
			}
		}
	}
	
	public boolean getOnExit() {
		return this.onExit;
	}
	
	public void setOnExit(boolean onExit) {
		this.onExit = onExit;
	}
}
