
fun main() {

    fun expandDiskMap(map : String) : String {
        return buildString {
            for (idx in map.indices) {
                if (idx % 2 == 0) {
                    append((" " + (idx/2).toString() + " ").repeat(map[idx].toString().toInt()))
                }
                else {
                    append(".".repeat(map[idx].toString().toInt()))
                }
            }
        }
    }

    fun part1(input: List<String>): Long {
        val diskMap : String = input[0]
        val expandedDiskMap : String = expandDiskMap(diskMap)
        // expandedDiskMap.println()
        val idList : MutableList<Int> = mutableListOf()
        val regex = Regex("\\d+")
        val matches : MutableList<MatchResult> = regex.findAll(expandedDiskMap).toMutableList()
        val dotIndices : MutableList<Int> = Regex("\\.").findAll(expandedDiskMap).map { it.range.first }.toMutableList()
        // dotIndices.println()
        while (matches.isNotEmpty()) {
            val match = matches.first()
            while (dotIndices.isNotEmpty() && matches.isNotEmpty() && dotIndices.first() < match.range.first) {
                idList.add(matches.removeLast().value.toInt())
                dotIndices.removeFirst()
            }
            if (matches.isEmpty()) {
                break
            }
            idList.add(match.value.toInt())
            matches.removeFirst()
        }
        var result = 0.toLong()
        // println(idList)
        for (idx in idList.indices) {
            result += idx * idList[idx]
        }
        return result
    }

    fun part2(input: List<String>): Long {
        val diskMap : String = input[0]
        val expandedDiskMap : String = expandDiskMap(diskMap)
        // expandedDiskMap.println()

        val regex = Regex("\\d+|\\.")
        val matches : MutableList<MatchResult> = regex.findAll(expandedDiskMap).toMutableList()
        val idList = buildList { for (i in 0..<matches.size) add(-1) }.toMutableList()
        for (idx in matches.indices) {
            if (matches[idx].value == ".") {
                continue
            }
            else {
                idList[idx] = matches[idx].value.toInt()
            }
        }
        // dotIndices.println()
        var index = idList.size-1
        val sequence : MutableList<Int> = mutableListOf()
        // idList.println()
        while (index >= idList.indexOfFirst { it == -1 }) {
            if (idList[index] != -1) {
                if (sequence.isEmpty() || idList[index] == idList[sequence[0]]) {
                    // println(index)
                    sequence.add(index)
                    index -= 1
                }
                else {
                    val dotSequence : MutableList<Int> = mutableListOf()
                    for (dotIdx in idList.indices) {
                        // println(dotIdx)
                        if (idList[dotIdx] == -1) {
                            // println(dotIdx)
                            if (dotSequence.isEmpty()) {
                                dotSequence.add(dotIdx)
                            }
                            else if (dotIdx == dotSequence.last()+1) {
                                dotSequence.add(dotIdx)
                            }
                            else if (dotSequence.size >= sequence.size) {
                                break
                            }
                            else {
                                dotSequence.clear()
                                dotSequence.add(dotIdx)
                            }

                        }
                    }
                    // dotSequence.println()
                    // sequence.println()
                    if (dotSequence.size >= sequence.size && dotSequence[0] < sequence.last()) {
                        for (dotIdx in dotSequence) {
                            idList[dotIdx] = idList[sequence.first()]
                            idList[sequence.removeFirst()] = -1
                            if (sequence.isEmpty())
                                break
                        }
                        // println(idList)
                    }
                    else {
                        sequence.clear()
                    }
                }
            }
            else {
                index -= 1
            }
        }
        var result = 0.toLong()
        // println(idList)
        for (idx in idList.indices) {
            if (idList[idx] == -1) {
                continue
            }
            result += idx * idList[idx]
        }
        return result
    }

    val testInput = readInput("inputs/Day09_test")
    val part1TestResult : Long = part1(testInput)
    part1TestResult.println()
    check(part1TestResult == 1928.toLong())
    val part2TestResult : Long = part2(testInput)
    part2TestResult.println()
    check(part2TestResult == 2858.toLong())

    val input = readInput("inputs/Day09")
    part1(input).println()
    part2(input).println()
}
