package t.masahide

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFails

/**
 * Created by Masahide on 2016/08/09.
 */
class KontainerTest {

    class TestObject(val id: Int, val name: String) {

    }

    @Before
    fun setUp() {
        koClear()
    }

    @Test
    fun get() {

        koRegister("hoge", { TestObject(1, "aaa") })

        val testObj1: TestObject = koInject("hoge")
        val testObj2: TestObject = koInject("hoge")

        assertNotEquals(testObj1, testObj2)
    }

    @Test
    fun getSingleton() {
        koRegister("hoge", { TestObject(1, "aaa") })

        val testObj1: TestObject = koInjectSingleton("hoge")
        val testObj2: TestObject = koInjectSingleton("hoge")
        assertEquals(testObj1, testObj2)
    }

    @Test
    fun unset() {
        koRegister("hoge", { TestObject(1, "aaa") })
        koUnset("hoge")
        try {
            val testObj: TestObject = koInject("hoge")
            assertFails { }
        } catch(e: Exception) {
            assertEquals("you must register hoge before call get", e.message)
        }
    }
}
