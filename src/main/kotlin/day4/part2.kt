package day4

import asFile


fun main(args: Array<String>) {

    val numberOfValid = ClassLoader.getSystemClassLoader().getResource("Day4.txt").file
            .asFile()
            .readLines()
            .mapIndexed { index, s ->
                val words = s.split("\\s+".toRegex())
                if(hasAnagramsInList(words)) 0 else 1
            }.sum()

    println("Number of valid is: $numberOfValid")

}

fun hasAnagramsInList(words : List<String>): Boolean {

    val sortedWordsByLetters: List<String> = words.map {
        it.toList().sorted().toString()
    }.toList()

    words.forEachIndexed { index, word ->
        val sortedWord = word.toList().sorted().toString()
        val filtered = sortedWordsByLetters.filterIndexed { i, _ -> i != index }
        if(filtered.contains(sortedWord)) return true
    }

    return false
}