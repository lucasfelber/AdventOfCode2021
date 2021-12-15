package solution.day1

import util.Solution

fun solve1(): Any? {
    val input = Solution.getInputAsIntArray()

    var output = 0;

    for(i in 1 until input.size){
        if(input[i] > input[i-1]){
            output++
        }
    }

    return output
}

fun solve2(): Any? {
    val input = Solution.getInputAsIntArray()

    val preparedInput = mutableListOf<Int>()

    var output = 0

    for(i in 0 until input.size - 2){
        preparedInput.add(input[i] + input[i + 1] + input[i + 2])
    }

    for(i in 1 until preparedInput.size){
        if(preparedInput[i] > preparedInput[i-1]){
            output++
        }
    }

    return output
}

fun main() {
    Solution.run(1, ::solve1)
    Solution.run(1, ::solve2)
}