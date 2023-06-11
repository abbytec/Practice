public class Multiplication {
  public static int[][] multiplicationTable(int n) {
    int[][] matrix = new int[n][n];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        matrix[i - 1][j - 1] = i * j;
      }
    }
    return matrix;
  }

  public static void main(String[] args) {
    multiplicationTable(3);
  }
}