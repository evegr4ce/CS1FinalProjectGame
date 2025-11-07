import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Obstacle extends JPanel {

    private final int width;
    private final int height;
    private final char[][] maze;
    private final Random rand = new Random();

    private Image rockImage;
    private Image treeImage;

    private final int cellSize = 32;

    public Obstacle(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new char[height][width];
        generateObstacle();

        // Load images
        rockImage = loadImage("desert_rock1.png");
        treeImage = loadImage("birch_1.png");

        setPreferredSize(new Dimension(width * cellSize, height * cellSize));
    }

    private Image loadImage(String filename) {
        ImageIcon icon = new ImageIcon(filename);
        if (icon.getIconWidth() == -1) {
            System.out.println(" Could not load image: " + filename);
            return null;
        }
        return icon.getImage();
    }

    private void generateObstacle() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                maze[y][x] = '#';
            }
        }

        carvePath(1, 1);

        maze[1][0] = 'S';
        maze[height - 2][width - 1] = 'E';
    }

    private void carvePath(int x, int y) {
        maze[y][x] = ' ';
        int[] directions = {0, 1, 2, 3};
        shuffle(directions);

        for (int dir : directions) {
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

    private void shuffle(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 🌿 Background
        g2.setColor(new Color(60, 160, 80));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // 🟨 Draw raised 3D paths
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char c = maze[y][x];
                int px = x * cellSize;
                int py = y * cellSize;

                if (c == ' ') {
                    // Shadow (bottom/right edge)
                    g2.setColor(new Color(170, 150, 70)); // darker shade
                    g2.fillRect(px + 3, py + 3, cellSize, cellSize);

                    // Highlighted top
                    GradientPaint gp = new GradientPaint(px, py,
                            new Color(255, 250, 180),
                            px + cellSize, py + cellSize,
                            new Color(220, 200, 100));
                    g2.setPaint(gp);
                    g2.fillRoundRect(px, py, cellSize - 2, cellSize - 2, 6, 6);
                } else if (c == 'S') {
                    g2.setColor(Color.GREEN);
                    g2.fillOval(px + 8, py + 8, 16, 16);
                } else if (c == 'E') {
                    g2.setColor(Color.RED);
                    g2.fillOval(px + 8, py + 8, 16, 16);
                }
            }
        }

        // 🌲 3 trees + 3 rocks in corner
        int startX = (width - 6) * cellSize;
        int startY = (height - 2) * cellSize;

        for (int i = 0; i < 3; i++) {
            if (rockImage != null)
                g2.drawImage(rockImage, startX + (i * cellSize), startY, cellSize, cellSize, this);
        }

        for (int i = 0; i < 3; i++) {
            if (treeImage != null)
                g2.drawImage(treeImage, startX + (i * cellSize), startY - cellSize, cellSize, cellSize, this);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze with 3D Paths");
        Obstacle mazePanel = new Obstacle(21, 15);

        frame.add(mazePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

