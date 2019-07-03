package net.cclounge.martianrobots

import net.cclounge.martianrobots.commands.Command
import net.cclounge.martianrobots.commands.Forward
import net.cclounge.martianrobots.commands.Left
import net.cclounge.martianrobots.commands.Right
import net.cclounge.martianrobots.datamodels.Cell
import net.cclounge.martianrobots.datamodels.GridSpec
import net.cclounge.martianrobots.datamodels.Orientation
import net.cclounge.martianrobots.datamodels.Robot
import java.lang.Exception

class InputParser {

    enum class Expected {GRID_SPEC, ROBOT_SPEC, COMMANDS}

    fun parse(inputText: String): Input {
        val lines = inputText.split('\n').map(String::trim)
        var expectedNext: Expected = Expected.GRID_SPEC
        lateinit var gridSpec: GridSpec
        var tmpRobot: Robot? = null
        val robotsAndCommands: MutableList<Pair<Robot, List<Command>>> = mutableListOf()
        lines.forEach { line ->
            val items = line.split("(\\s)+".toRegex()).dropLastWhile { it.isEmpty() }
            when {
                items.isEmpty() -> {} // skip empty lines
                expectedNext == Expected.GRID_SPEC -> {
                    if (items.size != 2) throw Exception("Invalid grid specification.")
                    gridSpec = GridSpec(items[0].toInt(), items[1].toInt())
                    expectedNext = Expected.ROBOT_SPEC
                }
                expectedNext == Expected.ROBOT_SPEC -> {
                    if (items.size != 3) throw Exception("Invalid robot position and orientation.")
                    tmpRobot = Robot(Cell(items[0].toInt(), items[1].toInt()), parseOrientation(items[2]), false)
                    expectedNext = Expected.COMMANDS
                }
                expectedNext == Expected.COMMANDS -> {
                    if (items.size != 1) throw Exception("Invalid commands string.")
                    robotsAndCommands.add(Pair(
                        tmpRobot!!,
                        parseCommands(items[0])
                    ))
                    expectedNext = Expected.ROBOT_SPEC
                }
            }
        }
        return Input(gridSpec, robotsAndCommands)
    }

    private fun parseOrientation(orientationText: String): Orientation = when(orientationText.toUpperCase()) {
        "N" -> Orientation.NORTH
        "E" -> Orientation.EAST
        "S" -> Orientation.SOUTH
        "W" -> Orientation.WEST
        else -> throw Exception("Invalid orientation.")
    }

    private fun parseCommands(commandString: String): List<Command> = commandString.toCharArray().map {
        when(it.toUpperCase()) {
            'L' -> Left()
            'R' -> Right()
            'F' -> Forward()
            else -> throw Exception("Invalid command")
        }
    }

}