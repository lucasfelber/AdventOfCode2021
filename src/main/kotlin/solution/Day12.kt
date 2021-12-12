package solution

import util.getInputAsStringArray

private fun level1(): Int{
    val input = getInputAsStringArray(12).map { it.split('-').let { (a,b) -> Cave(a) to Cave(b) } }

    val graph = CaveGraph(input)
    graph.recurse(Cave("start"), Cave("end"), emptyMap(), emptyList(), false)
    for(i in graph.paths){
        for(j in i){
            print(j.id + ",")
        }
        println()
    }
    println(graph.paths.size)
    graph.paths = mutableListOf()
    graph.recurse(Cave("start"), Cave("end"), emptyMap(), emptyList(), true)
    var strings = graph.paths.map { it.map { it.id }.joinToString ( "," ) }//.sorted()
    for(i in strings){
        println(i)
    }
    println(graph.paths.size)

    return 0
}

class CaveGraph(private val caveList: List<Pair<Cave, Cave>>, ) {
    val nodes: MutableMap<Cave, List<Cave>> = getUniqueCaves().associateWith { getNodes(it) }.toMutableMap()

    private fun getUniqueCaves(): List<Cave>{
        val result = arrayListOf<Cave>()
        caveList.forEach {
            if(!result.contains(it.first)) result.add(it.first)
            if(!result.contains(it.second)) result.add(it.second)
        }
        return result
    }

    private fun getNodes(origin: Cave): List<Cave>{
        val result = arrayListOf<Cave>()
        caveList.forEach {
            if(it.first == origin) result.add(it.second)
            if(it.second == origin) result.add(it.first)
        }
        return result
    }

    var paths = mutableListOf<List<Cave>>()

    private fun checkCavesTwice(input: List<Cave>): Boolean{
        return input.filter { !it.big }.groupingBy { it.id }.eachCount().filter { it.value > 1 }.size > 1
    }

    fun recurse(from: Cave, to: Cave, visited: Map<Cave, Int>, path: List<Cave>, part2: Boolean){
        val newPath = path + from

        if(checkCavesTwice(newPath) && part2){
            return
        }

        if(from == to){
            paths += path
            return
        }

        val newVisited = if (!from.big){
            visited + (from to visited.getOrDefault(from, 0) + 1)
        }else visited

        if(!part2){
            for(i in nodes[from]!!.filter { visited.getOrDefault(it, 0) < 1 }){
                recurse(i, to, newVisited, newPath, part2)
            }
        }else{
            val nodesWithoutStart = nodes[from]!!.filter { cave -> cave.id != "start" }
            val visitedTwice = visited.any { it.value >= 2 }
            var finalNodes: List<Cave>
            if (visitedTwice){
                finalNodes = nodesWithoutStart.filter { visited.getOrDefault(it, 0) < 1 }
            }else{
                finalNodes = nodesWithoutStart.filter { visited.getOrDefault(it, 0) < 2 }
            }
            for(i in finalNodes){
                recurse(i, to, newVisited, newPath, part2)
            }
        }
    }
}

data class Cave(val id: String, val big: Boolean = id.uppercase() == id)

fun main(){
    level1()
}