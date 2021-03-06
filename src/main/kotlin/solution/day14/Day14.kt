package solution.day14

import util.Solution

fun solve1(): Any? {
    val input = Solution.getInputAsStringArray()
    var template = input.take(1).joinToString()
    val insertionRules = input.drop(2).map { it.split(" -> ").let { (a,b) -> a to b } }

    repeat(10) {
        template = template.zipWithNext { a, b -> a.toString() + insertionRules.find { it.first == a.toString() + b }?.second.toString() + b.toString()}.joinToString().replace(Regex(", ."), "")
    }

    val occurrences = template.groupingBy { it }.eachCount()
    return occurrences.maxByOrNull { it.value }?.value!! - occurrences.minByOrNull { it.value }?.value!!
}

@OptIn(ExperimentalStdlibApi::class)
fun solve2(): Any? {
    val input = Solution.getInputAsStringArray()

    val template = input.first().windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }
    val insertionRules = input.drop(2).groupBy({ it.substringBefore(" -> ") }, {it.substringAfter(" -> ")}).mapValues { it.value.single() }

    val pairsCount = (0 until 40).fold(template) { current, _ ->
        buildMap {
            current.forEach { (pair, count) ->
                val first = pair[0] + insertionRules.getValue(pair)
                val second = insertionRules.getValue(pair) + pair[1]
                put(first, getOrDefault(first, 0) + count)
                put(second, getOrDefault(second, 0) + count)
            }
        }
    }

    val occurrences = buildMap<Char, Long> {
        put(input.first()[0], 1)
        pairsCount.forEach { (pair, count) ->
            put(pair[1], getOrDefault(pair[1], 0) + count)
        }
    }

    return occurrences.maxOf { it.value } - occurrences.minOf { it.value }
}

fun main(){
    Solution.run(14, ::solve1)
    Solution.run(14, ::solve2)
}