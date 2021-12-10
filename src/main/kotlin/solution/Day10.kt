package solution

import util.getInputAsStringArray
import java.math.BigInteger

private fun level1(): BigInteger{
    var input = getInputAsStringArray(10).toMutableList()
    val openChunks: MutableList<Chunk> = mutableListOf()
    var illegalBrackets = ""

    var newInput = input.toMutableList()

    outerLoop@for(i in input){
        openChunks.clear()
        innerLoop@for(j in i){
            if(j == '(' || j == '[' || j == '{' || j == '<'){
                openChunks.add(Chunk(j))
            }
            if(j == ')' || j == ']' || j == '}' || j == '>'){
                if(matchingType(openChunks.last().type, j)){
                    openChunks.removeLast()
                    continue@innerLoop
                }
                illegalBrackets += j
                newInput.remove(i)
                continue@outerLoop
            }
        }
    }

    println(illegalBrackets.count{ it == ')' } * 3 + illegalBrackets.count{ it == ']' } * 57 + illegalBrackets.count{ it == '}' } * 1197 + illegalBrackets.count{ it == '>' } * 25137)

    input = newInput
    val lineCompletion: MutableList<String> = mutableListOf()

    outerLoop@for(i in input){
        openChunks.clear()
        innerLoop@for(j in i){
            if(j == '(' || j == '[' || j == '{' || j == '<'){
                openChunks.add(Chunk(j))
            }
            if(j == ')' || j == ']' || j == '}' || j == '>'){
                if(matchingType(openChunks.last().type, j)){
                    openChunks.removeLast()
                    continue@innerLoop
                }
            }
        }
        var lineComplete = ""
        for(chunk in openChunks.reversed()){
            lineComplete += reverseType(chunk.type)
        }
        lineCompletion.add(lineComplete)
    }

    val lineEvaluation: MutableList<BigInteger> = mutableListOf()

    for(i in lineCompletion){
        var lineScore: BigInteger = BigInteger.valueOf(0)
        for(j in i){
            lineScore *= BigInteger.valueOf(5)
            if(j == ')') lineScore += BigInteger.valueOf(1)
            else if(j == ']') lineScore += BigInteger.valueOf(2)
            else if(j == '}') lineScore += BigInteger.valueOf(3)
            else if(j == '>') lineScore += BigInteger.valueOf(4)
        }
        lineEvaluation.add(lineScore)
    }

    return lineEvaluation.sorted()[lineEvaluation.size/2]
}

fun reverseType(type: Char): Char{
    when (type){
        '(' -> return ')'
        '[' -> return ']'
        '{' -> return '}'
        '<' -> return '>'
    }
    return ' '
}

fun matchingType(type1: Char, type2: Char): Boolean{
    if(type1 == '(' && type2 == ')') return true
    else if(type1 == '[' && type2 == ']') return true
    else if(type1 == '{' && type2 == '}') return true
    else if(type1 == '<' && type2 == '>') return true
    return false
}

data class Chunk(var type: Char)

fun main(){
    println(level1())
}