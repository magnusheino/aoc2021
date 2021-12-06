import java.util.*

fun main() {

    data class Point(val x: Int, val y: Int)

    data class Line(val definition: String, val includeDiagonal: Boolean = false) {
        fun points(): List<Point> {
            val points = mutableListOf<Point>()
            val match = "^(\\d+),(\\d+) -> (\\d+),(\\d+)\$".toRegex().find(definition)!!
            val (x1, y1, x2, y2) = match.destructured
            if ((x1.toInt() === x2.toInt() || y1.toInt() === y2.toInt())) {
                for (x in (x1.toInt() toward x2.toInt())) {
                    for (y in (y1.toInt() toward y2.toInt())) {
                        points.add(Point(x, y))
                    }
                }
            } else if (includeDiagonal) {
                val k = (y1.toInt() - y2.toInt()) / (x1.toInt() - x2.toInt())
                val m = y1.toInt() - (k * x1.toInt())
                for (x in (x1.toInt() toward x2.toInt())) {
                    points.add(Point(x, k * x + m))
                }
            }

            return points
        }

        override fun toString(): String {
            return "Line(definition='$definition', points='${points()}')"
        }
    }

    fun part1(input: List<String>): Int {
        val points = input.map { Line(it) }.flatMap { it.points() }

        return points.distinct().map { Collections.frequency(points, it) }.count { it >= 2 }
    }

    fun part2(input: List<String>): Int {
        val points = input.map { Line(it, includeDiagonal = true) }.flatMap { it.points() }
        return points.distinct().map { Collections.frequency(points, it) }.count { it >= 2 }
    }

    solvePuzzle("Day05_test", ::part1, 5)
    solvePuzzle("Day05", ::part1, 6267)

    solvePuzzle("Day05_test", ::part2, 12)
    solvePuzzle("Day05", ::part2, 20196)
}
