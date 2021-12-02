fun main() {
    fun part1(input: List<String>): Int {
        var position = 0
        var depth = 0
        for (line in input) {
            val instruction = Pair(line.substringBefore(' ').trim(), line.substringAfter(' ').trim().toInt())
            when (instruction.first) {
                "forward" -> {
                    println("Move forward ${instruction.second}")
                    position += instruction.second
                }
                "up" -> {
                    println("Move up ${instruction.second}")
                    depth -= instruction.second
                }
                "down" -> {
                    println("Move down ${instruction.second}")
                    depth += instruction.second
                }
                else -> {
                    throw IllegalArgumentException("Could not handle instruction $instruction")
                }
            }
        }

        println("Position: $position, depth: $depth")

        return position * depth
    }

    fun part2(input: List<String>): Int {
        var position = 0
        var depth = 0
        var aim = 0
        for (line in input) {
            val instruction = Pair(line.substringBefore(' ').trim(), line.substringAfter(' ').trim().toInt())
            when (instruction.first) {
                "forward" -> {
                    println("Move forward ${instruction.second}")
                    position += instruction.second
                    depth += aim * instruction.second
                }
                "up" -> {
                    println("Aim up ${instruction.second}")
                    aim -= instruction.second
                }
                "down" -> {
                    println("Aim down ${instruction.second}")
                    aim += instruction.second
                }
                else -> {
                    throw IllegalArgumentException("Could not handle instruction $instruction")
                }
            }
        }

        println("Position: $position, depth: $depth")

        return position * depth
    }

    solvePuzzle("Day02_test", ::part1, 150)
    solvePuzzle("Day02", ::part1, 1604850)

    solvePuzzle("Day02_test", ::part2, 900)
    solvePuzzle("Day02", ::part2, 1685186100)
}
