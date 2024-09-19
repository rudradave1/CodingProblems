import java.util.*

enum class Direction {
    N, E, S, W;

    fun left(): Direction = when (this) {
        N -> W
        E -> N
        S -> E
        W -> S
    }

    fun right(): Direction = when (this) {
        N -> E
        E -> S
        S -> W
        W -> N
    }
}

class Robot(var x: Int, var y: Int, var direction: Direction, private val maxX: Int, private val maxY: Int) {

    fun move() {
        when (direction) {
            Direction.N -> if (y < maxY) y += 1
            Direction.E -> if (x < maxX) x += 1
            Direction.S -> if (y > 0) y -= 1
            Direction.W -> if (x > 0) x -= 1
        }
    }

    fun executeInstructions(instructions: String) {
        instructions.forEach { instruction ->
            when (instruction) {
                'L' -> direction = direction.left()
                'R' -> direction = direction.right()
                'M' -> move()
            }
        }
    }

    fun displayPosition() {
        println("$x $y $direction")
    }
}

fun main() {
    val scanner = Scanner(System.`in`)

    try {
        // Input grid size
        println("Enter grid size (e.g., 5 5):")
        val gridX = scanner.nextInt()
        val gridY = scanner.nextInt()

        // Input the robot's initial position and orientation
        println("Enter initial position and direction (e.g., 1 2 N):")
        val startX = scanner.nextInt()
        val startY = scanner.nextInt()
        val startDirection = Direction.valueOf(scanner.next().uppercase())

        // Input navigation instructions
        println("Enter instructions (e.g., LMLMLMLMM):")
        val instructions = scanner.next()

        // Create robot with grid boundaries and execute instructions
        val robot = Robot(startX, startY, startDirection, gridX, gridY)
        robot.executeInstructions(instructions)

        // Display final position
        robot.displayPosition()

    } catch (e: NoSuchElementException) {
        println("Input error: Make sure to provide the correct format.")
    } catch (e: IllegalArgumentException) {
        println("Invalid direction or instructions provided.")
    }
}
