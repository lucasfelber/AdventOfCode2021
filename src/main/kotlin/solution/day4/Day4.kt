package solution.day4

import util.Solution

fun solve1(): Any? {
    var input = Solution.getInputAsStringArray().toMutableList()
    val numbersDrawn = input[0].split(",").map{it.toInt()}
    input.removeAt(0)
    input.removeAt(0)
    input.removeIf { obj: String -> obj == ""}

    input = input.map{ it.trim().replace("\\s+".toRegex(), ",")}.toMutableList()//.windowed(size = 5, step = 6)//.map{it.split(",").map { it.toInt() }}

    val boards = input.map{it.split(",").map { BoardItem(it.toInt(), false) }}.windowed(5, 5)

    for(i in numbersDrawn.indices){
        for(j in boards){
            for(k in j){
                for(l in k){
                    if(l.item == numbersDrawn[i]){
                        l.marked = true
                    }
                }
            }
            if(checkWinner(j) != 0) {
                return checkWinner(j) * numbersDrawn[i]
            }
        }
    }
    return 0
}

fun solve2(): Any? {
    var input = Solution.getInputAsStringArray().toMutableList()
    val numbersDrawn = input[0].split(",").map { it.toInt() }
    input.removeAt(0)
    input.removeAt(0)
    input.removeIf { obj: String -> obj == "" }

    input = input.map { it.trim().replace("\\s+".toRegex(), ",") }
        .toMutableList()//.windowed(size = 5, step = 6)//.map{it.split(",").map { it.toInt() }}

    val boards = input.map { it.split(",").map { BoardItem(it.toInt(), false) } }.windowed(5, 5)

    var finalBoards = boards.toMutableList()

    var index = 0

    for (i in numbersDrawn.indices) {
        var boardsLeft = 0
        for (j in boards) {
            for (k in j) {
                for (l in k) {
                    if (l.item == numbersDrawn[i]) {
                        l.marked = true
                    }
                }
            }
            if(finalBoards.size == 1 && checkWinner(finalBoards[0]) != 0){
                index = i
                break
            }
            if (checkWinner(j) != 0) {
                finalBoards.remove(j)
            }
        }

        if (finalBoards.size == 1 && checkWinner(finalBoards[0]) != 0) {
            return calcWinnerValue(finalBoards[0]) * numbersDrawn[index]
        }
    }
    return 0
}

data class BoardItem(val item: Int, var marked: Boolean)

fun checkWinner(board: List<List<BoardItem>>): Int{
    if(checkVertical(board)){
        return calcWinnerValue(board)
    }
    for(i in board){
        if(checkHorizontal(i)){
            return calcWinnerValue(board)
        }
    }
    return 0
}

fun calcWinnerValue(board: List<List<BoardItem>>): Int{
    var sum = 0
    for(i in board){
        for(j in i){
            if(!j.marked){
                sum += j.item
            }
        }
    }
    return sum
}

fun checkHorizontal(items: List<BoardItem>): Boolean{
    var count = 0
    for(i in items){
        if(i.marked){
            count++
        }
    }
    return count == items.size
}

fun checkVertical(lines: List<List<BoardItem>>): Boolean{
    var count = 0
    for(i in lines.indices){
        for(j in lines[i].indices){
            if(lines[j][i].marked) {
                count++
            }
        }
        if(count == lines.size){
            return true
        }
        count = 0
    }
    return false
}

fun main(){
    Solution.run(4, ::solve1)
    Solution.run(4, ::solve2)
}