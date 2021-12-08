fun main() {

    fun String.alphabetized() = String(toCharArray().apply { sort() })

    data class Entry(val signalPattern: List<String>, val outputValues: List<String>) {

        fun decodeOutputValue(): Int {
            val chars = outputValues.map { outputValue ->
                when (outputValue) {
                    one() -> {
                        1
                    }
                    two() -> {
                        2
                    }
                    three() -> {
                        3
                    }
                    four() -> {
                        4
                    }
                    five() -> {
                        5
                    }
                    six() -> {
                        6
                    }
                    seven() -> {
                        7
                    }
                    eight() -> {
                        8
                    }
                    nine() -> {
                        9
                    }
                    zero() -> {
                        0
                    }
                    else -> {
                        throw IllegalStateException(outputValue)
                    }
                }
            }

            return chars.joinToString("") { it.toString() }.toInt()
        }

        fun zero(): String {
            return signalPattern.firstOrNull {
                it.length === 6
                        && !four().toCharArray().all { char -> it.contains(char) }
                        && one().toCharArray().all { char -> it.contains(char) }
            } ?: ""
        }

        fun one(): String {
            return signalPattern.firstOrNull { it.length === 2 } ?: ""
        }

        fun two(): String {
            return signalPattern.firstOrNull {
                it.length === 5 && four().toCharArray().count { char -> it.contains(char) } === 2
            } ?: ""
        }

        fun three(): String {
            return signalPattern.firstOrNull {
                it.length === 5 && one().toCharArray().all { char -> it.contains(char) }
            } ?: ""
        }

        fun four(): String {
            return signalPattern.firstOrNull {
                it.length === 4 && one().toCharArray().all { char -> it.contains(char) }
            } ?: ""
        }

        fun five(): String {
            return signalPattern.firstOrNull {
                it.length === 5 && four().toCharArray()
                    .count { char -> it.contains(char) } === 3 && !one().toCharArray().all { char -> it.contains(char) }
            } ?: ""
        }

        fun six(): String {
            return signalPattern.firstOrNull {
                it.length === 6
                        && !four().toCharArray().all { char -> it.contains(char) }
                        && !one().toCharArray().all { char -> it.contains(char) }
            } ?: ""
        }

        fun seven(): String {
            return signalPattern.firstOrNull { it.length === 3 } ?: ""
        }

        fun eight(): String {
            return signalPattern.firstOrNull { it.length === 7 } ?: ""
        }

        fun nine(): String {
            return signalPattern.firstOrNull {
                it.length === 6 && four().toCharArray().all { char -> it.contains(char) }
            } ?: ""
        }
    }

    fun part1(input: List<String>): Int {
        val entries = input.map { line ->
            val signalPattern = line.split(" ").take(10).map { it.alphabetized() }
            val outputValues = line.split(" ").drop(11).map { it.alphabetized() }
            Entry(signalPattern, outputValues)
        }

        return entries.sumOf { entry ->
            entry.outputValues.count { listOf(entry.one(), entry.four(), entry.seven(), entry.eight()).contains(it) }
        }
    }

    fun part2(input: List<String>): Int {
        val entries = input.map { line ->
            val signalPattern = line.split(" ").take(10).map { it.alphabetized() }
            val outputValues = line.split(" ").drop(11).map { it.alphabetized() }
            Entry(signalPattern, outputValues)
        }

        return entries.sumOf { it.decodeOutputValue() }
    }

    solvePuzzle("Day08_test", ::part1, 26)
    solvePuzzle("Day08", ::part1, 245)

    solvePuzzle("Day08_test", ::part2, 61229)
    solvePuzzle("Day08", ::part2, 983026)
}
