package net.cclounge.martianrobots.datamodels

data class Robot(
    val position: Cell,
    val orientation: Orientation,
    val lost: Boolean
)