package solution.day7

import util.Solution
import kotlin.math.abs

fun solve1(): Any? {
    val input = Solution.getInputAsText().split(",").map { it.toInt() }.toIntArray()
    var output = Int.MAX_VALUE
    for(i in input){
        var temp = 0
        for(j in input){
            temp += abs(i - j)
        }
        if(temp < output){
            output = temp
        }
    }
    return output
}

fun solve2(): Any? {
    val input = Solution.getInputAsText().split(",").map { it.toInt() }.toIntArray()
    var output = Int.MAX_VALUE
    for(i in 0 until input.maxOrNull()!!){
        var temp = 0
        for(j in input){
            temp += calcCost(abs(i - j))
        }
        if(temp < output){
            output = temp
        }
    }
    return output
}

fun calcCost(input: Int): Int{
    var sum = 0
    for(i in 0 until input + 1){
        sum += i
    }
    return sum
}

fun main(){
    Solution.run(7, ::solve1)
    Solution.run(7, ::solve2)
}