package main;

import java.awt.Graphics;
import java.util.LinkedList;

import game_objects.GameObject;

public class Handler {
	
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject gameObject = objects.get(i);
			gameObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject gameObject = objects.get(i);
			gameObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		if (!this.objects.contains(object)) {
			this.objects.add(object);
		}
	}
	
	public void removeObject(GameObject object) {
		this.objects.remove(object);
	}
}
