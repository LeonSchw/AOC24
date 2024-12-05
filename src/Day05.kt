
fun main() {
    fun checkSequence (intSequence: List<Int>, mustBefore : MutableMap<Int, MutableSet<Int>>) : Boolean {
        for (idx in intSequence.indices) {
            if (mustBefore.containsKey(intSequence[idx])) {
                for (rule in mustBefore[intSequence[idx]]!!) {
                    if (intSequence.subList(idx, intSequence.size).contains(rule)) {
                        return false
                    }
                }
            }
        }
        return true
    }

    fun part1(input: List<String>): Int {
        val separatorIndex : Int = input.indexOf("")
        val rules : List<String> = input.subList(0, separatorIndex)
        val sequences : List<String> = input.subList(separatorIndex+1, input.size)
        val mustBefore : MutableMap<Int, MutableSet<Int>> = mutableMapOf()
        for (rule in rules) {
            val intRule : List<Int> = parseIntList(rule,"|")
            if (!mustBefore.containsKey(intRule[1])) {
                mustBefore[intRule[1]] = mutableSetOf(intRule[0])
            }
            else {
                mustBefore[intRule[1]]!!.add(intRule[0])
            }
        }
        var result = 0
        for (sequence in sequences) {
            val intSequence : List<Int> = parseIntList(sequence,",")
            val isWrong : Boolean = !checkSequence(intSequence, mustBefore)
            if (!isWrong) {
                val middleIndex : Int = intSequence.size/2
                result += intSequence[middleIndex]
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val separatorIndex : Int = input.indexOf("")
        val rules : List<String> = input.subList(0, separatorIndex)
        val sequences : List<String> = input.subList(separatorIndex+1, input.size)
        val mustBefore : MutableMap<Int, MutableSet<Int>> = mutableMapOf()
        for (rule in rules) {
            val intRule : List<Int> = parseIntList(rule,"|")
            if (!mustBefore.containsKey(intRule[1])) {
                mustBefore[intRule[1]] = mutableSetOf(intRule[0])
            }
            else {
                mustBefore[intRule[1]]!!.add(intRule[0])
            }
        }

        var result = 0
        for (sequence in sequences) {
            val intSequence : List<Int> = parseIntList(sequence,",")
            val isWrong : Boolean = !checkSequence(intSequence, mustBefore)
            if (isWrong) {
                val sortedIntSequence : List<Int> = intSequence.sortedWith { int1, int2 ->
                    if (mustBefore.containsKey(int1) && mustBefore[int1]!!.contains(int2)) {
                        1
                    } else if (mustBefore.containsKey(int2) && mustBefore[int2]!!.contains(int1)) {
                        -1
                    } else {
                        0
                    }
                }
                val middleIndex : Int = sortedIntSequence.size/2
                result += sortedIntSequence[middleIndex]
            }
        }
        return result
    }

    val testInput = readInput("inputs/Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    val input = readInput("inputs/Day05")
    part1(input).println()
    part2(input).println()
}
