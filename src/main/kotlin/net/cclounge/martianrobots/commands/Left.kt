package net.cclounge.martianrobots.commands

import net.cclounge.martianrobots.datamodels.GridSpec
import net.cclounge.martianrobots.datamodels.Orientation
import net.cclounge.martianrobots.datamodels.Robot
import net.cclounge.martianrobots.datamodels.Smells

class Left : Command {

    override fun execute(robot: Robot, gridSpec: GridSpec, smells: Smells): Pair<Robot, Smells> {

        // Implementation choice: you can try to execute a command on a lost robot, it will just be ignored
        if (robot.lost) return Pair(robot, smells)

        return Pair(
            robot.copy(
                orientation = when (robot.orientation) {
                    Orientation.NORTH -> Orientation.WEST
                    Orientation.WEST -> Orientation.SOUTH
                    Orientation.SOUTH -> Orientation.EAST
                    Orientation.EAST -> Orientation.NORTH
                }
            ),
            smells
        )
    }

}