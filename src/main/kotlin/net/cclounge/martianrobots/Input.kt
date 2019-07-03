package net.cclounge.martianrobots

import net.cclounge.martianrobots.commands.Command
import net.cclounge.martianrobots.datamodels.GridSpec
import net.cclounge.martianrobots.datamodels.Robot

class Input(
    val gridSpec: GridSpec,
    val robotsAndCommands: List<Pair<Robot, List<Command>>>
)