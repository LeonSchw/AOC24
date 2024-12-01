import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val left : MutableList<Int> = mutableListOf()
        val right : MutableList<Int> = mutableListOf()
        for (line : String in input) {
            val elem = line.split("   ")
            left.add(elem[0].toInt())
            right.add(elem[1].toInt())
        }
        left.sort()
        right.sort()
        var result = 0
        for (i : Int in input.indices) {
            result += (left[i] - right[i]).absoluteValue
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val left : MutableList<Int> = mutableListOf()
        val right : MutableList<Int> = mutableListOf()
        val numberToCount : MutableMap<Int, Int> = mutableMapOf()
        for (line : String in input) {
            val elem = line.split("   ")
            left.add(elem[0].toInt())
            val rightNumber = elem[1].toInt()
            right.add(rightNumber)
            if (!numberToCount.containsKey(rightNumber)){
                numberToCount[rightNumber] = 1
            }
            else {
                numberToCount[rightNumber] = numberToCount[rightNumber]!! + 1
            }
        }
        var result = 0
        for (number in left) {
            val count : Int = if (numberToCount.containsKey(number)) numberToCount[number]!! else 0
            result += number * count
        }

        return result
    }

    val testInput = readInput("inputs/Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("inputs/Day01")
    part1(input).println()
    part2(input).println()
}
