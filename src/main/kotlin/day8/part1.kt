package day8

import asFile

fun main(args: Array<String>) {

    val instructions = parse(ClassLoader.getSystemClassLoader().getResource("Day8.txt").file)
    val registers = run(instructions)
    println("Max value is ${registers.values.max()}")
}

private fun parse(program: String): List<Instruction> {
    return  program.asFile().readLines()
            .map {
                // Split line
                val splitted = it.split(" ".toRegex())
                // Map line to instruction
                Instruction(
                        operand = splitted[0],
                        operation = splitted[1],
                        value = splitted[2].toInt(),
                        quest_operand = splitted[4],
                        quest_operation = splitted[5],
                        quest_value = splitted[6].toInt()
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
        if(validate(registers, instruction)){
            when (instruction.operation) {
                "inc" -> registers[instruction.operand] = registers.getOrDefault(instruction.operand, 0) + instruction.value
                "dec" -> registers[instruction.operand] = registers.getOrDefault(instruction.operand, 0) - instruction.value
                else -> throw Error("Unknown operation!")
            }
        }
    }


    return registers
}

private fun validate(registers: MutableMap<String, Int> ,instruction: Instruction): Boolean {
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
