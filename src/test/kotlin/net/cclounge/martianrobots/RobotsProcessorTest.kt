package net.cclounge.martianrobots

import junit.framework.Assert.assertEquals
import net.cclounge.martianrobots.commands.Forward
import net.cclounge.martianrobots.commands.Left
import net.cclounge.martianrobots.commands.Right
import net.cclounge.martianrobots.datamodels.Cell
import net.cclounge.martianrobots.datamodels.GridSpec
import net.cclounge.martianrobots.datamodels.Orientation
import net.cclounge.martianrobots.datamodels.Robot
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RobotsProcessorTest {

    lateinit var processor: RobotsProcessor

    @Before
    fun setup() {
        processor = RobotsProcessor()
    }

    @Test
    fun `the input given as example produces the expected output`() {

        val input = Input(
            GridSpec(5, 3),
            listOf(
                Robot(Cell(1, 1), Orientation.EAST, false) to listOf(
                    Right(), Forward(), Right(), Forward(), Right(), Forward(), Right(), Forward()
                ),
                Robot(Cell(3, 2), Orientation.NORTH, false) to listOf(
                    Forward(), Right(), Right(), Forward(), Left(), Left(), Forward(), Forward(),
                    Right(), Right(), Forward(), Left(), Left()
                ),
                Robot(Cell(0, 3), Orientation.WEST, false) to listOf(
                    Left(), Left(), Forward(), Forward(), Forward(), Left(), Forward(), Left(),
                    Forward(), Left()
                )
            )
        )

        val output = processor.process(input)

        assertEquals(
            listOf(
                Robot(Cell(1, 1), Orientation.EAST, false),
                Robot(Cell(3, 3), Orientation.NORTH, true),
                Robot(Cell(2, 3), Orientation.SOUTH, false)
            ),
            output
        )

    }

}