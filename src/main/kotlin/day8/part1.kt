package day8

import getLines

fun main() {
  val instructions = parse(getLines("Day8.txt"))
  val registers = run(instructions)
  println("Max value is ${registers.values.maxOrNull()}")
}

private fun parse(lines: List<String>): List<Instruction> {
  return lines
    .map {
      // Split line
      val parts = it.split(" ".toRegex())
      // Map line to instruction
      Instruction(
        operand = parts[0],
        operation = parts[1],
        value = parts[2].toInt(),
        quest_operand = parts[4],
        quest_operation = parts[5],
        quest_value = parts[6].toInt()
      )
    }
}

private fun run(instructions: List<Instruction>): MutableMap<String, Int> {
  val registers: MutableMap<String, Int> = mutableMapOf()

  instructions.forEach { instruction ->

    // Initialize register value to 0
    registers.putIfAbsent(instruction.operand, 0)
    registers.putIfAbsent(instruction.quest_operand, 0)

    // check if condition is true
    if (validate(registers, instruction)) {
      when (instruction.operation) {
        "inc" -> registers[instruction.operand] = registers.getOrDefault(instruction.operand, 0) + instruction.value
        "dec" -> registers[instruction.operand] = registers.getOrDefault(instruction.operand, 0) - instruction.value
        else -> throw Error("Unknown operation!")
      }
    }
  }


  return registers
}

private fun validate(registers: MutableMap<String, Int>, instruction: Instruction): Boolean {
  val left: Int = registers.getOrDefault(instruction.quest_operand, 0)
  val right: Int = instruction.quest_value

  return when (instruction.quest_operation) {
    "<" -> left < right
    ">" -> left > right
    "<=" -> left <= right
    ">=" -> left >= right
    "!=" -> left != right
    "==" -> left == right
    else -> throw Error("Unknown operation!")
  }
}

data class Instruction(
  val operand: String = "",
  val operation: String = "",
  val value: Int = 0,
  val quest_operand: String = "",
  val quest_operation: String = "",
  val quest_value: Int = 0
)
