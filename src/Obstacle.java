import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Obstacle extends JPanel {
    private final int width;
    private final int height;
    private final char[][] maze;
    private final Random rand = new Random();
    private final int cellSize = 32;

    private final Image backgroundImage;
    private final Image wallImage;

   
    public Obstacle(int width, int height, String backgroundFile, String wallFile) {
        this.width = width;
        this.height = height;
        this.maze = new char[height][width];

        generateMaze();

        backgroundImage = loadImage(backgroundFile);
        wallImage = loadImage(wallFile);

        setPreferredSize(new Dimension(width * cellSize, height * cellSize));
        setBackground(new Color(200, 180, 255));
        setOpaque(true);
    }

    private Image loadImage(String filename) {
        ImageIcon icon = new ImageIcon(filename);
        return (icon.getIconWidth() == -1) ? null : icon.getImage();
    }

    private void generateMaze() {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                maze[y][x] = '#';

        carvePath(1, 1);

        // Start and end points
        maze[1][0] = 'S';
        maze[height - 2][width - 1] = 'E';
    }

    private void carvePath(int x, int y) {
        maze[y][x] = ' ';
        int[] dirs = {0, 1, 2, 3};
        
        for (int i = dirs.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int tmp = dirs[i];
            dirs[i] = dirs[j];
            dirs[j] = tmp;
        }

        for (int dir : dirs) {
            int dx = 0, dy = 0;
            switch (dir) {
                case 0 -> dx = 1;
                case 1 -> dx = -1;
                case 2 -> dy = 1;
                case 3 -> dy = -1;
            }

            int x2 = x + dx * 2;
            int y2 = y + dy * 2;
            if (x2 > 0 && x2 < width - 1 && y2 > 0 && y2 < height - 1 && maze[y2][x2] == '#') {
                maze[y + dy][x + dx] = ' ';
                carvePath(x2, y2);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw background
        if (backgroundImage != null)
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Draw cells
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int px = x * cellSize;
                int py = y * cellSize;
                char c = maze[y][x];

                if (c == '#') {
                    if (wallImage != null)
                        g2.drawImage(wallImage, px, py, cellSize, cellSize, this);
                    else {
                        g2.setColor(Color.GRAY);
                        g2.fillRect(px, py, cellSize, cellSize);
                    }
                } else if (c == 'S') {
                    g2.setColor(Color.GREEN);
                    g2.fillOval(px + 8, py + 8, 16, 16);
                } else if (c == 'E') {
                    g2.setColor(Color.RED);
                    g2.fillOval(px + 8, py + 8, 16, 16);
                }
            }
        }
    }

    
    public char[][] getMaze() {
        return maze;
    }
}

