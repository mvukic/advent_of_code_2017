package day2

import asFile


fun main(args: Array<String>) {

    val sum = ClassLoader.getSystemClassLoader().getResource("Day2.txt").file
            .asFile()
            .readLines()
            .map {
                it.split("\\s+".toRegex()).map {
                    it.toInt()
                }
            }.map{
                onlyTwoDivisible(it)
            }.sum()

    println("Sum is $sum")

}

fun onlyTwoDivisible(numbers: List<Int>): Int{

    numbers.forEach { current ->
        numbers.filter { it != current }.forEach {
            if(it % current == 0) {
                val result = it/current
                println("Divisible $it / $current = $result")
                return result
            }
            if(current % it == 0) {
                val result = current/it
                println("Divisible $current / $it = $result")
                return result
            }
        }
    }

    // This will never be reached
    return 0
}