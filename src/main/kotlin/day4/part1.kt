package day4

import getLines

fun main() {
  val numberOfValid = getLines("Day4.txt")
    .map { line -> line.split("\\s+".toRegex()) }
    .filter { words -> words.count() == words.toSet().count() }
    .count()
  println("Number of valid is: $numberOfValid")
}