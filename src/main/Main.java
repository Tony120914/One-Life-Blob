// Based off of learning from RealTutsGML on YouTube
package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.sound.sampled.Clip;

import UI.HUD;
import game_states.GameStateChooser;
import sounds.SoundMusicPlayer;
import states.Credits;
import states.Instructions;
import states.Menu;
import states.STATE;

public class Main extends Canvas implements Runnable{
	
	public static final int WIDTH = 1280, HEIGHT = 720;
	
	private static final long serialVersionUID = 6181612111777646203L;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Handler handler = new Handler();
	
	private HUD hud;
	
	private Menu menu;
	private Instructions instructions;
	private Credits credits;
	
	private GameStateChooser GSC = new GameStateChooser(this, this.handler);
	
	public static STATE state = STATE.Menu;
	
	public Main() {
		initializeBasic();
		initializeSoundMusic();
		initializeGame();
		initializeListeners();
		new Window(WIDTH, HEIGHT, "One-Life Blob", this);
	}
	
	public void initializeBasic() {
		menu = new Menu(this, handler);
		instructions = new Instructions(this, handler);
		credits = new Credits(this, handler);
	}
	
	public void initializeGame() {
		hud = new HUD();
	}
	
	public void initializeListeners() {
		this.addKeyListener(new Keyboard(this.handler));
		this.addMouseListener(menu);
		this.addMouseListener(instructions);
		this.addMouseListener(credits);
	
		this.addMouseListener(GSC); /* TEMPORARY */
	}
	
	public void initializeSoundMusic() {
		SoundMusicPlayer.loadSoundMusic();
		//SoundMusicPlayer.getMusic("background").loop(1, (float) 0.25);
		SoundMusicPlayer.clips.get("background").loop(Clip.LOOP_CONTINUOUSLY);
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
//		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
//			frames++;

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
//				System.out.println("FPS: "+ frames);
//				frames = 0;
			}
		}
		stop();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void tick() {
		if (state == STATE.Game) {
			GSC.tick();
			hud.tick();
		}
		else if (state == STATE.Menu) {
			menu.tick();
		}
		else if (state == STATE.Instructions) {
			instructions.tick();
		}
		else if (state == STATE.Credits) {
			credits.tick();
		}
	}
	
	private void render() {
		bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (state == STATE.Game) {
			GSC.render(g);
			hud.render(g);
		}
		else if (state == STATE.Menu) {
			menu.render(g);
		}
		else if (state == STATE.Instructions) {
			instructions.render(g);
		}
		else if (state == STATE.Credits) {
			credits.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	
	public static double clamp(double x, int min, int max) {
		if (x >= max) return x = max;
		else if (x <= min) return x = min;
		else return x;
	}
	
	public boolean clicked(int mouseX, int mouseY, int x, int y, int width, int height) {
		if (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height) {
			return true;
		}
		else return false;
	}


	public static void main(String[] args) {
		new Main();
	}

}
