package solution.day12

import util.Solution

fun solve1(): Any? {
    val input = Solution.getInputAsStringArray().map { it.split('-').let { (a,b) -> Cave(a) to Cave(b) } }
    val graph = CaveGraph(input)
    graph.recurse(Cave("start"), Cave("end"), emptyMap(), emptyList(), false)
    return graph.paths.size
}

fun solve2(): Any? {
    val input = Solution.getInputAsStringArray().map { it.split('-').let { (a,b) -> Cave(a) to Cave(b) } }
    val graph = CaveGraph(input)
    graph.recurse(Cave("start"), Cave("end"), emptyMap(), emptyList(), true)
    return graph.paths.size
}

class CaveGraph(private val caveList: List<Pair<Cave, Cave>>) {
    private val nodes: MutableMap<Cave, List<Cave>> = getUniqueCaves().associateWith { getNodes(it) }.toMutableMap()

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
            for(i in nodes[from]!!.filter { outer -> outer.id != "start" && visited.getOrDefault(outer, 0) < if (visited.any { it.value >= 2 }) 1 else 2}){
                recurse(i, to, newVisited, newPath, part2)
            }
        }
    }
}

data class Cave(val id: String, val big: Boolean = id.uppercase() == id)

fun main(){
    Solution.run(12, ::solve1)
    Solution.run(12, ::solve2)
}