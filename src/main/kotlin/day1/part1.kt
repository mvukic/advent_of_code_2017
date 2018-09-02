package day1

import asFile

fun main(args: Array<String>) {
    val line: String = ClassLoader.getSystemClassLoader().getResource("Day1.txt").file
            .asFile()
            .readLines()
            .get(0)
    val sum = run(line)
    println("Sum is $sum")
}

private fun run(numbers: String): Int {
    var sum = 0
    numbers.forEachIndexed { index, number ->
        val nextIndex = (index+1) % numbers.length
        if (number == numbers[nextIndex]) {
            sum += number.toString().toInt()
        }
    }
    return sum
}