package backgrounds;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import images.BufferedImageLoader;
import images.SpriteSheet;
import main.Main;

public class Grass extends Background{
	private BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage grass, grassEdge, grassDirt;
	private Graphics2D g2d;
	private double rotate;
	private double locationX;
	private double locationY;
	private AffineTransform at;
	private AffineTransformOp ato;
	
	public Grass() {
		super();
		constructBackground();
	}

	@Override
	public void tick() {
		/* Nothing, for now... */
	}

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D)g;
		for (int y = 0; y < Main.HEIGHT; y += grassEdge.getHeight()) {
			for (int x = 0; x < Main.WIDTH ; x += grassEdge.getWidth()) {
				if (y == 0) { // top: grass edge
					g.drawImage(grassEdge, x, y, null);
				}
				else if (y == grassEdge.getHeight() * 9) { // bottom: grass edge rotated 180
					rotate = Math.toRadians(180);
					locationX = grassEdge.getWidth() / 2;
					locationY = grassEdge.getHeight() / 2;
					at = AffineTransform.getRotateInstance(rotate, locationX, locationY);
					ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
					g2d.drawImage(ato.filter(grassEdge, null), x, y, null);
				}
				else { // middle: grass dirt
					g.drawImage(grassDirt, x, y, null);
				}
			}
		}
	}
	
	public void constructBackground() {
		grass = loader.loadImage("/Kenney/Base pack/Tiles/tiles_spritesheet.png");
		SpriteSheet ss = new SpriteSheet(grass);
		grassEdge = ss.grabImage(504, 576, 70, 70);
		grassDirt = ss.grabImage(576, 864, 70, 70);
	}

}
