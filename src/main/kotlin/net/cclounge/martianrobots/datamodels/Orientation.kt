package net.cclounge.martianrobots.datamodels

enum class Orientation {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    override fun toString(): String = when(this) {
        NORTH -> "N"
        EAST -> "E"
        SOUTH -> "S"
        WEST -> "W"
    }
}