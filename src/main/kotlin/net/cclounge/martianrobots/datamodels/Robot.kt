package net.cclounge.martianrobots.datamodels

data class Robot(
    val position: Cell,
    val orientation: Orientation,
    val lost: Boolean
) {
    override fun toString(): String
        = "$position $orientation ${if (lost) "LOST" else ""}"

}