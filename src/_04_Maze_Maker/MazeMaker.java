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
		selectNextPath(maze.manycells[xposition][yposition]);
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.hasBeenVisited();
		// B. check for unvisited neighbors using the cell
		int numofneighbors = getUnvisitedNeighbors(currentCell).size();
		// C. if has unvisited neighbors,
		if (numofneighbors > 0) {
			// C1. select one at random.
			int xposition = randGen.nextInt(width);
			int yposition = randGen.nextInt(height);
			// C2. push it to the stack
			uncheckedCells.push(maze.manycells[xposition][yposition]);
			// C3. remove the wall between the two cells
			removeWalls(currentCell, maze.manycells[xposition][yposition]);
			// C4. make the new cell the current cell and mark it as visited
			maze.manycells[xposition][yposition] = currentCell;
			maze.manycells[xposition][yposition].hasBeenVisited();
		}
		// D. if all neighbors are visited
		if (numofneighbors == 0) {
			// D1. if the stack is not empty
			if (uncheckedCells.isEmpty() == false) {
				// D1a. pop a cell from the stack
				Cell stackcell = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = stackcell;
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
			if (yp2 - 4 > 0) {
				if (yp1 == yp2 - 4) {
					c1.setNorthWall(false);
					c2.setSouthWall(false);
				}
			}
			if (yp2 + 4 < height) {
				if (yp1 == yp2 + 4) {
					c2.setNorthWall(false);
					c1.setSouthWall(false);
				}
			}
		}
		if (yp1 == yp2) {
			if (xp2 - 4 > 0) {
				if (xp1 == xp2 - 4) {
					// west wall
					c2.setEastWall(false);
					c1.setWestWall(false);

				}
			}
			if (xp2 + 4 < width) {
				if (xp1 == xp2 + 4) {
					// east wall
					c1.setEastWall(false);
					c2.setWestWall(false);
				}
			}
		} else {

		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> bunchofcells = new ArrayList<Cell>();
		int xp1 = c.getX();
		int yp1 = c.getY();
		if (yp1 - 4 > 0) {
			if (maze.manycells[xp1][yp1 - 4].hasBeenVisited() == false) {
				// up
				bunchofcells.add(maze.manycells[xp1][yp1 - 4]);
			}
		}
		if (yp1 + 4 < height) {
			if (maze.manycells[xp1][yp1 + 4].hasBeenVisited() == false) {
				// down
				bunchofcells.add(maze.manycells[xp1][yp1 + 4]);
			}
		}
		if (xp1 - 4 > 0) {
			if (maze.manycells[xp1 - 4][yp1].hasBeenVisited() == false) {
				// left
				bunchofcells.add(maze.manycells[xp1 - 4][yp1]);
			}
		}
		if (xp1 + 4 < width) {
			if (maze.manycells[xp1 + 4][yp1].hasBeenVisited() == false) {
				// right
				bunchofcells.add(maze.manycells[xp1 + 4][yp1]);
			}
		} else {

		}
		return bunchofcells;
	}
}