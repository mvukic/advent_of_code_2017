package day2

import getLines

fun main() {
  val sum = getLines("Day2.txt")
    .map { line -> line.split("\\s+".toRegex()).map { it.toInt() } }
    .map { numbers -> Pair(numbers.maxOrNull() ?: 0, numbers.minOrNull() ?: 0) }
    .map { it.first - it.second }
    .sum()
  println("Sum is: $sum")
}