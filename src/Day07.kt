import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val positions = input.first().split(",").map { it.toInt() }

        val min = positions.minOf { it }
        val max = positions.maxOf { it }

        var bestPosition = 0 to Int.MAX_VALUE

        for (position in min..max) {
            val cost = positions.sumOf { abs(position - it) }
            if (cost < bestPosition.second) {
                bestPosition = position to cost
            }
        }

        println("Align to $bestPosition")


        return bestPosition.second
    }

    fun part2(input: List<String>): Int {
        val positions = input.first().split(",").map { it.toInt() }

        val min = positions.minOf { it }
        val max = positions.maxOf { it }

        var bestPosition = 0 to Int.MAX_VALUE

        for (position in min..max) {
            val cost = positions.sumOf { (1..abs(position - it)).sum() }
            if (cost < bestPosition.second) {
                bestPosition = position to cost
            }
        }

        println("Align to $bestPosition")


        return bestPosition.second
    }

    solvePuzzle("Day07_test", ::part1, 37)
    solvePuzzle("Day07", ::part1, 336721)

    solvePuzzle("Day07_test", ::part2, 168)
    solvePuzzle("Day07", ::part2, 91638945)
}
