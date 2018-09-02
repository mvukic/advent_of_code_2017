package day3

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val x = 289326
    val time = measureTimeMillis {
        calculatePart1(x)
    }
    println("Time was $time")
}

fun calculatePart1(x: Int) {
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

/**
 * Min value of number in square i
 */
fun minInSquare(i: Int) = when(i) {
    1 -> 1
    else -> ( maxInSquare(i) - count(i) ) + 1
}

/**
 * Max value of number in square i
 */
fun maxInSquare(i: Int) = size(i) * size(i)


/**
 * Number of number in square i
 */
fun count(i: Int) = when(i) {
    1 -> 1
    else -> 8 * ( i - 1 )
}

/**
 * Height/width of square i
 */
fun size(i: Int) = 2*i-1


/**
 * Returns true if x is in the square i
 */
fun inSquare(x: Int, i: Int) = minInSquare(i) <= x && maxInSquare(i) >= x

/**
 * Coordinates of min value in square i
 */
fun coordinatesStart(i: Int) = when(i) {
    1 -> Pair(0,0)
    else -> Pair(i-1, 2-i)
}

/**
 * Coordinates of max value number in square i
 */
fun coordinatesEnd(i: Int) = Pair(i-1, 1-i)

/**
 * Calculates Manhattan distance between two points
 */
fun manhattanDistance(point1: Pair<Int, Int>, point2: Pair<Int, Int>): Int {
    return Math.abs(point1.first - point2.first) + Math.abs(point1.second - point2.second)
}