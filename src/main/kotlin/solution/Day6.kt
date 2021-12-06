package solution

import util.getInputAsText

private fun level1(): Int{
    val input = getInputAsText(6).split(",").map { it.toInt() }.toMutableList()

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

private fun level2(): Long{
    val input = getInputAsText(6).split(",").map { it.toInt() }
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
    println(level1())
    println(level2())
}