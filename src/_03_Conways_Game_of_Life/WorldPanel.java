package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;

	private Timer timer;

	// 1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] manycells;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		// 2. Calculate the cell size.
		cellSize = w / cellsPerRow;
		// 3. Initialize the cell array to the appropriate size.
		manycells = new Cell[cpr][cpr];
		// 3. Iterate through the array and initialize each cell.
		// Don't forget to consider the cell's dimensions when
		// passing in the location.
		for (int i = 0; i < manycells.length; i++) {
			for (int j = 0; j < manycells[i].length; j++) {
				int xvalue = i;
				int yvalue = j;
				manycells[i][j] = new Cell(xvalue, yvalue, cellSize);
			}
		}
	}

	public void randomizeCells() {
		// 4. Iterate through each cell and randomly set each
		// cell's isAlive memeber to true of false
		for (int i = 0; i < manycells.length; i++) {
			for (int j = 0; j < manycells[i].length; j++) {
				Random rand = new Random();
				manycells[i][j].isAlive = rand.nextBoolean();
			}
		}
		repaint();
	}

	public void clearCells() {
		// 5. Iterated through the cells and set them all to dead.
		for (int i = 0; i < manycells.length; i++) {
			for (int j = 0; j < manycells[i].length; j++) {
				manycells[i][j].isAlive = false;
			}
		}
		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		// 6. Iterate through the cells and draw them all
		for (int i = 0; i < manycells.length; i++) {
			for (int j = 0; j < manycells[i].length; j++) {
				manycells[i][j].draw(g);
			}
		}
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	// advances world one step
	public void step() {
		int[][] livingNeighbors = new int[manycells.length][manycells[0].length];
		// 7. iterate through cells and get their neighbors
		for (int i = 0; i < manycells.length; i++) {
			for (int j = 0; j < manycells[i].length; j++) {
				livingNeighbors[i][j] = getLivingNeighbors(i, j);

			}
		}
		// 8. check if each cell should live or die
		for (int k = 0; k < manycells.length; k++) {

			for (int k2 = 0; k2 < manycells[k].length; k2++) {
				manycells[k][k2].liveOrDie(livingNeighbors[k][k2]);
			}
		}

		// done above - will be completed in Cell Class

		repaint();
	}

	// 9. Complete the method.
	// It returns an array list of the 8 or less neighbors of the
	// cell identified by x and y
	public int getLivingNeighbors(int x, int y) {
		int livingneighbors = 0;
		if (x > 0) {
			if (manycells[x - 1][y].isAlive) {
				livingneighbors += 1;
			}
		}
		if (x <= cellsPerRow - 2) {
			if (manycells[x + 1][y].isAlive) {
				livingneighbors += 1;
			}
		}
		if (y > 0) {
			if (manycells[x][y - 1].isAlive) {
				livingneighbors += 1;
			}
		}
		if (y <= cellsPerRow - 2) {
			if (manycells[x][y + 1].isAlive) {
				livingneighbors += 1;
			}
		}
		if (y > 0 && x > 0) {
			if (manycells[x - 1][y - 1].isAlive) {
				livingneighbors += 1;
			}
		}
		if (y <= cellsPerRow - 2 && x > 0) {
			if (manycells[x - 1][y + 1].isAlive) {
				livingneighbors += 1;
			}
		}
		if (y > 0 && x <= cellsPerRow - 2) {
			if (manycells[x + 1][y - 1].isAlive) {
				livingneighbors += 1;
			}
		}
		if (y <= cellsPerRow - 2 && x <= cellsPerRow - 2) {
			if (manycells[x + 1][y + 1].isAlive) {
				livingneighbors += 1;
			}
		} else {
		}
		return livingneighbors;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 10. Use e.getX() and e.getY() to determine
		// which cell is clicked. Then toggle
		// the isAlive variable for that cell.
		int locx = e.getX() / cellSize;
		int locy = e.getY() / cellSize;
		manycells[locx][locy].isAlive = !manycells[locx][locy].isAlive;

		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
