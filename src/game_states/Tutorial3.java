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
import game_objects.Fly;
import main.Handler;
import main.Main;

public class Tutorial3 extends GameStateAbstract{
	
	private GameObject player;
	private ArrayList<Fly> flies = new ArrayList<Fly>();
	private Grass grass = new Grass();
	private SignExit signExit;
	private Font font = new Font("Monospaced", 1, 25);

	public Tutorial3(Main main, Handler handler) {
		super(main, handler);
		
		signExit = new SignExit(Main.WIDTH - 185, Main.HEIGHT - 125, ID.Exit, handler);
		player = Tutorial1.player;
//player = new Player(Main.WIDTH/10, Main.HEIGHT/2-46, ID.Player, handler);
//handler.addObject(player);

		flies.add(new Fly(Main.WIDTH/2 + Math.random()*Main.WIDTH/2 - 100, Math.random()*Main.HEIGHT - 100, ID.Fly, handler));
		flies.add(new Fly(Main.WIDTH/2 + Math.random()*Main.WIDTH/2 - 100, Math.random()*Main.HEIGHT - 100, ID.Fly, handler));
		flies.add(new Fly(Main.WIDTH/2 + Math.random()*Main.WIDTH/2 - 100, Math.random()*Main.HEIGHT - 100, ID.Fly, handler));
		flies.add(new Fly(Main.WIDTH/2 + Math.random()*Main.WIDTH/2 - 100, Math.random()*Main.HEIGHT - 100, ID.Fly, handler));
		flies.add(new Fly(Main.WIDTH/2 + Math.random()*Main.WIDTH/2 - 100, Math.random()*Main.HEIGHT - 100, ID.Fly, handler));
		flies.add(new Fly(Main.WIDTH/2 + Math.random()*Main.WIDTH/2 - 100, Math.random()*Main.HEIGHT - 100, ID.Fly, handler));
		flies.add(new Fly(Main.WIDTH/2 + Math.random()*Main.WIDTH/2 - 100, Math.random()*Main.HEIGHT - 100, ID.Fly, handler));
		flies.add(new Fly(Main.WIDTH/2 + Math.random()*Main.WIDTH/2 - 100, Math.random()*Main.HEIGHT - 100, ID.Fly, handler));
		flies.add(new Fly(Main.WIDTH/2 + Math.random()*Main.WIDTH/2 - 100, Math.random()*Main.HEIGHT - 100, ID.Fly, handler));
		flies.add(new Fly(Main.WIDTH/2 + Math.random()*Main.WIDTH/2 - 100, Math.random()*Main.HEIGHT - 100, ID.Fly, handler));
		
	}

	@Override
	public void tick() {
		if (GameStateChooser.gameState == GAMESTATE.Tutorial3) { // transition to this game state
			for (int i = 0; i < flies.size(); i++) {
				handler.addObject(flies.get(i));
			}
			handler.addObject(signExit);
		}
		
		handler.tick();
		if (Player.deathSignal) {
			resetGameState();
		}
		
		else if (signExit.getOnExit()) {
			signExit.setOnExit(false);
			clearHandler();
			GameStateChooser.gameState = GAMESTATE.Tutorial4;
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		grass.render(g);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Do they bite?", 200, 100);
		
		signExit.render(g);
		
		handler.render(g);
	}

	@Override
	public void resetGameState() {
		player.setX(Main.WIDTH/10);
		player.setY(Main.HEIGHT/2-46);
		for (int i = 0; i < flies.size(); i++) {
			int temp = (int) Math.round(Math.random());
			if (temp == 0) temp = -1;
			else temp = 1;
			flies.get(i).setVelX(flies.get(i).getVelX() * temp);
			flies.get(i).setVelY(flies.get(i).getVelY() * temp);
			flies.get(i).setX(Main.WIDTH/2 + Math.random()*Main.WIDTH/2 - 100);
			flies.get(i).setY(Math.random()*Main.HEIGHT - 100);
			
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

}
