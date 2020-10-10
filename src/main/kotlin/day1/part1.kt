package day1

import getLines

fun main() {
  val line: String = getLines("Day1.txt").first()
  val sum = run(line)
  println("Sum is $sum")
}

private fun run(numbers: String): Int {
  var sum = 0
  numbers.forEachIndexed { index, number ->
    val nextIndex = (index + 1) % numbers.length
    if (number == numbers[nextIndex]) {
      sum += number.toString().toInt()
    }
  }
  return sum
}