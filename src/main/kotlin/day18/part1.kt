package day18

import asFile

fun main(args: Array<String>) {

    val registers: MutableMap<String, Int> = mutableMapOf()

    val instructions = ClassLoader.getSystemClassLoader().getResource("Day18.txt").file
            .asFile()
            .readLines()
            .toList()

    var index = 0
    var lastFreq = 0
    var first = true
    while(true){
        if(index > instructions.size) break
        val split = instructions[index].split(" ")
        when (split[0]) {
            "set" -> {
                val register = split[1]
                val valueOrReg = split[2].toIntOrNull() ?: split[2]
                if(valueOrReg is Int){
                    registers[register] = valueOrReg
                } else {
                    registers[register] = registers.getOrDefault(valueOrReg, 0)
                }
                index++
            }
            "add" -> {
                val register = split[1]
                val valueOrReg = split[2].toIntOrNull() ?: split[2]
                val oldValue = registers.getOrDefault(register, 0)
                if(valueOrReg is Int){
                    registers[register] = oldValue + valueOrReg
                } else {
                    registers[register] = oldValue + registers.getOrDefault(valueOrReg, 0)
                }
                index++
            }
            "mul" -> {
                val register = split[1]
                val valueOrReg = split[2].toIntOrNull() ?: split[2]
                val oldValue = registers.getOrDefault(register, 0)
                if(valueOrReg is Int){
                    registers[register] = oldValue * valueOrReg
                } else {
                    registers[register] = oldValue * registers.getOrDefault(valueOrReg, 0)
                }
                index++
            }
            "mod" -> {
                val register = split[1]
                val valueOrReg = split[2].toIntOrNull() ?: split[2]
                val oldValue = registers.getOrDefault(register, 0)
                if(valueOrReg is Int){
                    registers[register] = oldValue % valueOrReg
                } else {
                    registers[register] = oldValue % registers.getOrDefault(valueOrReg, 0)
                }
                index++
            }
            "snd" -> {
                val valueOrReg = split[1].toIntOrNull() ?: split[1]
                if(valueOrReg is Int){
                    lastFreq = valueOrReg
                } else {
                    lastFreq = registers.getOrDefault(valueOrReg, 0)
                }
                index++
            }
            "rcv" -> {
                val reg1OrValue = split[1].toIntOrNull() ?: split[1]
                if(reg1OrValue is Int) {
                    if(reg1OrValue > 0 && first) {
                        first = false
                        println("Recover $lastFreq")
                    }
                } else {
                    if(registers.getOrDefault(reg1OrValue, 0) > 0 && first) {
                        first = false
                        println("Recover $lastFreq")
                    }
                }
                index++
            }
            "jgz" -> {
                val reg1OrValue = split[1].toIntOrNull() ?: split[1]
                val reg2OrValue = split[2].toIntOrNull() ?: split[2]
                if(reg1OrValue is Int) {
                    if(reg1OrValue > 0) {
                        index += reg2OrValue as? Int ?: registers.getOrDefault(reg2OrValue, 0)
                    } else {
                        index++
                    }
                } else {
                    if(registers.getOrDefault(reg1OrValue, 0) > 0) {
                        index += reg2OrValue as? Int ?: registers.getOrDefault(reg2OrValue, 0)
                    } else {
                        index++
                    }
                }
            }
        }
    }

}