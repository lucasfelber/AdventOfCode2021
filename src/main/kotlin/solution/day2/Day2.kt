package solution.day2

import util.Solution

fun solve1(): Any? {
    val input = Solution.getInputAsStringArray()
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

fun solve2(): Any? {
    val input = Solution.getInputAsStringArray()
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
    Solution.run(2, ::solve1)
    Solution.run(2, ::solve2)
}