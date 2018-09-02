package day3

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val x = 289326
    val time = measureTimeMillis {
        calculatePart2(x)
    }
    println("Time was $time")
}

fun calculatePart2(x: Int) {
    val square = generateSequence(1) { it + 1 }
            .filter { inSquare(x, it) }
            .first()

    val min = minInSquare(square)
    val max = maxInSquare(square)
    val end = coordinatesEnd(square)

    var delta = -1
    var side = 1
    var current = end.copy()
    val size = size(square)
    val numbers = (min until max).toMutableList()

    numbers.add(0, max)
    val coordinates = numbers.asSequence().map {
        val currentOld = current.copy()
        delta += 1
        if(delta == size - 1) {
            side += 1
            delta = 0
        }
        when(side) {
            1 -> current = Pair(current.first, current.second + 1)
            2 -> current = Pair(current.first - 1, current.second )
            3 -> current = Pair(current.first, current.second - 1)
            4 -> current = Pair(current.first + 1, current.second)
        }
        Pair(it, currentOld)
    }.filter{it.first == x }.first().second
    val distance = manhattanDistance(Pair(0, 0), coordinates)
    println("Distance between 1->(0,0) and $x->$coordinates is $distance")
}
