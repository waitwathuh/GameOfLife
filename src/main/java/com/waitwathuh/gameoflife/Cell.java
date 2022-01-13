package com.waitwathuh.gameoflife;

import java.awt.image.BufferedImage;

public class Cell {
	private int x;
	private int y;
	private boolean isAlive;
	private BufferedImage image;

	public Cell (int x, int y, boolean isAlive) {
		this.x = x;
		this.y = y;
		this.isAlive = isAlive;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
		image = isAlive ? CellImage.getAliveImage() : CellImage.getDeadImage() ;
	}

	public BufferedImage getImage() {
		return image;
	}
}
