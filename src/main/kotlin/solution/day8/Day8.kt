package solution.day8

import util.Solution

fun solve1(): Any? {
    val input = Solution.getInputAsStringArray().map { it.split(" | ").drop(1).toString().replace("[", "").replace("]", "") }.map { it.split(" ").filter { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }}
    var output = 0
    for(i in input){
        output += i.count()
    }
    return output
}

fun solve2(): Any? {
    val input = Solution.getInputAsStringArray().map { it.split(" | ").dropLast(1).toString().replace("[", "").replace("]", "") }.map { it.split(" ")}
    val outputNumbers = Solution.getInputAsStringArray().map { it.split(" | ").drop(1).toString().replace("[", "").replace("]", "") }.map { it.split(" ")}

    var output = 0
    for(i in input.indices){
        val numberLetters = getNumbersInLetters(input[i])
        val digit = getDigitAsString(numberLetters)
        output += getNumberSequence(outputNumbers[i], digit)
    }
    return output
}

fun getDigitAsString(input: Array<String>): String{
    val result = CharArray(size = 7)
    result[0] = getRemainingLetter(input[7], input[1])
    result[2] = getRemainingLetter(input[1], input[6])
    result[5] = getRemainingLetter(input[1], result[2].toString())
    result[4] = getRemainingLetter(input[8], input[9])
    result[1] = getRemainingLetter(input[8], input[3] + result[4])
    result[6] = getRemainingLetter(input[0], result[0].toString() + result[2] + result[5] + result[4] + result[1])
    result[3] = getRemainingLetter(input[8], input[0])
    return result.joinToString("")
}

fun getRemainingLetter(base: String, part: String): Char{
    var result = base
    for(i in part){
        result = result.replace(i.toString(), "")
    }
    return result.single()
}

fun getNumbersInLetters(input: List<String>): Array<String>{
    val mutableInput = input.toMutableList()
    val result = Array(10) { _ -> ""}
    for(i in input){
        when (i.length){
            2 -> {
                result[1] = i
                mutableInput.remove(i)
            }
            3 -> {
                result[7] = i
                mutableInput.remove(i)
            }
            4 -> {
                result[4] = i
                mutableInput.remove(i)
            }
            7 -> {
                result[8] = i
                mutableInput.remove(i)
            }
        }
    }
    for(i in input){
        if(i.length == 6 && !getContains(i, result[1])){
            result[6] = i
            mutableInput.remove(i)
        }else if(i.length == 6 && getContains(i, result[7]) && getContains(i, result[4])){
            result[9] = i
            mutableInput.remove(i)
        }else if(i.length == 5 && getContains(i, result[7])){
            result[3] = i
            mutableInput.remove(i)
        }
    }
    for(i in mutableInput){
        if(i.length == 6){
            result[0] = i
        }else if(i.length == 5 && getContainsRest(result[9], i) == 1){
            result[5] = i
        }else if(i.length == 5 && getContainsRest(result[9], i) != 1){
            result[2] = i
        }
    }
    return result
}

fun getContainsRest(base: String, part: String): Int{
    var counter = base.length
    for(i in base){
        for(j in part){
            if(i == j){
                counter --
            }
        }
    }
    return counter
}

fun getContains(base: String, part: String): Boolean{
    var counter = part.length
    for(i in base){
        for(j in part){
            if(i == j){
                counter --
            }
        }
    }
    return counter == 0
}

fun getNumericValue(input: String, digit: String): Int{
    when (input.toCharArray().sorted().joinToString("")){
        getSortedDigitNumber(digit, 0) -> return 0
        getSortedDigitNumber(digit, 1) -> return 1
        getSortedDigitNumber(digit, 2) -> return 2
        getSortedDigitNumber(digit, 3) -> return 3
        getSortedDigitNumber(digit, 4) -> return 4
        getSortedDigitNumber(digit, 5) -> return 5
        getSortedDigitNumber(digit, 6) -> return 6
        getSortedDigitNumber(digit, 7) -> return 7
        getSortedDigitNumber(digit, 8) -> return 8
        getSortedDigitNumber(digit, 9) -> return 9
    }
    return -1
}

fun getSortedDigitNumber(digit: String, number: Int): String{
    when (number){
        0 -> return charArrayOf(digit[0], digit[1], digit[2], digit[4], digit[5], digit[6]).sorted().joinToString("")
        1 -> return charArrayOf(digit[2], digit[5]).sorted().joinToString("")
        2 -> return charArrayOf(digit[0], digit[2], digit[3], digit[4], digit[6]).sorted().joinToString("")
        3 -> return charArrayOf(digit[0], digit[2], digit[3], digit[5], digit[6]).sorted().joinToString("")
        4 -> return charArrayOf(digit[1], digit[2], digit[3], digit[5]).sorted().joinToString("")
        5 -> return charArrayOf(digit[0], digit[1], digit[3], digit[5], digit[6]).sorted().joinToString("")
        6 -> return charArrayOf(digit[0], digit[1], digit[3], digit[4], digit[5], digit[6]).sorted().joinToString("")
        7 -> return charArrayOf(digit[0], digit[2], digit[5]).sorted().joinToString("")
        8 -> return charArrayOf(digit[0], digit[1], digit[2], digit[3], digit[4], digit[5], digit[6]).sorted().joinToString("")
        9 -> return charArrayOf(digit[0], digit[1], digit[2], digit[3], digit[5], digit[6]).sorted().joinToString("")
    }
    return ""
}

fun getNumberSequence(input: List<String>, digit: String): Int{
    var numberString = ""
    input.forEach { numberString += getNumericValue(it, digit) }
    return numberString.toInt()
}

fun main(){
    Solution.run(8, ::solve1)
    Solution.run(8, ::solve2)
}