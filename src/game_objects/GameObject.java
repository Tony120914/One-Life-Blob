package game_objects;
import java.awt.Graphics;
import java.awt.Rectangle;

import images.BufferedImageLoader;
import main.Handler;

public abstract class GameObject {
	
	protected Handler handler;
	protected BufferedImageLoader loader = new BufferedImageLoader();
	protected double x, y;	//positions
	protected double velX, velY; // velocities
	protected int width, height;
	protected ID id;
	

	public GameObject(double x, double y, ID id, Handler handler) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void constructGameObject();
	public abstract Rectangle getBounds();
	public abstract void collision();

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double d) {
		this.velX = d;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double d) {
		this.velY = d;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
