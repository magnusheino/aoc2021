fun main() {

    fun simulate(input: List<String>, days: Int): Long {
        var lanternfish =
            input.first().split(",").map { it.toInt() }.groupBy { it }.map { (k, v) -> k to v.size.toLong() }.toMap()
                .toMutableMap()

        repeat(days) {
            (lanternfish.map { (age, amount) ->
                if (age == 0) (6 to amount) else (age - 1 to amount)
            } + (8 to (lanternfish[0] ?: 0)))
                .also { lanternfish.clear() }
                .forEach { (age, amount) ->
                    lanternfish[age] = (lanternfish[age] ?: 0) + amount
                }
        }

        return lanternfish.values.sum()
    }

    fun part1(input: List<String>): Long {
        return simulate(input, 80)
    }

    fun part2(input: List<String>): Long {
        return simulate(input, 256)
    }

    solvePuzzle("Day06_test", ::part1, 5934L)
    solvePuzzle("Day06", ::part1, 366057L)

    solvePuzzle("Day06_test", ::part2, 26984457539L)
    solvePuzzle("Day06", ::part2, 1653559299811L)
}
