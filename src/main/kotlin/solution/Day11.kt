package solution

import util.getInputAsStringArray

var globalCount = 0

private fun level1(): Int {
    var input = getInputAsStringArray(11).mapIndexed { outerIndex, line -> line.mapIndexed { innerIndex, it -> Octopus(innerIndex, outerIndex, it.toString().toInt(), false) } }

    for(count in 1..500){
        val toBeFlashed = arrayListOf<Octopus>()
        var zeroes = 0
        for(i in input){
            for(j in i){
                if(j.value == 0){
                    zeroes++
                }
                j.flashed = false
                j.value ++
                if(j.value > 9){
                    toBeFlashed.add(j)
                }
            }
        }

        if(zeroes == 100){
            println("Only zeroes at step: ${count - 1}")
            break
        }

        if(toBeFlashed.size > 0) {
            input = flashOctopus(input, toBeFlashed, 0)
        }

        for(i in input){
            for(j in i){
                print(j.value.toString() + " ")
            }
            println()
        }
        println()
    }
    println(globalCount)
    return 0
}

fun flashOctopus(input: List<List<Octopus>>, origins: ArrayList<Octopus>, depth: Int): List<List<Octopus>>{
    val toBeFlashed = arrayListOf<Octopus>()
    for(origin in origins){
        if(!origin.flashed){
            input[origin.y][origin.x].value = 0
            input[origin.y][origin.x].flashed = true
            globalCount++
            val neighbors = getNeighbors(input, origin)

            for(i in neighbors){
                if(!input[i.y][i.x].flashed){
                    input[i.y][i.x].value ++
                }
                if(input[i.y][i.x].value > 9 && !input[i.y][i.x].flashed){
                    toBeFlashed.add(input[i.y][i.x])
                }
            }
        }
    }
    return if(toBeFlashed.size == 0){
        input
    }else{
        flashOctopus(input, toBeFlashed, depth + 1)
    }
}

fun getNeighbors(input: List<List<Octopus>>, origin: Octopus): ArrayList<Octopus>{
    val result = ArrayList<Octopus>()
    try {
        result.add(input[origin.y - 1][origin.x - 1])
    }catch (e: IndexOutOfBoundsException){
    }
    try {
        result.add(input[origin.y][origin.x - 1])
    }catch (e: IndexOutOfBoundsException){
    }
    try {
        result.add(input[origin.y + 1][origin.x - 1])
    }catch (e: IndexOutOfBoundsException){
    }
    try {
        result.add(input[origin.y + 1][origin.x])
    }catch (e: IndexOutOfBoundsException){
    }
    try {
        result.add(input[origin.y + 1][origin.x + 1])
    }catch (e: IndexOutOfBoundsException){
    }
    try {
        result.add(input[origin.y][origin.x + 1])
    }catch (e: IndexOutOfBoundsException){
    }
    try {
        result.add(input[origin.y - 1][origin.x + 1])
    }catch (e: IndexOutOfBoundsException){
    }
    try {
        result.add(input[origin.y - 1][origin.x])
    }catch (e: IndexOutOfBoundsException){
    }
    return result
}

data class Octopus(var x: Int, var y: Int, var value: Int, var flashed: Boolean)

fun main(){
    level1()
}