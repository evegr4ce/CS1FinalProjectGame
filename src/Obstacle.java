import java.util.Random;
import java.awt.*; 

public class Obstacle { //hi

    private final int width;
    private final int height;
    private final char[][] maze;
    private final Random rand = new Random();

    public Obstacle(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new char[height][width];
        generateObstacle();
    }

    private void generateObstacle() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                maze[y][x] = '#';
            }
        }

        
        carvePath(1, 1);
    }

    private void carvePath(int x, int y) {
        maze[y][x] = ' ';
        int[] directions = {0, 1, 2, 3};
        shuffle(directions);

        for (int dir : directions) {
            int dx = 0, dy = 0;
            
            switch (dir) {
                case 0 -> dx = 1;   // Right
                case 1 -> dx = -1;  // Left
                case 2 -> dy = 1;   // Down
                case 3 -> dy = -1;  // Up
            }

            int x2 = x + dx * 2;
            int y2 = y + dy * 2;

            if (x2 > 0 && x2 < width - 1 && y2 > 0 && y2 < height - 1 && maze[y2][x2] == '#') {
                maze[y + dy][x + dx] = ' ';
                carvePath(x2, y2);
            }
        }
    }

    private void shuffle(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public void printMaze() {
        maze[1][0] = 'S'; // Start
        maze[height - 2][width - 1] = 'E'; // End
        for (char[] row : maze) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
    	Obstacle m = new Obstacle (21, 15); 
        m.printMaze();
    }
}

