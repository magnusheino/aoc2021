fun main() {
    fun part1(input: List<String>): Int {
        var gammaRateBinary = ""
        input.first().forEachIndexed { index, _ ->
            gammaRateBinary += input.map { it[index] }.groupBy { it }.maxByOrNull { it.value.size }?.key
        }

        val gammaRate = gammaRateBinary.toInt(2)
        val epsilonRate = gammaRateBinary.map { if (it === '0') '1' else '0' }.joinToString("").toInt(2)

        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        fun rating(input: List<String>, defaultIfEqual: Char): Int {
            check(listOf('0', '1').contains(defaultIfEqual))

            var candidates = input.toMutableList()
            input.first().forEachIndexed { index, _ ->
                candidates = candidates.filter { candidate ->
                    val least = candidates.map { it[index] }.groupBy { it }
                        .minByOrNull { it.value.size }?.value
                    val most = candidates.map { it[index] }.groupBy { it }
                        .maxByOrNull { it.value.size }?.value
                    candidate[index] === if (least?.size ?: 0 === most?.size ?: 0) defaultIfEqual else if (defaultIfEqual === '1') most?.first() else least?.first()
                }.toMutableList()
                if (candidates.size === 1) {
                    return candidates.first().toInt(2)
                }
            }
            throw IllegalStateException("Failed to calculate rating")
        }

        val oxygen = rating(input, '1')
        val c02 = rating(input, '0')

        return oxygen * c02
    }

    solvePuzzle("Day03_test", ::part1, 198)
    solvePuzzle("Day03", ::part1, 3895776)

    solvePuzzle("Day03_test", ::part2, 230)
    solvePuzzle("Day03", ::part2, 7928162)
}
