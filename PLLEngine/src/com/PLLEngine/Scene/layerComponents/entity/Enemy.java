package com.PLLEngine.Scene.layerComponents.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.PLLEngine.collision.CollThread;

public class Enemy extends Entitie {
	private static boolean richtungAll;
	private boolean richtungOwn, once;
	private BufferedImage sprite;
	private int x, y;
	private int width = 20;
	private int height = 20;

	public Enemy(int startX, int startY) {
		x = startX;
		y = startY;
		System.out.println(px);
		if (!richtungAll) {
			richtungAll = true;
			richtungOwn = true;
			once = true;
		} else if (richtungAll) {
			richtungAll = false;
			once = true;
		}

	}

	@Override
	public void draw(Graphics2D g) {
		// Initialize kamera Movement first or there could be problems
		//siehe player -> cam movment
		//ich w�rd das ganze dann aber von der world aus steuern 
		cameraMovement(x, y, dx, dy);
		synchronize();
		setEntitiyNumber();
		if(sprite != null)
		g.drawImage(sprite, px, py, null);
		g.drawRect(px, py, width, height);

	}

	private void enemyMovement() {

		if (richtungOwn) {
			x++;
		} else {
			x--;
		}
	}

	private void synchronize() {
		if (Entitie.synchronize[entityNumberOwn]) {
			Entitie.synchronize[entityNumberOwn] = false;
			if (CollThread.collLeft[entityNumberOwn]) {

			} else if (CollThread.collRight[entityNumberOwn]) {

			} else {
				enemyMovement();
			}

		}
	}

	private void setEntitiyNumber() {
		arrX[entityNumberOwn] = px;
		arrY[entityNumberOwn] = py;
	}
	public void setSprite(BufferedImage br) {
		this.sprite = br;
	}


}
