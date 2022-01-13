package com.waitwathuh.gameoflife;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class GameOfLife implements Runnable {
	private static Map<String, Cell> gameMap;

	private int gameWidth;
	private int gameHeight;

	private Driver uiPanel;

	private boolean run = true;

	public GameOfLife(int gameWidth, int gameHeight, Driver uiPanel) {
		gameMap = buildMap(gameWidth, gameHeight);
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.uiPanel = uiPanel;
	}

	public void stop() {
		run = false;
	}

	@Override
	public void run() {
		while (run) {
			try {
				createNextItteration();
				uiPanel.repaint();
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Map<String, Cell> getGameMap() {
		return gameMap;
	}

	private Map<String, Cell> buildMap(int width, int height) {
		Random random = new Random();
		Map<String, Cell> gameMap = new HashMap<String, Cell>();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				gameMap.put(i + "," + j, new Cell(i, j, random.nextBoolean()));
			}
		}

		return gameMap;
	}

	private void createNextItteration() {
		for (Entry<String, Cell> entry : gameMap.entrySet()) {
			Cell gameCell = entry.getValue();

			int numberAliveNeighours = determineAliveNeighours(gameCell.getX(), gameCell.getY());
			boolean newState = determineNewState(gameCell.isAlive(), numberAliveNeighours);

			gameCell.setAlive(newState);
		}
	}

	private int determineAliveNeighours(int x, int y) {
		int alive = 0;

		// Check up
		if (y != 0) {
			Cell cellAbove = gameMap.get(x + "," + (y - 1));
			if (cellAbove.isAlive()) {
				alive++;
			}
		}

		// Check up+right
		if (y != 0 && x != gameWidth - 1) {
			Cell cellAboveRight = gameMap.get((x + 1) + "," + (y - 1));
			if (cellAboveRight.isAlive()) {
				alive++;
			}
		}

		// Check right
		if (x != gameWidth - 1) {
			Cell cellRight = gameMap.get((x + 1) + "," + y);
			if (cellRight.isAlive()) {
				alive++;
			}
		}

		// Check right+down
		if (x != gameWidth - 1 && y != gameHeight - 1) {
			Cell cellRightBellow = gameMap.get((x + 1) + "," + (y + 1));
			if (cellRightBellow.isAlive()) {
				alive++;
			}
		}

		// Check down
		if (y != gameHeight - 1) {
			Cell cellBellow = gameMap.get(x + "," + (y + 1));
			if (cellBellow.isAlive()) {
				alive++;
			}
		}

		// Check down+left
		if (y != gameHeight - 1 && x != 0) {
			Cell cellBellowLeft = gameMap.get((x - 1) + "," + (y + 1));
			if (cellBellowLeft.isAlive()) {
				alive++;
			}
		}

		// Check left
		if (x != 0) {
			Cell cellLeft = gameMap.get((x - 1) + "," + y);
			if (cellLeft.isAlive()) {
				alive++;
			}
		}

		// Check left+up
		if (x != 0 && y != 0) {
			Cell cellLeftAbove = gameMap.get((x - 1) + "," + (y - 1));
			if (cellLeftAbove.isAlive()) {
				alive++;
			}
		}

		return alive;
	}

	private boolean determineNewState(boolean oldState, int neighbourCount) {
		// Live cell, 2 OR 3 live neighbours survive
		// Dead cell, 3 life neighbours reanimate
		// All other live cells die, dead cells stay dead
		if (oldState && (neighbourCount == 2 || neighbourCount == 3)) {
			return true;
		} else if (!oldState && neighbourCount == 3) {
			return true;
		} else {
			return false;
		}
	}
}
