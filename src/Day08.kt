
fun main() {

    fun findAntinodes (map : List<String>, frequency : Char): MutableSet<Pair<Int,Int>> {
        val antennaPositions : MutableList<Pair<Int, Int>> = mutableListOf()
        for (row in map.indices) {
            for (column in map[row].indices) {
                if (map[row][column] == frequency) {
                    antennaPositions.add(Pair(row, column))
                }
            }
        }
        //antennaPositions.println()
        val antinodePositions : MutableSet<Pair<Int, Int>> = mutableSetOf()
        for (idx in antennaPositions.indices) {
            val position : Pair<Int, Int> = antennaPositions[idx]
            for (idx2 in idx+1..<antennaPositions.size) {
                val diffX : Int = position.second - antennaPositions[idx2].second
                val diffY : Int = position.first - antennaPositions[idx2].first
                //println("diff : ($diffY|$diffX)")
                val xPlus : Int = position.second + diffX
                val yPlus : Int = position.first + diffY
                //println("pos1 : ($yPlus|$xPlus)")
                if (0 <= yPlus && yPlus < map.size && 0 <= xPlus && xPlus < map[0].length) {
                    antinodePositions.add(Pair(yPlus, xPlus))
                }
                val xMinus : Int = antennaPositions[idx2].second - diffX
                val yMinus : Int = antennaPositions[idx2].first - diffY
                //println("pos2 : ($yMinus|$xMinus)")
                if (0 <= yMinus && yMinus < map.size && 0 <= xMinus && xMinus < map[0].length) {
                    antinodePositions.add(Pair(yMinus, xMinus))
                }
            }
        }
        return antinodePositions
    }

    fun part1(input: List<String>): Int {
        val antinodePositions : MutableSet<Pair<Int, Int>> = mutableSetOf()
        val frequencies : MutableSet<Char> = mutableSetOf()
        val regex = Regex("\\d|[a-z]|[A-Z]")
        for (line in input) {
            val matches : Sequence<MatchResult> = regex.findAll(line)
            for (match in matches){
                frequencies.add(match.value[0])
            }
        }
        //frequencies.println()
        for (frequency in frequencies) {
            antinodePositions.addAll(findAntinodes(input, frequency))
        }
        val map : MutableList<String> = input.toMutableList()
        for (position in antinodePositions) {
            map[position.first] = setCharAtIndex(map[position.first], '#', position.second)
        }
        //antinodePositions.println()
        /*for (line in map) {
            line.println()
        }*/
        return antinodePositions.size
    }

    fun findAntinodesWithHarmonics (map : List<String>, frequency : Char): MutableSet<Pair<Int,Int>> {
        val antennaPositions : MutableList<Pair<Int, Int>> = mutableListOf()
        for (row in map.indices) {
            for (column in map[row].indices) {
                if (map[row][column] == frequency) {
                    antennaPositions.add(Pair(row, column))
                }
            }
        }
        //antennaPositions.println()
        val antinodePositions : MutableSet<Pair<Int, Int>> = mutableSetOf()
        antinodePositions.addAll(antennaPositions)
        for (idx in antennaPositions.indices) {
            val position : Pair<Int, Int> = antennaPositions[idx]
            for (idx2 in idx+1..<antennaPositions.size) {
                val diffX : Int = position.second - antennaPositions[idx2].second
                val diffY : Int = position.first - antennaPositions[idx2].first
                //println("diff : ($diffY|$diffX)")
                var xPlus : Int = position.second + diffX
                var yPlus : Int = position.first + diffY
                //println("pos1 : ($yPlus|$xPlus)")
                while (0 <= yPlus && yPlus < map.size && 0 <= xPlus && xPlus < map[0].length) {
                    antinodePositions.add(Pair(yPlus, xPlus))
                    xPlus += diffX
                    yPlus += diffY
                }
                var xMinus : Int = antennaPositions[idx2].second - diffX
                var yMinus : Int = antennaPositions[idx2].first - diffY
                //println("pos2 : ($yMinus|$xMinus)")
                while (0 <= yMinus && yMinus < map.size && 0 <= xMinus && xMinus < map[0].length) {
                    antinodePositions.add(Pair(yMinus, xMinus))
                    xMinus -= diffX
                    yMinus -= diffY
                }
            }
        }
        return antinodePositions
    }

    fun part2(input: List<String>): Int {
        val antinodePositions : MutableSet<Pair<Int, Int>> = mutableSetOf()
        val frequencies : MutableSet<Char> = mutableSetOf()
        val regex = Regex("\\d|[a-z]|[A-Z]")
        for (line in input) {
            val matches : Sequence<MatchResult> = regex.findAll(line)
            for (match in matches){
                frequencies.add(match.value[0])
            }
        }
        //frequencies.println()
        for (frequency in frequencies) {
            antinodePositions.addAll(findAntinodesWithHarmonics(input, frequency))
        }
        val map : MutableList<String> = input.toMutableList()
        for (position in antinodePositions) {
            map[position.first] = setCharAtIndex(map[position.first], '#', position.second)
        }
        //antinodePositions.println()
        /*for (line in map) {
            line.println()
        }*/
        return antinodePositions.size
    }

    val testInput = readInput("inputs/Day08_test")
    val part1TestResult : Int = part1(testInput)
    part1TestResult.println()
    check(part1TestResult == 14)
    val part2TestResult : Int = part2(testInput)
    part2TestResult.println()
    check(part2TestResult == 34)

    val input = readInput("inputs/Day08")
    part1(input).println()
    part2(input).println()
}
