package solution

import util.getInputAsIntArray
import util.getInputAsStringArray
import util.getInputAsText
import java.lang.reflect.Proxy

private fun level1(): Int{
    val input = getInputAsStringArray(2)
    var distance = 0
    var depth = 0

    for(i in input){
        val lineArray = i.split(" ").toTypedArray()
        when (lineArray[0]) {
            "forward" -> distance += lineArray[1].toInt()
            "down" -> depth += lineArray[1].toInt()
            "up" -> depth -= lineArray[1].toInt()
        }
    }

    return distance * depth
}

private fun level2(): Int{
    val input = getInputAsStringArray(2)
    var aim = 0
    var distance = 0
    var depth = 0

    for(i in input){
        val lineArray = i.split(" ").toTypedArray()
        when (lineArray[0]) {
            "forward" -> {
                distance += lineArray[1].toInt()
                depth += aim * lineArray[1].toInt()
            }
            "down" -> aim += lineArray[1].toInt()
            "up" -> aim -= lineArray[1].toInt()
        }
    }

    return distance * depth
}

fun main(){
    println(level1())
    println(level2())
}