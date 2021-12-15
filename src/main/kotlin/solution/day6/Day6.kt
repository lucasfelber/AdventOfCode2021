package solution.day6

import util.Solution

fun solve1(): Any? {
    val input = Solution.getInputAsText().split(",").map { it.toInt() }.toMutableList()

    repeat(80){
        for(i in input.indices){
            if(input[i] == 0){
                input.add(8)
                input[i] = 6
            }else{
                input[i] --
            }
        }
    }
    return input.size
}

fun solve2(): Any? {
    val input = Solution.getInputAsText().split(",").map { it.toInt() }
    val map = input.groupingBy { it }.eachCount().mapValues { it.value.toLong() }.toMutableMap().withDefault { 0L }.apply {
        for (i in 0 until 256) {
            val zeroes = getValue(i)

            this[i] = 0L
            this[i + 7] = getValue(i + 7) + zeroes
            this[i + 9] = getValue(i + 9) + zeroes
        }
    }
    return map.values.sum()
}

fun main(){
    Solution.run(6, ::solve1)
    Solution.run(6, ::solve2)
}