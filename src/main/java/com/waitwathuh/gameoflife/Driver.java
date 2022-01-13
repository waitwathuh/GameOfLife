package com.waitwathuh.gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Driver extends JPanel {
	private static final int GAMEWIDTH = 40;
	private static final int GAMEHEIGHT = 40;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver uiPanel = new Driver();
					GameOfLife gameOfLife = new GameOfLife(GAMEWIDTH, GAMEHEIGHT, uiPanel);

					JFrame jframe = new JFrame();
					jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					jframe.setSize(400, 422);
					jframe.getContentPane().add(uiPanel, BorderLayout.CENTER);
					jframe.setVisible(true);

					Thread gameThread = new Thread(gameOfLife);
					gameThread.setName("GameThread");
					gameThread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Entry<String, Cell> entry : GameOfLife.getGameMap().entrySet()) {
			Cell gameCell = entry.getValue();

			BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
			Graphics imageGraphics = image.getGraphics();
			imageGraphics.setColor(gameCell.isAlive() ? Color.BLACK : Color.WHITE);
			imageGraphics.fillRect(0, 0, 10, 10);

			g.drawImage(image, gameCell.getX() * 10, gameCell.getY() * 10, null);
		}
	}
}
