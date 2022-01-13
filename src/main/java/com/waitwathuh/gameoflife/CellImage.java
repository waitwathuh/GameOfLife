package com.waitwathuh.gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CellImage {
	private static final BufferedImage aliveImage = buildAliveImage();
	private static final BufferedImage deadImage = buildDeadImage();

	static BufferedImage buildAliveImage() {
		BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		Graphics imageGraphics = image.getGraphics();
		imageGraphics.setColor(Color.BLACK);
		imageGraphics.fillRect(0, 0, 10, 10);
		return image;
	}

	static BufferedImage buildDeadImage() {
		BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		Graphics imageGraphics = image.getGraphics();
		imageGraphics.setColor(Color.WHITE);
		imageGraphics.fillRect(0, 0, 10, 10);
		return image;
	}

	public static BufferedImage getAliveImage() {
		return aliveImage;
	}

	public static BufferedImage getDeadImage() {
		return deadImage;
	}
}
