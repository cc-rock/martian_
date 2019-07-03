package net.cclounge.martianrobots.datamodels

data class Cell(val x: Int, val y: Int) {

    override fun toString(): String = "$x $y"

}