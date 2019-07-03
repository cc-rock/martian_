package net.cclounge.martianrobots

import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainTest {

    @Mock
    lateinit var theList: List<String>

    @Test
    fun example_test() {
        whenever(theList.size).thenReturn(5)
        assertEquals(4, theList.size)
    }

}