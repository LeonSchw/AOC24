fun main() {
    fun part1(input: List<String>): Int {
        val expression = Regex("XMAS|SAMX")
        var count = 0
        for (line in input) {
            val matches = expression.findAll(line)
            count += matches.count()
        }
        for (column in 0..<input[0].length) {
            val line = buildString { for (row in input) {append(row[column])} }
            count += expression.findAll(line).count()
        }
        for (i in 0..<input[0].length-3) {
            val diagToRight = buildString {
                var column = i
                var row = 0
                while (column < input[0].length && row < input.size) {
                    append(input[row][column])
                    row += 1
                    column += 1
                }
            }
        }
        return count
    }

    fun part1New(input: List<String>): Int {
        var count = 0

        for (row in input.indices) {
            for (column in 0..<input[row].length) {
                if (input[row][column] == 'X') {
                    if (row-3 >= 0 && column-3 >= 0) {
                        if (input[row-3][column-3] == 'S' && input[row-2][column-2] == 'A' && input[row-1][column-1] == 'M'){
                            count += 1
                        }
                    }
                    if (row-3 >= 0 && column+3 < input[row].length) {
                        if (input[row-3][column+3] == 'S' && input[row-2][column+2] == 'A' && input[row-1][column+1] == 'M'){
                            count += 1
                        }
                    }
                    if (row+3 < input.size && column+3 < input[row].length) {
                        if (input[row+3][column+3] == 'S' && input[row+2][column+2] == 'A' && input[row+1][column+1] == 'M'){
                            count += 1
                        }
                    }
                    if (row+3 < input.size && column-3 >= 0) {
                        if (input[row+3][column-3] == 'S' && input[row+2][column-2] == 'A' && input[row+1][column-1] == 'M'){
                            count += 1
                        }
                    }
                    if (row-3 >= 0) {
                        if (input[row-3][column] == 'S' && input[row-2][column] == 'A' && input[row-1][column] == 'M'){
                            count += 1
                        }
                    }
                    if (row+3 < input.size) {
                        if (input[row+3][column] == 'S' && input[row+2][column] == 'A' && input[row+1][column] == 'M'){
                            count += 1
                        }
                    }
                    if (column-3 >= 0) {
                        if (input[row][column-3] == 'S' && input[row][column-2] == 'A' && input[row][column-1] == 'M'){
                            count += 1
                        }
                    }
                    if (column+3 < input[row].length) {
                        if (input[row][column+3] == 'S' && input[row][column+2] == 'A' && input[row][column+1] == 'M'){
                            count += 1
                        }
                    }
                }
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0

        for (row in 1..<input.size-1) {
            for (column in 1..<input[row].length-1) {
                if (input[row][column] == 'A') {
                    var mases = 0
                    if (input[row-1][column-1] == 'M' && input[row+1][column+1] == 'S') {
                        mases += 1
                    }
                    if (input[row-1][column-1] == 'S' && input[row+1][column+1] == 'M') {
                        mases += 1
                    }
                    if (input[row+1][column-1] == 'M' && input[row-1][column+1] == 'S') {
                        mases += 1
                    }
                    if (input[row+1][column-1] == 'S' && input[row-1][column+1] == 'M') {
                        mases += 1
                    }
                    if (mases == 2){
                        count += 1
                    }
                }
            }
        }

        return count
    }

    val testInput = readInput("inputs/Day04_test")
    val part1TestResult = part1New(testInput)
    part1TestResult.println()
    check(part1TestResult == 18)
    check(part2(testInput) == 9)

    val input = readInput("inputs/Day04")
    part1New(input).println()
    part2(input).println()
}
