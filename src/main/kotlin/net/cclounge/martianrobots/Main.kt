package net.cclounge.martianrobots

import java.io.File

fun main(args: Array<String>) {

    val inputFileName = if (args.size > 0) {
        args[0]
    } else {
        "src/main/resources/input.txt"
    }

    val inputText: String = try {
        File(inputFileName).readText()
    } catch (t: Throwable) {
        println("Error reading input file.")
        return
    }

    val input: Input = try {
        InputParser().parse(inputText)
    } catch (t: Throwable) {
        println("Error parsing the input file.")
        return
    }

    // Process input and print each robot's final status
    RobotsProcessor().process(input).forEach {
        println(it)
    }

}