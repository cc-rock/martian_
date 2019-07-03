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
class ForwardTest {

    lateinit var robot: Robot
    lateinit var gridSpec: GridSpec
    lateinit var command: Forward

    @Before
    fun setup() {
        gridSpec = GridSpec(5, 5)
        robot = Robot(Cell(1, 1), Orientation.NORTH, false)
        command = Forward()
    }

    @Test
    fun `Going forward when heading north increases the y coordinate by 1 and does not change orientation and smells`() {
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(position = Cell(1, 2)),
            result.first
        )
        assertEquals(emptySet(), result.second)
    }

    @Test
    fun `Going forward when heading east increases the x coordinate by 1 and does not change orientation and smells`() {
        robot = robot.copy(orientation = Orientation.EAST)
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(position = Cell(2, 1)),
            result.first
        )
        assertEquals(emptySet(), result.second)
    }

    @Test
    fun `Going forward when heading south descreases the y coordinate by 1 and does not change orientation and smells`() {
        robot = robot.copy(orientation = Orientation.SOUTH)
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(position = Cell(1, 0)),
            result.first
        )
        assertEquals(emptySet(), result.second)
    }

    @Test
    fun `Going forward when heading west descreases the x coordinate by 1 and does not change orientation and smells`() {
        robot = robot.copy(orientation = Orientation.WEST)
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(position = Cell(0, 1)),
            result.first
        )
        assertEquals(emptySet(), result.second)
    }

    @Test
    fun `Going forward when heading north and at the upper edge of the grid causes lost robot and added smell`() {
        robot = robot.copy(position = Cell(1, 5))
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(lost = true),
            result.first
        )
        assertEquals(setOf(Cell(1, 5)), result.second)
    }

    @Test
    fun `Going forward when heading east and at the right edge of the grid causes lost robot and added smell`() {
        robot = robot.copy(position = Cell(5, 1), orientation = Orientation.EAST)
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(lost = true),
            result.first
        )
        assertEquals(setOf(Cell(5, 1)), result.second)
    }

    @Test
    fun `Going forward when heading south and at the lower edge of the grid causes lost robot and added smell`() {
        robot = robot.copy(position = Cell(1, 0), orientation = Orientation.SOUTH)
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(lost = true),
            result.first
        )
        assertEquals(setOf(Cell(1, 0)), result.second)
    }

    @Test
    fun `Going forward when heading west and at the left edge of the grid causes lost robot and added smell`() {
        robot = robot.copy(position = Cell(0, 1), orientation = Orientation.WEST)
        val result = command.execute(robot, gridSpec, emptySet())
        assertEquals(
            robot.copy(lost = true),
            result.first
        )
        assertEquals(setOf(Cell(0, 1)), result.second)
    }

    @Test
    fun `Going forward when heading north and at the upper edge of the grid, with smell, returns same status`() {
        robot = robot.copy(position = Cell(1, 5), orientation = Orientation.NORTH)
        val smells = setOf(Cell(1, 5))
        val result = command.execute(robot, gridSpec, smells)
        assertEquals(
            robot,
            result.first
        )
        assertEquals(smells, result.second)
    }

    @Test
    fun `Going forward when heading east and at the right edge of the grid, with smell, returns same status`() {
        robot = robot.copy(position = Cell(5, 1), orientation = Orientation.EAST)
        val smells = setOf(Cell(5, 1))
        val result = command.execute(robot, gridSpec, smells)
        assertEquals(
            robot,
            result.first
        )
        assertEquals(smells, result.second)
    }

    @Test
    fun `Going forward when heading south and at the lower edge of the grid, with smell, returns same status`() {
        robot = robot.copy(position = Cell(1, 0), orientation = Orientation.SOUTH)
        val smells = setOf(Cell(1, 0))
        val result = command.execute(robot, gridSpec, smells)
        assertEquals(
            robot,
            result.first
        )
        assertEquals(smells, result.second)
    }

    @Test
    fun `Going forward when heading west and at the left edge of the grid, with smell, returns same status`() {
        robot = robot.copy(position = Cell(0, 1), orientation = Orientation.WEST)
        val smells = setOf(Cell(0, 1))
        val result = command.execute(robot, gridSpec, smells)
        assertEquals(
            robot,
            result.first
        )
        assertEquals(smells, result.second)
    }

    @Test
    fun `Going forward on a lost robot returns same status`() {
        robot = robot.copy(lost = true)
        val smells = emptySet<Cell>()
        val result = command.execute(robot, gridSpec, smells)
        assertEquals(
            robot,
            result.first
        )
        assertEquals(smells, result.second)
    }

}