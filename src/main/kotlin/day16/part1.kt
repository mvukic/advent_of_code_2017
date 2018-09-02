package day16

import asFile
import swapAtNames
import swapAtPositions

fun main(args: Array<String>) {
    var programs: MutableList<Char> = ('a'..'p').toMutableList()
    val line: String = ClassLoader.getSystemClassLoader().getResource("Day16.txt").file
            .asFile()
            .readLines()
            .get(0)
    val sequence = line.splitToSequence(",")

    sequence.forEach {
        if(it.startsWith("s")){
            val spinSize = it.takeLast(it.length-1).toInt()
            val lastN = programs.takeLast(spinSize).toMutableList()
            programs = (lastN + programs.dropLast(spinSize)).toMutableList()
        } else if(it.startsWith("x")) {
            // x3/4 -> Pair(3,4)
            val byPosition: Pair<Int, Int> = it.takeLast(it.length-1).split("/").zipWithNext { a, b -> Pair(a.toInt(),b.toInt()) }[0]
            programs.swapAtPositions(byPosition)
        } else if (it.startsWith("p")) {
            val byName: Pair<Char,Char> = it.takeLast(it.length-1).split("/").zipWithNext { a, b -> Pair(a.toCharArray()[0],b.toCharArray()[0]) }[0]
            programs.swapAtNames<Char>(byName)
        }
    }

    println(programs.joinToString(""))
}

