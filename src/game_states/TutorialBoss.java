package game_states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import backgrounds.Grass;
import game_objects.Fly;
import game_objects.GameObject;
import game_objects.ID;
import game_objects.Player;
import game_objects.Rock;
import game_objects.Slime;
import game_objects.Snail;
import game_objects.Spikes;
import game_objects.SwitchTrap;
import main.Handler;
import main.Main;

public class TutorialBoss extends GameStateAbstract{
	
	private GameObject player;
	private Snail currentSnail;
	private Spikes currentSpikes;
	private Slime slime;
	private ArrayList<Fly> flies = new ArrayList<Fly>();
	private Grass grass = new Grass();
	private ArrayList<Rock> rocks = new ArrayList<Rock>();
	private ArrayList<SwitchTrap> switches = new ArrayList<SwitchTrap>();
	private ArrayList<Spikes> spikes = new ArrayList<Spikes>();
	private int numSpikes = 0;
	private Font font = new Font("Monospaced", 1, 25);
	private int numFlies = 2;

	public TutorialBoss(Main main, Handler handler) {
		super(main, handler);
		
		player = Tutorial1.player;
//player = new Player(Main.WIDTH/10, Main.HEIGHT/2-46, ID.Player, handler);
//handler.addObject(player);

		constructRocks();
		constructSwitches();
		constructMechanic();

		slime = new Slime(0, 620, ID.Slime, handler);
		slime.setVelX(0.1);
		for (int i = 0; i < 10; i++) {
			flies.add(new Fly(325 + Math.random()*800, 600 + Math.random()*20, ID.Fly, handler));
		}
		
	}

	@Override
	public void tick() {
		if (GameStateChooser.gameState == GAMESTATE.TutorialBoss) { // transition to this game state
			for (int i = 0; i < numFlies; i++) {
				handler.addObject(flies.get(i));
			}
			for (int i = 0; i < rocks.size(); i++) {
				handler.addObject(rocks.get(i));
			}
			for (int i = 0; i < switches.size(); i++) {
				handler.addObject(switches.get(i));
			}
			handler.addObject(currentSnail);
			handler.addObject(currentSpikes);
			handler.addObject(slime);
		}
		
		for (int i = 0; i < switches.size(); i++) {
			if ((switches.get(i).getX() + switches.get(i).getWidth()/2) <= slime.getX()) {
				switches.get(i).setSwitched(true);
			}
		}
		
		// switches determining # of flies
		if (switches.get(2).getSwitched()) this.numFlies = 10;
		else if (switches.get(1).getSwitched()) this.numFlies = 4;
		else if (switches.get(0).getSwitched()) this.numFlies = 3;
		
		if (currentSnail.getDead()) { //if a snail dies
			currentSnail.setDead(false);
			double snailX = 325 + Math.round(Math.random()*800);
			double snailY = 450 - Math.random()*300;
			double spikesX = 325 + Math.random()*800;
			double spikesY = 450 - Math.random()*300;
			while (player.getBounds().intersects(new Rectangle((int) snailX, (int) snailY, 44, 30)) ||
					player.getBounds().intersects(new Rectangle((int) spikesX, (int) spikesY, 70, 35))) {
				snailX = 325 + Math.round(Math.random()*800);
				snailY = 450 - Math.random()*300;
				spikesX = 325 + Math.random()*800;
				spikesY = 450 - Math.random()*300;
			}
			
			currentSnail.setX(snailX);
			currentSnail.setY(snailY);
			currentSpikes.setX(spikesX);
			currentSpikes.setY(spikesY);
			
			handler.addObject(spikes.get(numSpikes));
			numSpikes++;
		}
		
		if (slime.getDead()) { //if boss slime is dead
			handler.objects.clear();
			handler.addObject(player);
		}
		
		handler.tick();
		
		
		if (Player.deathSignal) {
			resetGameState();
		}
		
//		else if (signExit.getOnExit()) {
//			signExit.setOnExit(false);
//			clearHandler();
//			GameStateChooser.gameState = GAMESTATE.Tutorial4;
//		}
		
		
	}

	@Override
	public void render(Graphics g) {
		grass.render(g);
		
		if (!slime.getDead()) {
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("Boss fight already? I hope you're feeling lucky...", 50, 500);
		}
		else {
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("That's all for now folks...", Main.WIDTH/2 - 200, Main.HEIGHT/2 - 100);
			g.drawString("Thanks for playing!", Main.WIDTH/2 - 140, Main.HEIGHT/2 - 50);
		}

		
		handler.render(g);
		
		
	}

	@Override
	public void resetGameState() {
		player.setX(Main.WIDTH/10);
		player.setY(Main.HEIGHT/2-46);
		slime.setX(0);
		slime.setY(620);
		slime.setDead(false);
		currentSnail.setDead(false);
		currentSnail.setX(325 + Math.round(Math.random()*800));
		currentSnail.setY(450 - Math.random()*300);
		currentSpikes.setX(325 + Math.random()*800);
		currentSpikes.setY(450 - Math.random()*300);
		this.numFlies = 2;
		this.numSpikes = 0;
		
		for (int i = 0; i < switches.size(); i++) {
			switches.get(i).setSwitched(false);
			switches.get(i).setTickCounter(0);
		}
		for (int i = 0; i < flies.size(); i++) {
			handler.removeObject(flies.get(i));
			int temp = (int) Math.round(Math.random());
			if (temp == 0) temp = -1;
			else temp = 1;
			flies.get(i).setVelX(flies.get(i).getVelX() * temp);
			flies.get(i).setVelY(flies.get(i).getVelY() * temp);
			flies.get(i).setX(325 + Math.random()*800);
			flies.get(i).setY(600 + Math.random()*20);
		}
		for (int i = 0; i < spikes.size(); i++) {
			handler.removeObject(spikes.get(i));
		}
	}

	@Override
	public void clearHandler() {
		handler.objects.clear();
		handler.addObject(player);
		// reset player
		player.setX(Main.WIDTH/10);
		player.setY(Main.HEIGHT/2-46);
	}
	
	public void constructRocks() {
		for (int i = 0; i < Main.WIDTH; i += 35) {
			rocks.add(new Rock(i, 550, ID.Obstacle, handler));
		}
	}
	
	public void constructSwitches() {
		for (int i = 320; i < Main.WIDTH; i += 320) {
			switches.add(new SwitchTrap(i, 575, ID.Inanimate, handler));
		}
	}
	
	public void constructMechanic() {
		this.currentSnail = new Snail(325 + Math.round(Math.random()*800), 450 - Math.random()*300, ID.Snail, handler);
		this.currentSpikes = new Spikes(325 + Math.random()*800, 450 - Math.random()*300, ID.Hazard, handler);
		
		for (int i = Main.WIDTH-70; i > 70; i-=70) {
			this.spikes.add(new Spikes(i, 620, ID.Hazard, handler));
		}
	}

}
