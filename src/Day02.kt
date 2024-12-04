fun main() {
    fun part1(input : List<String>): Int {

        fun checkDecreasing (input : List<Int>): Int {
            var prev : Int = input[0]
            for (i in 1..<input.size){
                val diff : Int = prev - input[i]
                if (diff <= 0 || diff > 3)
                    return 0
                prev = input[i]
            }
            return 1
        }

        fun checkIncreasing (input : List<Int>): Int {
            var prev : Int = input[0]
            for (i in 1..<input.size){
                val diff : Int = input[i] - prev
                if (diff <= 0 || diff > 3)
                    return 0
                prev = input[i]
            }
            return 1
        }

        var count = 0
        for (line in input) {
            val ints : List<Int> = parseIntList(line)
            if (ints[0] < ints[1]) {
                count += checkIncreasing(ints)
            }
            else if (ints[0] > ints[1]) {
                count += checkDecreasing(ints)
            }
        }
        return count
    }

    fun part2(input : List<String>): Int {
        fun checkDecreasing (input : List<Int>, seenOneBad : Boolean = false, saveToRemove : Boolean = true): Int {
            if (input.size == 1) {
                return 1
            }
            if (input.size == 2) {
                val diff : Int = input[0] - input[1]
                val isBad = diff <= 0 || diff > 3
                return if (isBad && seenOneBad) 0 else 1
            }
            val diff : Int = input[0] - input[1]
            val isBad = diff <= 0 || diff > 3
            val nextDiff : Int = input[0] - input[2]
            val nextIsBad : Boolean = nextDiff <= 0 || nextDiff > 3

            if (isBad && nextIsBad) {
                return 0
            }
            if (isBad) {
                if (checkDecreasing(input.subList(1, input.size), true) == 1) {
                    return 1
                }
                else if (saveToRemove) {
                    return checkDecreasing(input.subList(1, input.size), true)
                }
            }
            else {
                return checkDecreasing(input.subList(1,input.size), seenOneBad, !nextIsBad)
            }
            return 0
        }

        fun checkIncreasing (input : List<Int>, seenOneBad : Boolean = false, saveToRemove : Boolean = true): Int {
            if (input.size == 1) {
                return 1
            }
            if (input.size == 2) {
                val diff : Int = input[1] - input[0]
                val isBad = diff <= 0 || diff > 3
                return if (isBad && seenOneBad) 0 else 1
            }
            val diff : Int = input[1] - input[0]
            val isBad = diff <= 0 || diff > 3
            val nextDiff : Int = input[2] - input[0]
            val nextIsBad : Boolean = nextDiff <= 0 || nextDiff > 3

            if (isBad && nextIsBad && !saveToRemove) {
                return 0
            }
            if (isBad) {
                if (checkIncreasing(input.subList(1, input.size), true) == 1) {
                    return 1
                }
                else if (saveToRemove) {
                    return checkIncreasing(input.subList(1, input.size), true)
                }
            }
            else {
                return checkIncreasing(input.subList(1,input.size), seenOneBad, !nextIsBad)
            }
            return 0
        }

        var count = 0
        for (line in input) {
            val ints : List<Int> = parseIntList(line)
            if (ints[0] < ints[1] || ints[0] < ints[2]) {
                count += checkIncreasing(ints)
            }
            if (ints[0] > ints[1] || ints[0] > ints[2]) {
                count += checkDecreasing(ints)
            }
        }
        return count
    }

    fun part2New (input : List<String>) : Int {

        fun checkDecreasing(input : MutableList<Int>, seenOneBad : Boolean = false) : Boolean {
            if (input.size == 2) {
                val diff : Int = input[0] - input[1]
                val isBad : Boolean = diff !in 1..3
                return !seenOneBad || !isBad
            }
            val diff : Int = input[0] - input[2]
            val saveToRemove : Boolean = diff in 1..3

            val diff1 : Int = input[0] - input[1]
            val isBad1 : Boolean = diff1 !in 1..3

            val diff2 : Int = input[1] - input[2]
            val isBad2 : Boolean = diff2 !in 1..3

            if (isBad1 && !saveToRemove) {
                return false
            }
            if ((isBad2 || isBad1) && seenOneBad)
                return false
            if (saveToRemove && isBad1){
                val modInput = input.toMutableList()
                modInput.removeAt(1)
                return checkDecreasing(modInput, true)
            }
            if (saveToRemove && isBad2){
                var modInput = input.toMutableList()
                modInput.removeAt(1)
                val first : Boolean = checkDecreasing(modInput, true)
                modInput = input.toMutableList()
                modInput.removeAt(2)
                val second : Boolean = checkDecreasing(modInput, true)
                return first || second
            }
            return checkDecreasing(input.subList(1, input.size), seenOneBad)
        }

        fun checkIncreasing (input : MutableList<Int>, seenOneBad : Boolean = false) : Boolean {
            if (input.size == 2) {
                val diff : Int = input[1] - input[0]
                val isBad : Boolean = diff !in 1..3
                return !seenOneBad || !isBad
            }
            val diff : Int = input[2] - input[0]
            val saveToRemove : Boolean = diff in 1..3

            val diff1 : Int = input[1] - input[0]
            val isBad1 : Boolean = diff1 !in 1..3

            val diff2 : Int = input[2] - input[1]
            val isBad2 : Boolean = diff2 !in 1..3

            if (isBad1 && !saveToRemove) {
                return false
            }
            if ((isBad2 || isBad1) && seenOneBad)
                return false
            if (saveToRemove && isBad1){
                val modInput = input.toMutableList()
                modInput.removeAt(1)
                return checkIncreasing(modInput, true)
            }
            if (saveToRemove && isBad2) {
                var modInput = input.toMutableList()
                modInput.removeAt(1)
                val first : Boolean = checkIncreasing(modInput, true)
                modInput = input.toMutableList()
                modInput.removeAt(2)
                val second : Boolean = checkIncreasing(modInput, true)
                return first || second
            }
            return checkIncreasing(input.subList(1, input.size), seenOneBad)
        }

        var count = 0
        for (line in input) {
            val ints : List<Int> = parseIntList(line)
            if (ints[0] < ints[1] && ints[1] - ints[0] <=3) {
                if (checkIncreasing(ints.toMutableList()))
                    count += 1
            }
            else if (ints[0] < ints[2] && ints[2] - ints[0] <=3) {
                val modInts = ints.toMutableList()
                modInts.removeAt(1)
                if (checkIncreasing(modInts, true))
                    count += 1
            }
            else if (ints[1] < ints[2] && ints[2] - ints[1] <=3) {
                if (checkIncreasing(ints.subList(1, ints.size).toMutableList(), true))
                    count += 1
            }
            if (ints[0] > ints[1] && ints[0] - ints[1] <=3) {
                if (checkDecreasing(ints.toMutableList()))
                    count += 1
            }
            else if (ints[0] > ints[2] && ints[0] - ints[2] <=3) {
                val modInts = ints.toMutableList()
                modInts.removeAt(1)
                if (checkDecreasing(modInts, true))
                    count += 1
            }
            else if (ints[1] > ints[2] && ints[1] - ints[2] <=3) {
                if (checkDecreasing(ints.subList(1, ints.size).toMutableList(), true))
                    count += 1
            }
        }
        return count
    }

    val testInput = readInput("inputs/Day02_test")
    check(part1(testInput) == 2)
    check(part2New(testInput) == 7)
    check(part2New(readInput("inputs/Day02_test2")) == 21)

    val input = readInput("inputs/Day02")
    part1(input).println()
    part2New(input).println()
}