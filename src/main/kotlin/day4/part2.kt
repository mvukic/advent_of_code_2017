package day4

import getLines

fun main() {
  val numberOfValid = getLines("Day4.txt")
    .map { line -> line.split("\\s+".toRegex()) }
    .filter { words -> hasAnagramsInList(words) }
    .count()
  println("Number of valid is: $numberOfValid")
}

fun hasAnagramsInList(words: List<String>): Boolean {
  val sortedWordsByLetters: List<String> = words.map {
    it.toList().sorted().toString()
  }.toList()

  words.forEachIndexed { index, word ->
    val sortedWord = word.toList().sorted().toString()
    val filtered = sortedWordsByLetters.filterIndexed { i, _ -> i != index }
    if (filtered.contains(sortedWord)) return true
  }

  return false
}