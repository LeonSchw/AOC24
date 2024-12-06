
fun main() {

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

    fun getNextPosition (position : Triple<Int, Int, String>, map : MutableList<String>) : Triple<Int, Int, String> {
        val nextPosition = when (position.third) {
            "^" -> if ((position.first-1) >= 0 && map[position.first-1][position.second] == '#') {
                        Triple(position.first, position.second, ">")
                    } else {
                        Triple(position.first-1, position.second, position.third)
                    }
            "v" -> if ((position.first+1) < map.size && map[position.first+1][position.second] == '#') {
                        Triple(position.first, position.second, "<")
                    } else {
                        Triple(position.first+1, position.second, position.third)
                    }
            "<" -> if ((position.second-1) >= 0 && map[position.first][position.second-1] == '#') {
                        Triple(position.first, position.second, "^")
                    } else {
                        Triple(position.first, position.second-1, position.third)
                    }
            ">" -> if ((position.second+1) < map[position.first].length && map[position.first][position.second+1] == '#') {
                        Triple(position.first, position.second, "v")
                    } else {
                        Triple(position.first, position.second+1, position.third)
                    }
            else -> position
        }
        if (nextPosition == position)
            throw IllegalArgumentException("encountered unexpected orientation token: " + position.third)
        map[position.first] = setCharAtIndex(map[position.first], 'X', position.second)
        return nextPosition
    }


    fun part1(input: List<String>): Int {
        var result = 0

        var row = 0
        var column = 0
        for (y in input.indices) {
            val line : String = input[y]
            if (line.contains('^')) {
                row = y
                column = line.indexOf('^')
            }
        }

        var position : Triple<Int, Int, String> = Triple(row, column, "^")
        val map : MutableList<String> = input.toMutableList()

        while (position.first in input.indices && position.second in 0..<input[position.first].length){
            position = getNextPosition(position, map)
        }

        for (line in map ) {
            result += line.count { char -> char == 'X' }
        }

        return result
    }

    fun loops (input : List<String>, initialPosition : Triple<Int, Int, String>) : Boolean {
        val map : MutableList<String> = input.toMutableList()
        val seenPairs : MutableSet<Pair<Triple<Int, Int, String>, Triple<Int, Int, String>>> = mutableSetOf()
        var position = initialPosition
        while (position.first in input.indices && position.second in 0..<input[position.first].length){
            val newPosition = getNextPosition(position, map)
            val pair : Pair<Triple<Int, Int, String>, Triple<Int, Int, String>> = Pair(position, newPosition)
            if (seenPairs.contains(pair)){
                //println(map)
                return true
            }
            position = newPosition
            seenPairs.add(pair)
        }

        return false
    }

    fun part2(input: List<String>): Int {
        var result = 0

        var row = 0
        var column = 0
        for (y in input.indices) {
            val line : String = input[y]
            if (line.contains('^')) {
                row = y
                column = line.indexOf('^')
            }
        }

        val map : MutableList<String> = input.toMutableList()

        val position : Triple<Int, Int, String> = Triple(row, column, "^")

        for (y in map.indices) {
            for (x in map[y].indices) {
                if (y== position.first && x == position.second)
                    continue
                if (map[y][x] == '#')
                    continue
                val tmp : String = map[y]
                map[y] = setCharAtIndex(input[y], '#', x)
                if (loops(map, position))
                    result += 1
                map[y] = tmp
            }
        }

        return result
    }

    val testInput = readInput("inputs/Day06_test")
    val part1TestResult : Int = part1(testInput)
    part1TestResult.println()
    check(part1TestResult == 41)
    val part2TestResult : Int = part2(testInput)
    part2TestResult.println()
    check(part2TestResult == 6)

    val input = readInput("inputs/Day06")
    part1(input).println()
    part2(input).println()
}
