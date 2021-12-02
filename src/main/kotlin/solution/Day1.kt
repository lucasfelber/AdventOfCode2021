package solution

import util.getInputAsIntArray
import util.getInputAsText
import java.lang.reflect.Proxy

private fun level1(): Int {
    val input = getInputAsIntArray(1)

    var output = 0;

    for(i in 1 until input.size){
        if(input[i] > input[i-1]){
            output++
        }
    }

    return output
}

private fun level2(): Int {
    val input = getInputAsIntArray(1)

    var preparedInput = mutableListOf<Int>()

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
    println(level1())
    println(level2())
}