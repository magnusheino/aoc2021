fun main() {

    fun upValue(grid: Array<Array<Int>>, rowIndex: Int, colIndex: Int): Int {
        return try {
            grid[rowIndex - 1][colIndex]
        } catch (e: IndexOutOfBoundsException) {
            Int.MAX_VALUE
        }

    }

    fun downValue(grid: Array<Array<Int>>, rowIndex: Int, colIndex: Int): Int {
        return try {
            grid[rowIndex + 1][colIndex]
        } catch (e: IndexOutOfBoundsException) {
            Int.MAX_VALUE
        }
    }

    fun rightValue(grid: Array<Array<Int>>, rowIndex: Int, colIndex: Int): Int {
        return try {
            grid[rowIndex][colIndex + 1]
        } catch (e: IndexOutOfBoundsException) {
            Int.MAX_VALUE
        }
    }

    fun leftValue(grid: Array<Array<Int>>, rowIndex: Int, colIndex: Int): Int {
        return try {
            grid[rowIndex][colIndex - 1]
        } catch (e: IndexOutOfBoundsException) {
            Int.MAX_VALUE
        }
    }

    fun part1(input: List<String>): Int {
        var grid = input.map { line ->
            line.toCharArray().map { it.digitToInt() }.toTypedArray()
        }.toTypedArray()

        val lowPoints = mutableListOf<Int>()

        grid.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, col ->
                if (listOf(
                        upValue(grid, rowIndex, colIndex),
                        rightValue(grid, rowIndex, colIndex),
                        downValue(grid, rowIndex, colIndex),
                        leftValue(grid, rowIndex, colIndex)
                    ).all { it > col }
                ) {
                    lowPoints.add(col)
                }
            }
        }

        return lowPoints.sumOf { it + 1 }
    }


    fun part2(input: List<String>): Int {
        var grid = input.map { line ->
            line.toCharArray().map { it.digitToInt() }.toTypedArray()
        }.toTypedArray()

        fun isBasin(value: Int, current: Int): Boolean {
            return value > current && value != Int.MAX_VALUE && value != 9
        }

        fun walk(
            grid: Array<Array<Int>>,
            current: Int,
            rowIndex: Int,
            colIndex: Int,
            visited: MutableSet<Pair<Int, Int>>,
            basin: MutableList<Int>
        ) {
            val up = upValue(grid, rowIndex, colIndex)
            val right = rightValue(grid, rowIndex, colIndex)
            val down = downValue(grid, rowIndex, colIndex)
            val left = leftValue(grid, rowIndex, colIndex)

            if (!visited.contains(rowIndex - 1 to colIndex) && isBasin(up, current)) {
                visited.add(rowIndex - 1 to colIndex)
                basin.add(up)
                walk(grid, up, rowIndex - 1, colIndex, visited, basin)
            }
            if (!visited.contains(rowIndex to colIndex + 1) && isBasin(right, current)) {
                visited.add(rowIndex to colIndex + 1)
                basin.add(right)
                walk(grid, right, rowIndex, colIndex + 1, visited, basin)
            }
            if (!visited.contains(rowIndex + 1 to colIndex) && isBasin(down, current)) {
                visited.add(rowIndex + 1 to colIndex)
                basin.add(down)
                walk(grid, down, rowIndex + 1, colIndex, visited, basin)
            }
            if (!visited.contains(rowIndex to colIndex - 1) && isBasin(left, current)) {
                visited.add(rowIndex to colIndex - 1)
                basin.add(left)
                walk(grid, left, rowIndex, colIndex - 1, visited, basin)
            }
        }

        val basins = mutableListOf<List<Int>>()

        grid.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, col ->
                if (listOf(
                        upValue(grid, rowIndex, colIndex),
                        rightValue(grid, rowIndex, colIndex),
                        downValue(grid, rowIndex, colIndex),
                        leftValue(grid, rowIndex, colIndex)
                    ).all { it > col }
                ) {
                    val visited = mutableSetOf<Pair<Int, Int>>(rowIndex to colIndex)
                    val basin = mutableListOf<Int>(col)
                    walk(grid, col, rowIndex, colIndex, visited, basin)
                    basins.add(basin)
                }
            }
        }

        return basins.sortedBy { it.size }.reversed().take(3).fold(1) { acc: Int, ints -> acc * ints.size }
    }

    solvePuzzle("Day09_test", ::part1, 15)
    solvePuzzle("Day09", ::part1, 594)

    solvePuzzle("Day09_test", ::part2, 1134)
    solvePuzzle("Day09", ::part2, 858494)
}
