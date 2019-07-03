package net.cclounge.martianrobots.commands

import net.cclounge.martianrobots.datamodels.GridSpec
import net.cclounge.martianrobots.datamodels.Robot
import net.cclounge.martianrobots.datamodels.Smells

interface Command {
    
    fun execute(robot: Robot, gridSpec: GridSpec, smells: Smells): Pair<Robot, Smells>

}