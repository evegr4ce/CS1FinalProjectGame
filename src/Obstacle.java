import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Obstacle extends JPanel {

    private int width;
    private int height;
    private char[][] maze;
    private final Random rand = new Random();

    private Image backgroundImage;
    private Image wallImage;

    private int cellSize = 32;

    // Player position
    private int playerX = 1;
    private int playerY = 1;

    public Obstacle(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new char[height][width];
        generateObstacle();

       
        backgroundImage = loadImage("grass.png");
        wallImage = loadImage("walls.png");

        setPreferredSize(new Dimension(width * cellSize, height * cellSize));
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                movePlayer(e.getKeyCode());
            }
        });
    }

    private Image loadImage(String filename) {
        ImageIcon icon = new ImageIcon(filename);
        if (icon.getIconWidth() == -1) {
            System.out.println("Could not load image: " + filename);
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

    private void movePlayer(int keyCode) {
        int newX = playerX;
        int newY = playerY;

        switch (keyCode) {
            case KeyEvent.VK_UP -> newY--;
            case KeyEvent.VK_DOWN -> newY++;
            case KeyEvent.VK_LEFT -> newX--;
            case KeyEvent.VK_RIGHT -> newX++;
        }

        if (newX >= 0 && newX < width && newY >= 0 && newY < height && maze[newY][newX] != '#') {
            playerX = newX;
            playerY = newY;
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background tiled
        if (backgroundImage != null) {
            int imgWidth = backgroundImage.getWidth(this);
            int imgHeight = backgroundImage.getHeight(this);
            for (int y = 0; y < getHeight(); y += imgHeight) {
                for (int x = 0; x < getWidth(); x += imgWidth) {
                    g2.drawImage(backgroundImage, x, y, this);
                }
            }
        } else {
            g2.setColor(new Color(60, 160, 80));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        // Draw maze grid
        int mazeWidth = width * cellSize;
        int mazeHeight = height * cellSize;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int px = x * cellSize;
                int py = y * cellSize;
                char c = maze[y][x];

                boolean isBorderCell = (y == 0 || y == height - 1 || x == 0 || x == width - 1);

                if (isBorderCell && wallImage != null) {
                    int smallerHeight = (int) (cellSize * 0.8);
                    if (y == 0)
                        g2.drawImage(wallImage, px, py + (cellSize - smallerHeight), cellSize, smallerHeight, this);
                    else if (y == height - 1)
                        g2.drawImage(wallImage, px, py, cellSize, smallerHeight, this);
                    else
                        g2.drawImage(wallImage, px, py, cellSize, cellSize, this);
                    continue;
                }

                if (c == ' ') {
                    g2.setColor(new Color(255, 255, 200));
                    g2.fillRect(px, py, cellSize, cellSize);
                } else if (c == 'S') {
                    g2.setColor(Color.GREEN);
                    g2.fillOval(px + cellSize / 4, py + cellSize / 4, cellSize / 2, cellSize / 2);
                } else if (c == 'E') {
                    g2.setColor(Color.RED);
                    g2.fillOval(px + cellSize / 4, py + cellSize / 4, cellSize / 2, cellSize / 2);
                }
            }
        }

        // Draw player
        g2.setColor(Color.BLUE);
        g2.fillOval(playerX * cellSize + cellSize / 4, playerY * cellSize + cellSize / 4, cellSize / 2, cellSize / 2);

        // Grid overlay
        g2.setColor(new Color(0, 0, 0, 60));
        for (int x = 0; x <= width; x++) {
            g2.drawLine(x * cellSize, 0, x * cellSize, mazeHeight);
        }
        for (int y = 0; y <= height; y++) {
            g2.drawLine(0, y * cellSize, mazeWidth, y * cellSize);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Window");
        Obstacle mazePanel = new Obstacle(21, 15);

        frame.add(mazePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        
        frame.setSize(700, 500);
        frame.setLocation(100, 100); 
    }
}

