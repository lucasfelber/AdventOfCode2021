package solution

import util.getInputAsText
import kotlin.math.abs

private fun level1(): Int{
    val input = getInputAsText(7).split(",").map { it.toInt() }.toIntArray()
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

private fun level2(): Int{
    val input = getInputAsText(7).split(",").map { it.toInt() }.toIntArray()
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
    //println(level1())
    println(level2())
}