package net.cclounge.martianrobots.commands

import net.cclounge.martianrobots.datamodels.*

class Forward: Command {

    override fun execute(robot: Robot, gridSpec: GridSpec, smells: Smells): Pair<Robot, Smells> {

        // Implementation choice: you can try to execute a command on a lost robot, it will just be ignored
        if (robot.lost) return Pair(robot, smells)

        val newPosition = when(robot.orientation) {
            Orientation.NORTH -> Cell(robot.position.x, robot.position.y + 1)
            Orientation.EAST -> Cell(robot.position.x + 1, robot.position.y)
            Orientation.SOUTH -> Cell(robot.position.x, robot.position.y - 1)
            Orientation.WEST -> Cell(robot.position.x - 1, robot.position.y)
        }

        return when {
            isInsideGrid(newPosition, gridSpec) -> Pair(robot.copy(position = newPosition), smells)
            smells.contains(robot.position) -> Pair(robot, smells)
            else -> Pair(robot.copy(lost = true), smells + robot.position)
        }

    }

    private fun isInsideGrid(pos: Cell, gridSpec: GridSpec): Boolean
            = pos.x >=0 && pos.x <= gridSpec.width && pos.y >= 0 && pos.y <= gridSpec.height

}