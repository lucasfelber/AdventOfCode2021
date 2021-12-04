package solution

import util.getInputAsStringArray

private fun level1(): Int{
    val input = getInputAsStringArray(3)
    val preppedInput = arrayListOf<List<Int>>()

    for(i in input.indices){
        val result = input[i].chunked(1).map {it.toInt()}
        preppedInput.add(result)
    }

    var output = IntArray(preppedInput[0].size)

    for(i in preppedInput){
        for(j in 0 until preppedInput[0].size){
            output[j] += i[j]
        }
    }

    return calcGamma(output, input.size) * calcEpsilon(output, input.size)
}

fun calcGamma(Sums: IntArray, InputSize: Int): Int {
    val bitOutput = IntArray(Sums.size)

    for (i in Sums.indices) {
        if (Sums[i] > InputSize / 2) {
            bitOutput[i] = 1
        } else {
            bitOutput[i] = 0
        }
    }

    val binaryString = bitOutput.joinToString(separator = "") { it.toString() }

    return Integer.parseInt(binaryString, 2)
}

fun calcEpsilon(Sums: IntArray, InputSize: Int): Int {
    val bitOutput = IntArray(Sums.size)

    for (i in Sums.indices) {
        if (Sums[i] < InputSize / 2) {
            bitOutput[i] = 1
        } else {
            bitOutput[i] = 0
        }
    }

    val binaryString = bitOutput.joinToString(separator = "") { it.toString() }

    return Integer.parseInt(binaryString, 2)
}

private fun level2(): Int{
    val input = getInputAsStringArray(3)
    var preppedInput = arrayListOf<List<Int>>()

    for(i in input.indices){
        val result = input[i].chunked(1).map {it.toInt()}
        preppedInput.add(result)
    }

    var oxygenGenerator = preppedInput

    for(i in oxygenGenerator[0].indices){
        oxygenGenerator = removeUnwantedItem(oxygenGenerator, getMostCommonBit(oxygenGenerator, i), i)
    }

    val binaryOxygenGenerator = oxygenGenerator[0].joinToString(separator = "") { it.toString() }
    val intOxygenGenerator = Integer.parseInt(binaryOxygenGenerator, 2)

    var co2Scrubber = preppedInput

    for(i in co2Scrubber[0].indices){
        if(co2Scrubber.size == 1){
            break;
        }
        co2Scrubber = removeUnwantedItem(co2Scrubber, getLeastCommonBit(co2Scrubber, i), i)
    }

    val binaryCo2Scrubber = co2Scrubber[0].joinToString(separator = "") { it.toString() }
    val intCo2Scrubber = Integer.parseInt(binaryCo2Scrubber, 2)

    return(intOxygenGenerator * intCo2Scrubber)
}

fun removeUnwantedItem(preppedInput: ArrayList<List<Int>>, commonBit: Int, depth: Int): ArrayList<List<Int>>{
    val result = arrayListOf<List<Int>>()

    for(i in preppedInput){
        if(i[depth] == commonBit){
            result.add(i)
        }
    }

    return result
}

fun getMostCommonBit(preppedInput: ArrayList<List<Int>>, index: Int): Int{
    var ones = 0
    var zeros = 0

    for(i in preppedInput){
        if(i[index] == 1){
            ones++
        }else{
            zeros++
        }
    }
    return if(ones >= zeros){
        1
    }else{
        0
    }
}

fun getLeastCommonBit(preppedInput: ArrayList<List<Int>>, index: Int): Int{
    var ones = 0
    var zeros = 0

    for(i in preppedInput){
        if(i[index] == 1){
            ones++
        }else{
            zeros++
        }
    }
    return if(ones < zeros){
        1
    }else{
        0
    }
}

fun main(){
    print(level1())
    println(level2())
}