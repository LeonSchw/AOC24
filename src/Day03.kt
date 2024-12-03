fun main() {
    fun part1(input: List<String>): Int {
        val mulExpression = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
        val numberRegex = Regex("\\d{1,3}")

        var result = 0
        for (line in input) {
            for (match in mulExpression.findAll(line)){
                val numbers = numberRegex.findAll(match.value).toList()
                result += numbers[0].value.toInt() * numbers[1].value.toInt()
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val expression = Regex("do\\(\\)|don't\\(\\)|mul\\(\\d{1,3},\\d{1,3}\\)")
        val numberRegex = Regex("\\d{1,3}")

        var result = 0
        var skip = false
        for (line in input) {
            for (match in expression.findAll(line)){
                if (match.value == "don't()")
                    skip = true
                else if (match.value == "do()")
                    skip = false
                else if (!skip) {
                    val numbers = numberRegex.findAll(match.value).toList()
                    result += numbers[0].value.toInt() * numbers[1].value.toInt()
                }
            }
        }
        return result
    }

    val testInput = readInput("inputs/Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    val input = readInput("inputs/Day03")
    part1(input).println()
    part2(input).println()
}
