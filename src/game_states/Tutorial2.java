package game_states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import backgrounds.Grass;
import game_objects.GameObject;
import game_objects.ID;
import game_objects.Player;
import game_objects.SignExit;
import game_objects.Snail;
import game_objects.Spikes;
import main.Handler;
import main.Main;

public class Tutorial2 extends GameStateAbstract{
	
	private GameObject player;
	private Snail snail;
	private Grass grass = new Grass();
	private SignExit signExit;
	private Font font = new Font("Monospaced", 1, 25);
	
	int spikeWidth = 70;
	int spikeHeight = 35;
	private ArrayList<Spikes> spikes = new ArrayList<Spikes>();
	private Spikes spikesLock1 = new Spikes(Main.WIDTH/2 + spikeWidth*6, Main.HEIGHT/2 + spikeHeight*4, ID.Hazard, handler);
	private Spikes spikesLock2 = new Spikes(Main.WIDTH/2 + spikeWidth*7, Main.HEIGHT/2 + spikeHeight*4, ID.Hazard, handler);

	public Tutorial2(Main main, Handler handler) {
		super(main, handler);
		
		signExit = new SignExit(Main.WIDTH - 185, Main.HEIGHT - 125, ID.Exit, handler);
		constructSpikes();
		player = Tutorial1.player;
//player = new Player(Main.WIDTH/10, Main.HEIGHT/2-46, ID.Player, handler);
//handler.addObject(player);		
		snail = new Snail(Main.WIDTH/2, Main.HEIGHT/2, ID.Snail, handler);
	}

	@Override
	public void tick() {
		if (GameStateChooser.gameState == GAMESTATE.Tutorial2) { // transition to this game state
			handler.addObject(snail);
			for (int i = 0; i < spikes.size(); i++) {
				handler.addObject(spikes.get(i));
			}
			handler.addObject(signExit);
		}
		
		if (snail.getDead()) {
			spikes.remove(spikesLock1);
			spikes.remove(spikesLock2);
			handler.removeObject(spikesLock1);
			handler.removeObject(spikesLock2);
		}
		
		handler.tick();
		if (Player.deathSignal) {
			resetGameState();
		}
		
		else if (signExit.getOnExit()) {
			signExit.setOnExit(false);
			clearHandler();
			GameStateChooser.gameState = GAMESTATE.Tutorial3;
		}
		
	}

	@Override
	public void render(Graphics g) {
		grass.render(g);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("I heard snails aren't very intelligent...", 200, 100);
		
		signExit.render(g);
		
		for (int i = 0; i < spikes.size(); i++) {
			spikes.get(i).render(g);
		}
		
		
		handler.render(g);
	}

	@Override
	public void resetGameState() {
		player.setX(Main.WIDTH/10);
		player.setY(Main.HEIGHT/2-46);
		snail.setX(Main.WIDTH/2);
		snail.setY(Main.HEIGHT/2);
		snail.setDead(false);
		spikes.add(spikesLock1);
		spikes.add(spikesLock2);
		handler.addObject(spikesLock1);
		handler.addObject(spikesLock2);
	}
	
	@Override
	public void clearHandler() {
		handler.objects.clear();
		handler.addObject(player);
		// reset player
		player.setX(Main.WIDTH/10);
		player.setY(Main.HEIGHT/2-46);
	}
	
	public void constructSpikes() {
		//"left" of sign spikes
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*4, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*5, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*6, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*7, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*5, Main.HEIGHT/2 + spikeHeight*8, ID.Hazard, handler));
		//"right" of sign spikes
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*4, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*5, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*6, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*7, ID.Hazard, handler));
		spikes.add(new Spikes(Main.WIDTH/2 + spikeWidth*8, Main.HEIGHT/2 + spikeHeight*8, ID.Hazard, handler));
		
		//spike to kill snail with
		spikes.add(new Spikes(1000, 150, ID.Hazard, handler));
		
		
		//"top" of sign spikes (to be removed when snail dies)
		spikes.add(spikesLock1);
		spikes.add(spikesLock2);
	}

}
