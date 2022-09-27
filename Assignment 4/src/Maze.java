import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * 
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

  /** The maze */
  private TwoDimGrid maze;

  public Maze(TwoDimGrid m) {
    maze = m;
  }

  /** Wrapper method. */
  public boolean findMazePath() {
    return findMazePath(0, 0); // (0, 0) is the start point.
  }

  /**
   * Attempts to find a path through point (x, y).
   * 
   * @pre Possible path cells are in BACKGROUND color;
   *      barrier cells are in ABNORMAL color.
   * @post If a path is found, all cells on it are set to the
   *       PATH color; all cells that were visited but are
   *       not on the path are in the TEMPORARY color.
   * @param x The x-coordinate of current point
   * @param y The y-coordinate of current point
   * @return If a path through (x, y) is found, true;
   *         otherwise, false
   */
  public boolean findMazePath(int x, int y) {
    // COMPLETE HERE FOR PROBLEM 1
    int nRows = maze.getNRows() - 1;
    int nCols = maze.getNCols() - 1;

    if (x < 0 || y < 0 || x > nCols || y > nRows) {
      return false;
    }

    if (maze.getColor(x, y) != NON_BACKGROUND) {
      return false;
    }

    if (x == nCols && y == nRows) {
      maze.recolor(x, y, PATH);
      return true;
    }

    maze.recolor(x, y, PATH);

    if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y - 1) || findMazePath(x, y + 1)) {
      return true;
    }

    else {
      maze.recolor(x, y, TEMPORARY);
      return false;
    }
  }

  // ADD METHOD FOR PROBLEM 2 HERE
  public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {

    int nRows = maze.getNRows() - 1;
    int nCols = maze.getNCols() - 1;
    if (x < 0 || y < 0 || x > nCols || y > nRows) {
      throw new IllegalArgumentException();
    }

    ArrayList<ArrayList<PairInt>> result = new ArrayList<ArrayList<PairInt>>();
    Stack<PairInt> trace = new Stack<PairInt>();
    findMazePathStackBased(0, 0, result, trace);
    return result;
  }

  public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    trace.push(new PairInt(x, y));
    int nRows = maze.getNRows() - 1;
    int nCols = maze.getNCols() - 1;

    if (x < 0 || y < 0 || x > nCols || y > nRows) {
      trace.pop();
      return;
    }
    if (maze.getColor(x, y) != NON_BACKGROUND) {
      trace.pop();
      return;
    }
    if (x == nCols && y == nRows) {
      ArrayList<PairInt> temp = new ArrayList<PairInt>(trace);
      result.add(temp);
      trace.pop();
      maze.recolor(x, y, NON_BACKGROUND);
      return;
    }

    maze.recolor(x, y, PATH);

    findMazePathStackBased(x - 1, y, result, trace);
    findMazePathStackBased(x + 1, y, result, trace);
    findMazePathStackBased(x, y - 1, result, trace);
    findMazePathStackBased(x, y + 1, result, trace);

    trace.pop();
    maze.recolor(x, y, NON_BACKGROUND);

  }

  // ADD METHOD FOR PROBLEM 3 HERE
  public ArrayList<PairInt> findMazePathMin(int x, int y) {

    int nRows = maze.getNRows() - 1;
    int nCols = maze.getNCols() - 1;

    if (x < 0 || y < 0 || x > nCols || y > nRows) {
      throw new IllegalArgumentException();
    }

    ArrayList<ArrayList<PairInt>> final_result = findAllMazePaths(x, y);
    int min = final_result.get(0).size();
    ArrayList<PairInt> ans = final_result.get(0);
    for (int i = 1; i < final_result.size(); i++) {
      if (final_result.get(i).size() < min) {
        min = final_result.get(i).size();
        ans = final_result.get(i);
      }
    }

    return ans;

  }

  /* <exercise chapter="5" section="6" type="programming" number="2"> */
  public void resetTemp() {
    maze.recolor(TEMPORARY, BACKGROUND);
  }
  /* </exercise> */

  /* <exercise chapter="5" section="6" type="programming" number="3"> */
  public void restore() {
    resetTemp();
    maze.recolor(PATH, BACKGROUND);
    maze.recolor(NON_BACKGROUND, BACKGROUND);
  }
  /* </exercise> */
}
/* </listing> */
