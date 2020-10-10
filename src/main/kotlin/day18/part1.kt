package day18

import getLines

fun main() {
  val registers: MutableMap<String, Int> = mutableMapOf()
  val instructions: List<String> = getLines("Day18.txt")

  var index = 0
  var lastFreq = 0
  var first = true
  while (true) {
    if (index > instructions.size) break

    val instructionParts = instructions[index].split(" ")
    when (instructionParts[0]) {
      "set" -> {
        val register = instructionParts[1]
        if (instructionParts[2].toIntOrNull() !== null) {
          registers[register] = instructionParts[2].toInt()
        } else {
          registers[register] = registers.getOrDefault(instructionParts[2], 0)
        }
        index++
      }
      "add" -> {
        val register = instructionParts[1]
        val oldValue = registers.getOrDefault(register, 0)
        if (instructionParts[2].toIntOrNull() !== null) {
          registers[register] = oldValue + instructionParts[2].toInt()
        } else {
          registers[register] = oldValue + registers.getOrDefault(instructionParts[2], 0)
        }
        index++
      }
      "mul" -> {
        val register = instructionParts[1]
        val oldValue = registers.getOrDefault(register, 0)
        if (instructionParts[2].toIntOrNull() !== null) {
          registers[register] = oldValue * instructionParts[2].toInt()
        } else {
          registers[register] = oldValue * registers.getOrDefault(instructionParts[2], 0)
        }
        index++
      }
      "mod" -> {
        val register = instructionParts[1]
        val oldValue = registers.getOrDefault(register, 0)
        if (instructionParts[2].toIntOrNull() !== null) {
          registers[register] = oldValue % instructionParts[2].toInt()
        } else {
          registers[register] = oldValue % registers.getOrDefault(instructionParts[2], 0)
        }
        index++
      }
      "snd" -> {
        lastFreq = if (instructionParts[1].toIntOrNull() !== null) {
          instructionParts[1].toInt()
        } else {
          registers.getOrDefault(instructionParts[1], 0)
        }
        index++
      }
      "rcv" -> {
        if (instructionParts[1].toIntOrNull() !== null) {
          if (instructionParts[1].toInt() > 0 && first) {
            first = false
            println("Recover $lastFreq")
          }
        } else {
          if (registers.getOrDefault(instructionParts[1], 0) > 0 && first) {
            first = false
            println("Recover $lastFreq")
          }
        }
        index++
      }
      "jgz" -> {
        if (instructionParts[1].toIntOrNull() !== null) {
          if (instructionParts[1].toInt() > 0) {
            index += if(instructionParts[2].toIntOrNull() !== null) instructionParts[2].toInt() else registers.getOrDefault(instructionParts[2], 0)
          } else {
            index++
          }
        } else {
          if (registers.getOrDefault(instructionParts[1], 0) > 0) {
            index += if(instructionParts[2].toIntOrNull() !== null) instructionParts[2].toInt() else registers.getOrDefault(instructionParts[2], 0)
          } else {
            index++
          }
        }
      }
    }
  }

}