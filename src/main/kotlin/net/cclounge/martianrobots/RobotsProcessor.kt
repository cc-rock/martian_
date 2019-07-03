package net.cclounge.martianrobots

import net.cclounge.martianrobots.datamodels.Robot
import net.cclounge.martianrobots.datamodels.Smells

/**
 * Processes the input by executing the given commands for each robot, and returning the list
 * of robots in their final positions.
 */
class RobotsProcessor {

    fun process(input: Input): List<Robot> {
        var smells: Smells = emptySet()
        return input.robotsAndCommands.map { robotAndCommands ->
            robotAndCommands.second.fold(Pair(robotAndCommands.first, smells)) { acc, command ->
                command.execute(acc.first, input.gridSpec, acc.second)
            }.apply { smells = second }.first
        }
    }

}