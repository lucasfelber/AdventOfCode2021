package solution.day5

import util.Solution

fun solve2(): Any? {
    val input = Solution.getInputAsStringArray().map { it.split(" -> ").map { it.split(",") }.map{ Point(it[0].toInt(), it[1].toInt(),0) } }
    val diagram = Array(getBiggestXValue(input) + 1){ IntArray(getBiggestYValue(input) + 1) }

    for(i in input){
        val points = getPoints(i[0],i[1])
        for(point in points){
            diagram[point.y][point.x] ++
        }
    }
    var counter = 0
    for(i in diagram){
        for(j in i){
            if(j > 1){
                counter++
            }
        }
    }
    return counter
}

data class Point(val x: Int, val y: Int, val value: Int)

fun getPoints(a: Point, b: Point): List<Point>{
    val result = arrayListOf<Point>()
    if(a.x == b.x){
        if(a.y > b.y){
            for(i in a.y downTo b.y){
                result.add(Point(a.x,i,0))
            }
        }else{
            for(i in a.y until b.y + 1){
                result.add(Point(a.x,i,0))
            }
        }
    }else if (a.y == b.y){
        if(a.x > b.x){
            for(i in a.x downTo b.x){
                result.add(Point(i,a.y,0))
            }
        }else{
            for(i in a.x until b.x + 1){
                result.add(Point(i,a.y,0))
            }
        }
    }else if (a.x > b.x && a.y > b.y){
        for(i in 0 until a.x - b.x + 1){
            result.add(Point(a.x - i, a.y - i, 0))
        }
    }else if (a.x < b.x && a.y < b.y){
        for(i in 0 until b.x - a.x + 1){
            result.add(Point(a.x + i, a.y + i, 0))
        }
    }else if (a.x > b.x && a.y < b.y){
        for(i in 0 until a.x - b.x + 1){
            result.add(Point(a.x - i, a.y + i, 0))
        }
    }else if (a.x < b.x && a.y > b.y){
        for(i in 0 until b.x - a.x + 1){
            result.add(Point(a.x + i, a.y - i, 0))
        }
    }
    return result
}

fun getBiggestXValue(points: List<List<Point>>): Int{
    var value = 0
    for(i in points){
        for(j in i){
            if(j.x > value){
                value = j.x
            }
        }
    }
    return value
}

fun getBiggestYValue(points: List<List<Point>>): Int{
    var value = 0
    for(i in points){
        for(j in i){
            if(j.y > value){
                value = j.y
            }
        }
    }
    return value
}

fun main(){
    Solution.run(5, ::solve2)
    Solution.run(5, ::solve2)
}