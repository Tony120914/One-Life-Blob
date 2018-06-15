package game_states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import backgrounds.Grass;
import game_objects.GameObject;
import game_objects.ID;
import game_objects.Player;
import game_objects.Rock;
import game_objects.SignExit;
import game_objects.Slime;
import game_objects.Spikes;
import game_objects.SwitchTrap;
import main.Handler;
import main.Main;

public class Tutorial4 extends GameStateAbstract{
	
	private GameObject player;
	private Slime slime;
	private Grass grass = new Grass();
	private ArrayList<Rock> rocks = new ArrayList<Rock>();
	private ArrayList<Spikes> spikes = new ArrayList<Spikes>();
	private SignExit signExit;
	private SignExit fakeExit;
	private Spikes fakeSpikes;
	private SwitchTrap switchTrap;
	private Font font = new Font("Monospaced", 1, 25);
	private boolean binded = false;

	public Tutorial4(Main main, Handler handler) {
		super(main, handler);
		
		constructRocksAndSpikes();
		switchTrap = new SwitchTrap(640, 60, ID.Switch, handler);
		signExit = new SignExit(1000, 100, ID.Exit, handler);
		fakeExit = new SignExit(740, Main.HEIGHT - 125, ID.Exit, handler);
		fakeSpikes = new Spikes(740, Main.HEIGHT - 125, ID.Inanimate, handler);
		player = Tutorial1.player;
//player = new Player(Main.WIDTH/10, Main.HEIGHT/2-46, ID.Player, handler);
//handler.addObject(player);
		slime = new Slime(640, 175, ID.Slime, handler);
	}

	@Override
	public void tick() {
		if (GameStateChooser.gameState == GAMESTATE.Tutorial4) { // transition to this game state
			for (int i = 0; i < rocks.size(); i++) {
				handler.addObject(rocks.get(i));
			}
			for (int i = 0; i < spikes.size(); i++) {
				handler.addObject(spikes.get(i));
			}
			handler.addObject(switchTrap);
			handler.addObject(slime);
		}
		
		
		if (player.getX() > fakeExit.getX() - 80 && player.getX() < fakeExit.getX() + 80 && 
				player.getY() > fakeExit.getY() - 125) {
			slime.setTriggered(true);
		}
		
		if (slime.getEscape()) {
			switchTrap.setSwitched(true);
			//TODO
		}
		
		if (player.getBounds().intersects(fakeSpikes.getBounds()) && !slime.getDead()) {
			this.binded = true;
		}
		
		if (slime.getDead()) {
			signExit.tick();
		}
		
		if (binded) { //fell into trap
			for (int i = 0; i < spikes.size(); i++) {
				handler.removeObject(spikes.get(i));
			}
			player.setX(fakeExit.getX());
			player.setY(fakeExit.getY() - 75);
		}
		
		handler.tick();
		
		if (Player.deathSignal) {
			resetGameState();
		}
		
		else if (signExit.getOnExit()) {
			signExit.setOnExit(false);
			clearHandler();
			GameStateChooser.gameState = GAMESTATE.TutorialBoss;
		}
		
	}

	@Override
	public void render(Graphics g) {
		grass.render(g);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Oh isn't it adorable ...", 200, 95);
		
		if (!slime.getEscape()) { //before switched
			fakeExit.render(g);
		}
		else { //after switched
			g.setColor(Color.RED);
			g.drawString("... and cunning ...", 200, 130);
			fakeSpikes.render(g);
			

			if (slime.getDead()) {
				signExit.render(g);
			}

		}
		
		handler.render(g);
		
	}

	@Override
	public void resetGameState() {
		player.setX(Main.WIDTH/10);
		player.setY(Main.HEIGHT/2-46);
		slime.setTriggered(false);
		slime.setDead(false);
		slime.setEscape(false);
		slime.setReversed(false);
		slime.setVelX(0);
		slime.setVelY(0);
		slime.setX(640);
		slime.setY(175);
		switchTrap.setSwitched(false);
		switchTrap.setTickCounter(0);
		this.binded = false;
		
		for (int i = 0; i < spikes.size(); i++) {
			handler.addObject(spikes.get(i));
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
	
	public void constructRocksAndSpikes() {
		//top wall rocks
		rocks.add(new Rock(565 + 60*0, 60, ID.Obstacle, handler));
		rocks.add(new Rock(565 + 60*1, 60, ID.Obstacle, handler));
		rocks.add(new Rock(565 + 60*2, 60, ID.Obstacle, handler));
		rocks.add(new Rock(565 + 60*3, 60, ID.Obstacle, handler));
		rocks.add(new Rock(565 + 60*4, 60, ID.Obstacle, handler));
		//left wall rocks
		rocks.add(new Rock(565, 60 + 30*1, ID.Obstacle, handler));
		rocks.add(new Rock(565, 60 + 30*2, ID.Obstacle, handler));
		rocks.add(new Rock(565, 60 + 30*3, ID.Obstacle, handler));
		rocks.add(new Rock(565, 60 + 30*4, ID.Obstacle, handler));
		//right wall rocks
		rocks.add(new Rock(565 + 60*4, 60 + 30*1, ID.Obstacle, handler));
		rocks.add(new Rock(565 + 60*4, 60 + 30*2, ID.Obstacle, handler));
		rocks.add(new Rock(565 + 60*4, 60 + 30*3, ID.Obstacle, handler));
		rocks.add(new Rock(565 + 60*4, 60 + 30*4, ID.Obstacle, handler));
		
		//bottom wall spikes
		spikes.add(new Spikes(565 + 10 + 70*1, 60 + 35*4 + 20, ID.Hazard, handler));
		spikes.add(new Spikes(565 + 20 + 70*2, 60 + 35*4 + 20, ID.Hazard, handler));

	}

}
