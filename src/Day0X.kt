
fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("inputs/Day0X_test")
    val part1TestResult : Int = part1(testInput)
    part1TestResult.println()
    check(part1TestResult == 0)
    val part2TestResult : Int = part2(testInput)
    part2TestResult.println()
    check(part2TestResult == 0)

    val input = readInput("inputs/Day0X")
    part1(input).println()
    //part2(input).println()
}
