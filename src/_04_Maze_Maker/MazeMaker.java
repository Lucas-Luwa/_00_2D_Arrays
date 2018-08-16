package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {
	private static int width;
	private static int height;
	private static Maze maze;
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);
		// 4. select a random cell to start
		int xposition = randGen.nextInt(w);
		int yposition = randGen.nextInt(h);
		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(xposition, yposition));
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> getneighbors = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (getneighbors.size() > 0) {
			// C1. select one at random.
			int randomcell = randGen.nextInt(getneighbors.size());
			Cell randneighbor = getneighbors.get(randomcell);
			// C2. push it to the stack
			uncheckedCells.push(randneighbor);
			// C3. remove the wall between the two cells
			removeWalls(currentCell, randneighbor);
			// C4. make the new cell the current cell and mark it as visited
			currentCell = randneighbor;
			currentCell.setBeenVisited(true);
			selectNextPath(currentCell);
		}
		// D. if all neighbors are visited
		else {
			// D1. if the stack is not empty
			if (uncheckedCells.isEmpty() == false) {
				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
				// D1b. make that the current cell
				// recall method
				selectNextPath(currentCell);
			}
		}

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		int xp1 = c1.getX();
		int yp1 = c1.getY();
		int xp2 = c2.getX();
		int yp2 = c2.getY();
		if (xp1 == xp2) {
			// north and south wall
			if (yp1 > yp2) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			} else {
				c2.setNorthWall(false);
				c1.setSouthWall(false);
			}
		}
		if (yp1 == yp2) {
			if (xp1 > xp2) {
				// west wall
				c2.setEastWall(false);
				c1.setWestWall(false);
			} else {
				// east wall
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> bunchofcells = new ArrayList<Cell>();
		int xp1 = c.getX();
		int yp1 = c.getY();
		if (yp1 + 1 < height) {
			if (maze.getCell(xp1, yp1 + 1).hasBeenVisited() == false) {
				// down
				bunchofcells.add(maze.getCell(xp1, yp1 + 1));
			}
		}
		if (yp1 - 1 >= 0) {
			if (maze.getCell(xp1, yp1 - 1).hasBeenVisited() == false) {
				// up
				bunchofcells.add(maze.getCell(xp1, yp1 - 1));
			}
		}
		if (xp1 + 1 < width) {
			if (maze.getCell(xp1 + 1, yp1).hasBeenVisited() == false) {
				// right
				bunchofcells.add(maze.getCell(xp1 + 1, yp1));
			}
		}
		if (xp1 - 1 >= 0) {
			if (maze.getCell(xp1 - 1, yp1).hasBeenVisited() == false) {
				// left
				bunchofcells.add(maze.getCell(xp1 - 1, yp1));
			}
		} else {
		}
		return bunchofcells;
	}
}