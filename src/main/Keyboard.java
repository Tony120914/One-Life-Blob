package main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game_objects.GameObject;
import game_objects.ID;
import game_objects.Player;
import sounds.SoundMusicPlayer;
import states.STATE;

public class Keyboard extends KeyAdapter{
	
	protected Handler handler;
	public static boolean[] keyPressed = new boolean[4];
	private double playerSpeed = 2; //default 2
	
	public Keyboard(Handler hanlder) {
		this.handler = hanlder;
		
		for (int i = 0; i < keyPressed.length; i++) {
			keyPressed[i] = false;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject gameObject = handler.objects.get(i);
			if (gameObject.getId() == ID.Player) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					gameObject.setVelY(-playerSpeed);
					keyPressed[0] = true;
				}
				else if (e.getKeyCode() == KeyEvent.VK_A) {
					gameObject.setVelX(-playerSpeed);
					keyPressed[1] = true;
					Player.faceLeft = true;
				}
				else if (e.getKeyCode() == KeyEvent.VK_S) {
					gameObject.setVelY(+playerSpeed);
					keyPressed[2] = true;
				}
				else if (e.getKeyCode() == KeyEvent.VK_D) {
					gameObject.setVelX(+playerSpeed);
					keyPressed[3] = true;
					Player.faceLeft = false;
				}
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//			SoundMusicPlayer.getSound("back").play();
			SoundMusicPlayer.clips.get("back").start();
			SoundMusicPlayer.clips.get("back").setMicrosecondPosition(0);
			Main.state = STATE.Menu;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject gameObject = handler.objects.get(i);
			if (gameObject.getId() == ID.Player) {
				//key events
				if (e.getKeyCode() == KeyEvent.VK_W) {
					keyPressed[0] = false;
					if (keyPressed[2]) {
						gameObject.setVelY(+playerSpeed);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					keyPressed[1] = false;
					if (keyPressed[3]) {
						gameObject.setVelX(+playerSpeed);
						Player.faceLeft = false;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					keyPressed[2] = false;
					if (keyPressed[0]) {
						gameObject.setVelY(-playerSpeed);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					keyPressed[3] = false;
					if (keyPressed[1]) {
						gameObject.setVelX(-playerSpeed);
						Player.faceLeft = true;
					}
				}
				
				if (!keyPressed[0] && !keyPressed[2]) gameObject.setVelY(0);
				if (!keyPressed[1] && !keyPressed[3]) gameObject.setVelX(0);
			}
		}
	}
}
