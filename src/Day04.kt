fun main() {

    data class Board(val numbers: MutableMap<Int, Boolean>) {
        fun mark(number: Int) {
            if (numbers.contains(number)) {
                numbers[number] = true
            }
        }

        fun hasBingo(): Boolean {
            val combinations = listOf(
                listOf(0, 1, 2, 3, 4),
                listOf(5, 6, 7, 8, 9),
                listOf(10, 11, 12, 13, 14),
                listOf(15, 16, 17, 18, 19),
                listOf(20, 21, 22, 23, 24),

                listOf(0, 5, 10, 15, 20),
                listOf(1, 6, 11, 16, 21),
                listOf(2, 7, 12, 17, 22),
                listOf(3, 8, 13, 18, 23),
                listOf(4, 9, 14, 19, 24)
            )

            for (combination in combinations) {
                if (combination.map { numbers.entries.elementAt(it).value }.all { it === true }) {
                    return true
                }
            }
            return false
        }
    }

    fun part1(input: List<String>): Int {
        val numbers = input[0].split(",").map { it.toInt() }

        val boards = input.drop(1).windowed(6, 6).map { it.filter { row -> row.length !== 0 } }
            .map { rows ->
                Board(rows.map { it.windowed(2, 3) }.map { it.map { it.trim().toInt() } }.flatten().map { it to false }
                    .toMap().toMutableMap())
            }

        for (number in numbers) {
            for (board in boards) {
                board.mark(number)
                if (board.hasBingo()) {
                    return board.numbers.filter { it.value === false }.map { it.key }.sum() * number
                }
            }
        }
        throw IllegalStateException("No winner :-(")
    }

    fun part2(input: List<String>): Int {
        val numbers = input[0].split(",").map { it.toInt() }

        val boards = input.drop(1).windowed(6, 6).map { it.filter { row -> row.length !== 0 } }
            .map { rows ->
                Board(rows.map { it.windowed(2, 3) }.map { it.map { it.trim().toInt() } }.flatten().map { it to false }
                    .toMap().toMutableMap())
            }.toMutableList()

        var bingoBoards: MutableList<Board> = mutableListOf()
        var previousNumber: Int = 0

        for (number in numbers) {
            if (bingoBoards.isNotEmpty()) {
                boards.removeAll(bingoBoards)
                if (boards.isEmpty()) {
                    return bingoBoards.first().numbers.filter { it.value === false }.map { it.key }
                        .sum() * previousNumber
                }
                bingoBoards.clear()
            }

            for (board in boards) {
                board.mark(number)
                if (board.hasBingo()) {
                    bingoBoards.add(board)
                }
            }
            previousNumber = number
        }

        throw IllegalStateException("No winner :-(")
    }

    solvePuzzle("Day04_test", ::part1, 4512)
    solvePuzzle("Day04", ::part1, 60368)

    solvePuzzle("Day04_test", ::part2, 1924)
    solvePuzzle("Day04", ::part2, 17435)
}
