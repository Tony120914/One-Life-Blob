package game_states;

import java.awt.Graphics;

import main.Handler;
import main.Main;

public abstract class GameStateAbstract {
	protected Main main;
	protected Handler handler;
	
	public GameStateAbstract(Main main, Handler handler) {
		this.main = main;
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void resetGameState();
	public abstract void clearHandler();

}
