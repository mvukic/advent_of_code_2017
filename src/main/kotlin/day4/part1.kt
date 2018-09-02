package day4

import asFile


fun main(args: Array<String>) {

    val numberOfValid = ClassLoader.getSystemClassLoader().getResource("Day4.txt").file
            .asFile()
            .readLines()
            .map {
                val allWords = it.split("\\s+".toRegex())
                val setOfWords = allWords.toSet()
                if(allWords.count() == setOfWords.count()) 1 else 0
            }.sum()

    println("Number of valid is: $numberOfValid")

}