import java.util.Scanner;

enum Direction {
    N, E, S, W;

    Direction left() {
        switch (this) {
            case N: return W;
            case E: return N;
            case S: return E;
            case W: return S;
            default: throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }

    Direction right() {
        switch (this) {
            case N: return E;
            case E: return S;
            case S: return W;
            case W: return N;
            default: throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;
    private final int maxX;
    private final int maxY;

    public Robot(int x, int y, Direction direction, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    private void move() {
        switch (direction) {
            case N:
                if (y < maxY) y++;
                break;
            case E:
                if (x < maxX) x++;
                break;
            case S:
                if (y > 0) y--;
                break;
            case W:
                if (x > 0) x--;
                break;
        }
    }

    public void executeInstructions(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'L':
                    direction = direction.left();
                    break;
                case 'R':
                    direction = direction.right();
                    break;
                case 'M':
                    move();
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected instruction: " + instruction);
            }
        }
    }

    public void displayPosition() {
        System.out.println(x + " " + y + " " + direction);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input grid size
            System.out.println("Enter grid size (e.g., 5 5):");
            int gridX = scanner.nextInt();
            int gridY = scanner.nextInt();

            // Input the robot's initial position and orientation
            System.out.println("Enter initial position and direction (e.g., 1 2 N):");
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            Direction startDirection = Direction.valueOf(scanner.next().toUpperCase());

            // Input navigation instructions
            System.out.println("Enter instructions (e.g., LMLMLMLMM):");
            String instructions = scanner.next();

            // Create robot with grid boundaries and execute instructions
            Robot robot = new Robot(startX, startY, startDirection, gridX, gridY);
            robot.executeInstructions(instructions);

            // Display final position
            robot.displayPosition();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
