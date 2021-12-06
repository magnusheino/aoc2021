import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

infix fun Int.toward(to: Int): IntProgression {
    val step = if (this > to) -1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun solvePuzzle(input: String, solver: (List<String>) -> Number, expectedResult: Number) {
    val result = solver(readInput(input))
    println("$input resolved with $solver is $result")
    check(result == expectedResult)
}
