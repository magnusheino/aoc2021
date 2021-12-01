fun main() {
    fun solvePuzzle(input: String, solver: (List<String>) -> Int, expectedResult: Int) {
        val result = solver(readInput(input))
        println("$input resolved with $solver is $result")
        check(result === expectedResult)
    }

    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }.zipWithNext().count { (prev, next) -> next > prev }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toInt() }.windowed(3) { it.sum() }.zipWithNext().count { (prev, next) -> next > prev }
    }

    solvePuzzle("Day01_test", ::part1, 7)
    solvePuzzle("Day01", ::part1, 1616)

    solvePuzzle("Day01_test", ::part2, 5)
    solvePuzzle("Day01", ::part2, 1645)
}
