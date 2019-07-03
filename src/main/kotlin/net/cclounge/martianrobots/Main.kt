package net.cclounge.martianrobots

fun main(args: Array<String>) {
    println("Ciao ${if (args.size > 0) args[0] else "" }")
}