package solution

import util.getInputAsStringArray

private fun level1(): Int{
    var input = getInputAsStringArray(9).map { it.chunked(1).map { it.toInt() } }
    var output = 0
    for(i in input.indices){
        for(j in input[i].indices){
            if(checkIfLower(input, i, j)){
                output += input[i][j] + 1
            }
        }
    }
    return output
}

fun checkIfLower(input: List<List<Int>>, i: Int, j: Int): Boolean{
    var sum = 0
    var maxSum = 4
    if(i > 0){
        if(input[i-1][j] > input[i][j]){
            sum++
        }
    }else{
        maxSum--
    }
    if(i < input.size - 1){
        if(input[i+1][j] > input[i][j]){
            sum++
        }
    }else{
        maxSum--
    }
    if(j > 0){
        if(input[i][j-1] > input[i][j]){
            sum++
        }
    }else{
        maxSum--
    }
    if(j < input[i].size - 1){
        if(input[i][j+1] > input[i][j]){
            sum++
        }
    }else{
        maxSum--
    }
    if(maxSum == sum){
        return true
    }
    return false
}

private fun level2(): Int{
    var input = getInputAsStringArray(9).mapIndexed { indexOuter, line -> line.chunked(1).mapIndexed { indexInner, it -> Location(indexInner, indexOuter, it.toInt(), false) } }
//    for(i in input){
//        println(i)
//    }
    var lowestLocations = arrayListOf<Location>()

    for(i in input){
        for(j in i){
            if(checkIfLowestNeighbor(j, getNeighbors(input, j))){
                lowestLocations.add(j)
            }
        }
    }

    for(i in lowestLocations){
        input[i.y][i.x].visited = true
    }

    var basinSizes = arrayListOf<Int>()
    for(i in lowestLocations){
        var startingLocation = arrayListOf(i)
        basinSizes.add(getBasinSize(input, startingLocation, 0).size)
    }

    return basinSizes.sorted().drop(basinSizes.size - 3).reduce { acc, i -> acc * i }
}

fun checkIfLowestNeighbor(origin: Location, arr: ArrayList<Location>): Boolean{
    for(i in arr){
        if(origin.value > i.value){
            return false
        }
    }
    return true
}

fun getBasinSize(input: List<List<Location>>, visitedLocations: ArrayList<Location>, depth: Int): ArrayList<Location>{
    var newVisitedLocations = ArrayList<Location>(visitedLocations)
    for(i in visitedLocations){
        for(j in getNeighbors(input, i)){
            newVisitedLocations.add(j)
            input[j.y][j.x].visited = true
        }
    }
    if(newVisitedLocations.size == visitedLocations.size){
        return visitedLocations
    }else{
        return getBasinSize(input, newVisitedLocations, depth + 1)
    }
}

fun getNeighbors(input: List<List<Location>>, origin: Location): ArrayList<Location>{
    var arr = arrayListOf<Location>()
    var count = 0
    try {
        if(input[origin.y - 1][origin.x].value < 9 && !input[origin.y - 1][origin.x].visited) {
            arr.add(input[origin.y - 1][origin.x])
        }
    }catch (e: IndexOutOfBoundsException){
    }
    try {
        if(input[origin.y][origin.x + 1].value < 9 && !input[origin.y][origin.x + 1].visited) {
            arr.add(input[origin.y][origin.x + 1])
        }
    }catch (e: IndexOutOfBoundsException){
    }
    try {
        if(input[origin.y + 1][origin.x].value < 9 && !input[origin.y + 1][origin.x].visited){
            arr.add(input[origin.y + 1][origin.x])
        }
    }catch (e: IndexOutOfBoundsException){
    }
    try {
        if(input[origin.y][origin.x - 1].value < 9 && !input[origin.y][origin.x - 1].visited){
            arr.add(input[origin.y][origin.x - 1])
        }
    }catch (e: IndexOutOfBoundsException){
    }
    return arr
}

data class Location(var x: Int, var y: Int, var value: Int, var visited: Boolean)

fun main(){
    println(level1())
    println(level2())
}