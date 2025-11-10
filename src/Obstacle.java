import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Obstacle extends JPanel {

    private int width;
    private int height;
    private char[][] maze;
    private final Random rand = new Random();

    private Image rockImage;
    private Image treeImage;
    private Image backgroundImage;
    private Image wallImage;
    private Image characterImage;
    private Image CoinImage; 

    private int cellSize = 32;

    public Obstacle(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new char[height][width];
        generateObstacle();

        // Load images
        rockImage = loadImage("desert_rock1.png");
        treeImage = loadImage("birch_1.png");
        backgroundImage = loadImage("grass.png");
        wallImage = loadImage("walls.png");
        characterImage = loadImage("Enemy.png");
        CoinImage = loadImage("Coin.png"); 

        setPreferredSize(new Dimension(width * cellSize, height * cellSize));
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Dynamically adjust cell size
        cellSize = Math.min(panelWidth / width, panelHeight / height);

        // Center maze area
        int mazeWidth = cellSize * width;
        int mazeHeight = cellSize * height;
        int offsetX = (panelWidth - mazeWidth) / 2;
        int offsetY = (panelHeight - mazeHeight) / 2;

        //  Tile the background
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

        // Draw maze & border walls
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int px = offsetX + x * cellSize;
                int py = offsetY + y * cellSize;
                char c = maze[y][x];
                boolean isBorderCell = (y == 0 || y == height - 1 || x == 0 || x == width - 1);

                // Draw border walls
                if (isBorderCell) {
                    int smallerHeight = (int) (cellSize * 0.7);
                    if (wallImage != null) {
                        if (y == 0)
                            g2.drawImage(wallImage, px, py + (cellSize - smallerHeight), cellSize, smallerHeight, this);
                        else if (y == height - 1)
                            g2.drawImage(wallImage, px, py, cellSize, smallerHeight, this);
                        else
                            g2.drawImage(wallImage, px, py, cellSize, cellSize, this);
                    }
                    continue;
                }

                // Draw open maze paths
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

        //  Add random coins
        if (CoinImage != null) {
            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    if (maze[y][x] == ' ' && rand.nextDouble() < 0.15) { 
                        int px = offsetX + x * cellSize;
                        int py = offsetY + y * cellSize;
                        int decoSize = (int) (cellSize * 0.6);
                        int offset = (cellSize - decoSize) / 2;
                        g2.drawImage(CoinImage, px + offset, py + offset, decoSize, decoSize, this);
                    }
                }
            }
        }

        //  Draw grid overlay
        g2.setColor(new Color(0, 0, 0, 50));
        for (int x = 0; x <= width; x++) {
            int px = offsetX + x * cellSize;
            g2.drawLine(px, offsetY, px, offsetY + mazeHeight);
        }
        for (int y = 0; y <= height; y++) {
            int py = offsetY + y * cellSize;
            g2.drawLine(offsetX, py, offsetX + mazeWidth, py);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze1");
        Obstacle mazePanel = new Obstacle(21, 15);

        frame.add(mazePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                mazePanel.repaint();
            }
        });
    }
}

