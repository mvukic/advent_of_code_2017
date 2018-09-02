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
                it.max()!! - it.min()!!
            }.sum()

    println("Sum is: $sum")

}