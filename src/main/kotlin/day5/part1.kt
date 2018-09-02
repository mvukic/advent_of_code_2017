package day5

import asFile

fun main(args: Array<String>) {

    val commands = ClassLoader.getSystemClassLoader().getResource("Day5.txt").file
            .asFile()
            .readLines()
            .map {
                it.toInt()
            }.toMutableList()

    var index = 0
    var steps = 0

    while (index >= 0 && index < commands.count()){
//        println("Step: $steps")
//        println("Index is $index")
//        println("Current value is ${commands[index]}")
//        println()

        // save current index
        val currentIndex = index

        // move backward/forward
        index += commands[currentIndex]

        // increase value att current command
        commands[currentIndex]++

        steps++


    }

    println("Number of steps is $steps")

}