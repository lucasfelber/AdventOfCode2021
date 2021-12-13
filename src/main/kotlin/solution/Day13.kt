package solution

import util.getInputAsStringArray

private fun level1(): Int{
    val input = getInputAsStringArray(13).partition { !it.contains("fold")}
    var positions = input.first.dropLast(1).map { it.split(',').let { (a,b) -> Position(a.toInt(), b.toInt()) } }
    val instructions = input.second.map { it.split("along ").drop(1).joinToString().split('=') }

    for(instruction in instructions){
        if(instruction[0] == "y"){
            positions.forEach {
                if (it.y > instruction[1].toInt()){
                    it.y = instruction[1].toInt() - (it.y - instruction[1].toInt())
                }
            }
        }else{
            positions.forEach {
                if (it.x > instruction[1].toInt()){
                    it.x = instruction[1].toInt() - (it.x - instruction[1].toInt())
                }
            }
        }
    }

    positions = positions.distinct()

    for(i in 0..positions.maxOf { it.y }){
        for(j in 0..positions.maxOf { it.x }){
            if(positions.contains(Position(j, i))) print('#') else print('.')
        }
        println()
    }

    return positions.size
}

data class Position(var x: Int, var y: Int)

fun main(){
    println(level1())
}