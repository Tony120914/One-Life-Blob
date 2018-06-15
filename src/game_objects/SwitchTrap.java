package game_objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Handler;

public class SwitchTrap extends GameObject{
	
	private BufferedImage switchLeft;
	private BufferedImage switchMid;
	private BufferedImage switchRight;
	private BufferedImage[] switchedAnimation = new BufferedImage[3*10];
	private int width = 70;
	private int height = 70;
	private int tickCounter = 0;
	
	private boolean switched = false;
	

	public SwitchTrap(double x, double y, ID id, Handler handler) {
		super(x, y, id, handler);
		constructGameObject();
	}

	@Override
	public void tick() {
		while (true) {
			if (switched) tickCounter++;
			if (tickCounter == switchedAnimation.length) {
				tickCounter = switchedAnimation.length-1;
			}
			break;
		}
		
	}

	@Override
	public void render(Graphics g) {
		if (!switched) g.drawImage(switchLeft, (int) x, (int) y, null);
		else {
			g.drawImage(switchedAnimation[tickCounter], (int) x, (int) y, null);
		}
	}

	@Override
	public void constructGameObject() {
		switchLeft = loader.loadImage("/Kenney/Base pack/Items/switchLeft.png");
		switchMid = loader.loadImage("/Kenney/Base pack/Items/switchMid.png");
		switchRight = loader.loadImage("/Kenney/Base pack/Items/switchRight.png");
		
		for (int i = 0; i < switchedAnimation.length*1/3; i++) {
			switchedAnimation[i] = switchLeft;
		}
		for (int i = switchedAnimation.length*1/3; i < switchedAnimation.length*2/3; i++) {
			switchedAnimation[i] = switchMid;
		}
		for (int i = switchedAnimation.length*2/3; i < switchedAnimation.length; i++) {
			switchedAnimation[i] = switchRight;
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
	
	public boolean getSwitched() {
		return this.switched;
	}
	
	public void setSwitched (boolean switched) {
		this.switched = switched;
	}
	
	public void setTickCounter(int tickCounter) {
		this.tickCounter = tickCounter;
	}

}
