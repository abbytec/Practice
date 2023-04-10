public class MazeRunner {
  public static String walk(int[][] maze, String[] directions) {
    // here be dragons
    int status = 0, actual_x = 0, actual_y = 0, maze_size = maze.length;
    for (int y = 0; y < maze.length; y++) {
      for (int x = 0; x < maze[y].length; x++) {
        if (maze[y][x] == 2) {
          actual_x = x;
          actual_y = y;
        }
      }
    }

    for (int i = 0; i < directions.length; i++) {
      switch (directions[i]) {
        case "N":
          actual_y--;
          break;
        case "S":
          actual_y++;
          break;
        case "W":
          actual_x--;
          break;
        case "E":
          actual_x++;
          break;
      }
      if (status == 0) {
        if (actual_x == maze_size || actual_y == maze_size || actual_x < 0 || actual_y < 0
            || maze[actual_y][actual_x] == 1) {
          status = 1;
        } else if (maze[actual_y][actual_x] == 3) {
          status = 3;
        }
      }

    }
    switch (status) {
      default:
        return "Lost";
      case 1:
        return "Dead";
      case 3:
        return "Finish";
    }
  }

  public static void main(String[] args) {
    int[][] maze = {
        { 1, 1, 1, 1, 1, 1, 1 },
        { 1, 0, 0, 0, 0, 0, 3 },
        { 1, 0, 1, 0, 1, 0, 1 },
        { 0, 0, 1, 0, 0, 0, 1 },
        { 1, 0, 1, 0, 1, 0, 1 },
        { 1, 0, 0, 0, 0, 0, 1 },
        { 1, 2, 1, 0, 1, 0, 1 } };
    String[] directions = { "N", "N", "N", "N", "N", "E", "E", "E", "E", "E" };
    System.out.println(walk(maze, directions));
  }
}