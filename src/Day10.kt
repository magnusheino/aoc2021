import java.util.*

fun main() {
    
    data class Line(val data: CharArray) {
        private fun isStartingChar(char: Char): Boolean {
            return listOf('(', '{', '[', '<').contains(char)
        }

        private fun getIllegalCharacterPoints(char: Char): Int {
            return when (char) {
                ')' -> 3
                ']' -> 57
                '}' -> 1197
                '>' -> 25137
                else -> throw IllegalStateException("Illegal char $char")
            }
        }

        fun getCorruptedPoints(): Int {
            val startingChars = LinkedList<Char>()
            data.forEach {
                if (isStartingChar(it)) {
                    startingChars.addFirst(it)
                } else {
                    when (startingChars.poll()) {
                        '(' -> if (it != ')') return getIllegalCharacterPoints(it)
                        '{' -> if (it != '}') return getIllegalCharacterPoints(it)
                        '<' -> if (it != '>') return getIllegalCharacterPoints(it)
                        '[' -> if (it != ']') return getIllegalCharacterPoints(it)
                    }
                }
            }

            return 0
        }

        fun getAutocompletePoints(): Long {
            val startingChars = LinkedList<Char>()
            data.forEach {
                if (isStartingChar(it)) startingChars.addFirst(it) else startingChars.poll()
            }

            return startingChars.fold(0L) { score, startingChar ->
                score * 5 + when (startingChar) {
                    '{' -> 3
                    '[' -> 2
                    '<' -> 4
                    '(' -> 1
                    else -> throw IllegalStateException("Illegal char $startingChar")
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        return input.map { Line(it.toCharArray()) }.sumOf { it.getCorruptedPoints() }
    }

    fun part2(input: List<String>): Long {
        return input.map { Line(it.toCharArray()) }.filter { it.getCorruptedPoints() === 0 }
            .map { it.getAutocompletePoints() }.sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }
    }

    solvePuzzle("Day10_test", ::part1, 26397)
    solvePuzzle("Day10", ::part1, 240123)

    solvePuzzle("Day10_test", ::part2, 288957L)
    solvePuzzle("Day10", ::part2, 3260812321L)
}
