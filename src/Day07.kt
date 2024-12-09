
fun main() {

    fun checkEquation (result : Long, numbers : List<Long>) : Boolean {
        if (numbers.size == 2) {
            return result == numbers[0] + numbers[1] || result == numbers[0] * numbers[1]
        }
        var checkResult = false
        if (result % numbers[numbers.size-1] == 0.toLong()) {
            checkResult = checkEquation(result/numbers[numbers.size-1], numbers.subList(0,numbers.size-1))
        }
        if (!checkResult && result - numbers[numbers.size-1] > 0.toLong()) {
            checkResult = checkEquation(result - numbers[numbers.size-1], numbers.subList(0, numbers.size-1))
        }

        return checkResult
    }

    fun part1(input: List<String>): Long {
        var result : Long = 0
        for (line in input) {
            val splitColon : List<String> = line.split(": ")
            val testValue : Long = splitColon[0].toLong()
            val numbers : List<Long> = parseLongList(splitColon[1], " ")
            if (checkEquation(testValue, numbers)) {
                result += testValue
            }
        }
        return result
    }

    fun splitNumber (number : Long, rightPart : Long): Long {
        val numberString : String = number.toString()
        val rightPartString : String = rightPart.toString()
        val leftPartString : String = numberString.substring(0, numberString.length-rightPartString.length)

        return leftPartString.toLong()
    }

    fun checkConcatenation (number : Long, rightPart : Long): Boolean {
        val numberString : String = number.toString()
        val rightPartString : String = rightPart.toString()

        if (numberString.length-rightPartString.length <= 0) {
            return false
        }

        return numberString.substring(numberString.length-rightPartString.length) == rightPartString
    }

    fun checkEquation2 (result : Long, numbers : List<Long>): Boolean {
        if (numbers.size == 2) {
            return result == numbers[0] + numbers[1] || result == numbers[0] * numbers[1] || (checkConcatenation(result, numbers[1]) &&  numbers[0] == splitNumber(result, numbers[1]))
        }
        var checkResult = false
        if (result % numbers[numbers.size-1] == 0.toLong()) {
            checkResult = checkEquation2(result/numbers[numbers.size-1], numbers.subList(0,numbers.size-1))
        }
        if (!checkResult && result - numbers[numbers.size-1] > 0.toLong()) {
            checkResult = checkEquation2(result - numbers[numbers.size-1], numbers.subList(0, numbers.size-1))
        }
        if (!checkResult && checkConcatenation(result, numbers[numbers.size-1])){
            checkResult = checkEquation2(splitNumber(result, numbers[numbers.size-1]), numbers.subList(0, numbers.size-1))
        }

        return checkResult
    }

    fun part2(input: List<String>): Long {
        var result : Long = 0
        for (line in input) {
            val splitColon : List<String> = line.split(": ")
            val testValue : Long = splitColon[0].toLong()
            val numbers : List<Long> = parseLongList(splitColon[1], " ")
            if (checkEquation2(testValue, numbers)) {
                result += testValue
            }
        }
        return result
    }

    val testInput = readInput("inputs/Day07_test")
    check(part1(testInput) == 3749.toLong())
    check(part2(testInput) == 11387.toLong())

    val input = readInput("inputs/Day07")
    part1(input).println()
    part2(input).println()
}
