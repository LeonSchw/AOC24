import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Parses integer list from given string, where numbers are separated by given delimiter.
 */
fun parseIntList (input : String, delimiter : String = " ") =  input.split(delimiter).map { it.toInt() }.toList()

/**
 * Parses integer list from given string, where numbers are separated by given delimiter.
 */
fun parseLongList (input : String, delimiter : String = " ") =  input.split(delimiter).map { it.toLong() }.toList()

fun setCharAtIndex (line : String, char: Char, index : Int) : String {
    val output = buildString {
        for (idx in line.indices) {
            if (idx == index)
                append(char)
            else
                append(line[idx])
        }
    }
    return output
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
