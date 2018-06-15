package game_states;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Handler;
import main.Main;
import states.STATE;

public class GameStateChooser extends MouseAdapter{ /* TEMPORARY EXTEND */
	
	private Main main;
	private Handler handler;
	private Tutorial1 tutorial1;
	private Tutorial2 tutorial2;
	private Tutorial3 tutorial3;
	private Tutorial4 tutorial4;
	private TutorialBoss tutorialBoss;
	
	public static GAMESTATE gameState = GAMESTATE.Tutorial1; //USE THIS TO TEST GAME STATE
	
	public GameStateChooser(Main main, Handler handler) {
		this.main = main;
		this.handler = handler;

		tutorial1 = new Tutorial1(main, handler);
		tutorial2 = new Tutorial2(main, handler);
		tutorial3 = new Tutorial3(main, handler);
		tutorial4 = new Tutorial4(main, handler);
		tutorialBoss = new TutorialBoss(main, handler);
		 
	}
	
	public void tick() {
		if (gameState == GAMESTATE.Tutorial1) {
			tutorial1.tick();
		}
		else if (gameState == GAMESTATE.Tutorial2) {
			tutorial2.tick();
		}
		else if (gameState == GAMESTATE.Tutorial3) {
			tutorial3.tick();
		}
		else if (gameState == GAMESTATE.Tutorial4) {
			tutorial4.tick();
		}
		else if (gameState == GAMESTATE.TutorialBoss) {
			tutorialBoss.tick();
		}
	}
	
	public void render(Graphics g) {
		if (gameState == GAMESTATE.Tutorial1) {
			tutorial1.render(g);
		}
		else if (gameState == GAMESTATE.Tutorial2) {
			tutorial2.render(g);
		}
		else if (gameState == GAMESTATE.Tutorial3) {
			tutorial3.render(g);
		}
		else if (gameState == GAMESTATE.Tutorial4) {
			tutorial4.render(g);
		}
		else if (gameState == GAMESTATE.TutorialBoss) {
			tutorialBoss.render(g);
		}
		
	}
	
	/*
	 * TEMPORARY TO CHECK LOCATION
	 */
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX() + ", " + e.getY());
		System.out.println(handler.objects);
	}
}
