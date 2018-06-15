package game_states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import backgrounds.Grass;
import game_objects.ID;
import game_objects.Player;
import game_objects.SignExit;
import game_objects.SignRight;
import game_objects.Spikes;
import main.Handler;
import main.Main;

public class Tutorial1 extends GameStateAbstract{
	
	public static Player player;
	private Grass grass = new Grass();
	private SignRight signRight;
	private SignExit signExit;
	private ArrayList<Spikes> spikes = new ArrayList<Spikes>();
	private Font font = new Font("Monospaced", 1, 50);
	
	public Tutorial1(Main main, Handler handler) {
		super(main, handler);
		
		signExit = new SignExit(Main.WIDTH - 185, Main.HEIGHT - 125, ID.Exit, handler);
		signRight = new SignRight(Main.WIDTH/3, Main.HEIGHT/2 + 15 - 50, ID.Inanimate, handler);
		
		constructSpikes();
		
		player = new Player(Main.WIDTH/10, Main.HEIGHT/2-46, ID.Player, handler);
		handler.addObject(player);
		handler.addObject(signExit);
	}
	
	public void tick() {
		handler.tick();
		if (Player.deathSignal) {
			resetGameState();
		}
		
		else if (signExit.getOnExit()) {
			signExit.setOnExit(false);
			clearHandler();
			GameStateChooser.gameState = GAMESTATE.Tutorial2;
		}
	}
	public void render(Graphics g) {
		grass.render(g);
		// WASD tutorial
		int W_x = Main.WIDTH/10 + 17;
		int W_y = Main.HEIGHT/2 - 75;
		int A_x = Main.WIDTH/10 - 67;
		int A_y = Main.HEIGHT/2 + 15;
		int S_x = Main.WIDTH/10 + 17;
		int S_y = Main.HEIGHT/2 + 100;
		int D_x = Main.WIDTH/10 + 107;
		int D_y = Main.HEIGHT/2 + 15;
		int fontWidth = 10;
		int fontHeight = 40;
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("W", W_x, W_y);
		g.drawString("A", A_x, A_y);
		g.drawString("S", S_x, S_y);
		g.drawString("D", D_x, D_y);
		g.drawRoundRect(W_x - fontWidth, W_y - fontHeight, 50, 50, 25, 25);
		g.drawRoundRect(A_x - fontWidth, A_y - fontHeight, 50, 50, 25, 25);
		g.drawRoundRect(S_x - fontWidth, S_y - fontHeight, 50, 50, 25, 25);
		g.drawRoundRect(D_x - fontWidth, D_y - fontHeight, 50, 50, 25, 25);
		
		signRight.render(g);
		signExit.render(g);
		
		for (int i = 0; i < spikes.size(); i++) {
			spikes.get(i).render(g);
		}
		
		
		handler.render(g);
		
	}
	
	public void resetGameState() {
		player.setX(Main.WIDTH/10);
		player.setY(Main.HEIGHT/2-46);
	}
	
	public void clearHandler() {
		handler.objects.clear();
		handler.addObject(player);
		// reset player
		player.setX(Main.WIDTH/10);
		player.setY(Main.HEIGHT/2-46);
	}
	
	public void constructSpikes() {
		int spikeWidth = 70;
		int spikeHeight = 35;
		//"right" wall spikes
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*0, Main.HEIGHT/2, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*1, Main.HEIGHT/2, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*2, Main.HEIGHT/2, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*3, Main.HEIGHT/2, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*4, Main.HEIGHT/2, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 - spikeHeight*1, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 - spikeHeight*2, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 - spikeHeight*3, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 - spikeHeight*4, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 - spikeHeight*5, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*1, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*2, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*3, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*4, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*5, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*6, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*7, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*8, ID.Hazard, handler));
		//"left" wall spikes
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*0, Main.HEIGHT/2 - spikeHeight*5, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*1, Main.HEIGHT/2 - spikeHeight*5, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*2, Main.HEIGHT/2 - spikeHeight*5, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*2, Main.HEIGHT/2 - spikeHeight*6, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*2, Main.HEIGHT/2 - spikeHeight*7, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*2, Main.HEIGHT/2 - spikeHeight*8, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*2, Main.HEIGHT/2 - spikeHeight*9, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*2, Main.HEIGHT/2 - spikeHeight*10, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*2, Main.HEIGHT/2 - spikeHeight*10, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*3, Main.HEIGHT/2 - spikeHeight*10, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*4, Main.HEIGHT/2 - spikeHeight*10, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 - spikeHeight*10, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*6, Main.HEIGHT/2 - spikeHeight*10, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*7, Main.HEIGHT/2 - spikeHeight*10, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 - spikeHeight*10, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 - spikeHeight*9, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 - spikeHeight*8, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 - spikeHeight*7, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 - spikeHeight*6, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 - spikeHeight*5, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 - spikeHeight*4, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 - spikeHeight*3, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 - spikeHeight*2, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 - spikeHeight*1, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 - spikeHeight*0, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*1, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*2, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*3, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*4, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*5, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*6, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*7, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*8, ID.Hazard, handler));
		
		for (int i = 0; i < spikes.size(); i++) {
			handler.addObject(spikes.get(i));
		}
	}
	
}
