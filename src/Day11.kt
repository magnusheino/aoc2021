fun main() {

    data class Octopus(var energyLevel: Int, var flashed: Boolean = false) {
        override fun toString(): String {
            return "$energyLevel"
        }
    }

    data class Grid(val grid: Array<Array<Octopus>>) {

        fun tick(): Int {
            increaseEnergyLevel()
            flash()
            return resetFlashedEnergyLevels()
        }

        private fun resetFlashedEnergyLevels(): Int {
            var flashed = 0
            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { colIndex, col ->
                    if (grid[rowIndex][colIndex].energyLevel > 9) {
                        grid[rowIndex][colIndex].energyLevel = 0
                        flashed++
                    }
                    grid[rowIndex][colIndex].flashed = false
                }
            }

            return flashed
        }

        private fun flash() {
            while (grid.any { it.any { octopus -> octopus.energyLevel > 9 && !octopus.flashed } }) {
                grid.forEachIndexed { rowIndex, row ->
                    row.forEachIndexed { colIndex, col ->
                        if (grid[rowIndex][colIndex].energyLevel > 9 && !grid[rowIndex][colIndex].flashed) {
                            grid[rowIndex][colIndex].flashed = true

                            try {
                                grid[rowIndex][colIndex].energyLevel += 1
                            } catch (e: Exception) {
                            }
                            try {
                                grid[rowIndex - 1][colIndex - 1].energyLevel += 1
                            } catch (e: Exception) {
                            }
                            try {
                                grid[rowIndex - 1][colIndex].energyLevel += 1
                            } catch (e: Exception) {
                            }
                            try {
                                grid[rowIndex - 1][colIndex + 1].energyLevel += 1
                            } catch (e: Exception) {
                            }
                            try {
                                grid[rowIndex][colIndex - 1].energyLevel += 1
                            } catch (e: Exception) {
                            }
                            try {
                                grid[rowIndex][colIndex + 1].energyLevel += 1
                            } catch (e: Exception) {
                            }
                            try {
                                grid[rowIndex + 1][colIndex - 1].energyLevel += 1
                            } catch (e: Exception) {
                            }
                            try {
                                grid[rowIndex + 1][colIndex].energyLevel += 1
                            } catch (e: Exception) {
                            }
                            try {
                                grid[rowIndex + 1][colIndex + 1].energyLevel += 1
                            } catch (e: Exception) {
                            }
                        }
                    }
                }
            }

        }

        private fun increaseEnergyLevel() {
            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { colIndex, col ->
                    grid[rowIndex][colIndex].energyLevel += 1
                }
            }
        }

        override fun toString(): String {
            return buildString { grid.forEach { line -> appendLine(line.asList()) } }
        }
    }

    fun part1(input: List<String>): Int {
        val grid =
            Grid(input.map { line ->
                line.toCharArray().map { it.digitToInt() }.toIntArray().map { Octopus(it) }.toTypedArray()
            }.toTypedArray())

        println("Before any steps:")
        println(grid)

        var flashed = 0

        repeat(100) { index ->
            flashed += grid.tick()
            println("After step ${index + 1}:")
            println(grid)
        }
        return flashed
    }


    fun part2(input: List<String>): Int {
        val grid =
            Grid(input.map { line ->
                line.toCharArray().map { it.digitToInt() }.toIntArray().map { Octopus(it) }.toTypedArray()
            }.toTypedArray())

        println("Before any steps:")
        println(grid)

        var ticks = 1

        while (grid.tick() != 100) {
            println("After step ${ticks + 1}:")
            println(grid)
            ticks++
        }
        return ticks
    }

    solvePuzzle("Day11_test", ::part1, 1656)
    solvePuzzle("Day11", ::part1, 1697)

    solvePuzzle("Day11_test", ::part2, 195)
    solvePuzzle("Day11", ::part2, 344)
}

