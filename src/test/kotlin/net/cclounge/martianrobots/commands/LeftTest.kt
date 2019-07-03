package net.cclounge.martianrobots.commands

import net.cclounge.martianrobots.datamodels.Cell
import net.cclounge.martianrobots.datamodels.GridSpec
import net.cclounge.martianrobots.datamodels.Orientation
import net.cclounge.martianrobots.datamodels.Robot
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class LeftTest {

    lateinit var robot: Robot
    lateinit var gridSpec: GridSpec
    lateinit var command: Left

    @Before
    fun setup() {
        gridSpec = GridSpec(5, 5)
        robot = Robot(Cell(1, 1), Orientation.NORTH, false)
        command = Left()
    }

    @Test
    fun `turning left from north leads to west and does not change the position and smells`() {
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(orientation = Orientation.WEST),
            result.first
        )
        assertEquals(emptySet(), result.second)
    }

    @Test
    fun `turning left from west leads to south and does not change the position and smells`() {
        robot = robot.copy(orientation = Orientation.WEST)
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(orientation = Orientation.SOUTH),
            result.first
        )
        assertEquals(emptySet(), result.second)
    }

    @Test
    fun `turning left from south leads to east and does not change the position and smells`() {
        robot = robot.copy(orientation = Orientation.SOUTH)
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(orientation = Orientation.EAST),
            result.first
        )
        assertEquals(emptySet(), result.second)
    }

    @Test
    fun `turning left from east leads to north and does not change the position and smells`() {
        robot = robot.copy(orientation = Orientation.EAST)
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(orientation = Orientation.NORTH),
            result.first
        )
        assertEquals(emptySet(), result.second)
    }

    @Test
    fun `turning left on a lost robot returns an unchanged status`() {
        robot = robot.copy(lost = true)
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot,
            result.first
        )
        assertEquals(emptySet(), result.second)
    }

}