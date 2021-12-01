fun main() {
    fun solvePuzzle(input: String, solver: (List<String>) -> Int, expectedResult: Int) {
        val result = solver(readInput(input))
        println("$input resolved with $solver is $result")
        check(result === expectedResult)
    }

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    solvePuzzle("Day02_test", ::part1, 0)
    solvePuzzle("Day02", ::part1, 0)

    solvePuzzle("Day02_test", ::part2, 0)
    solvePuzzle("Day02", ::part2, 0)
}
