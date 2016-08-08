package t.masahide

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by Masahide on 2016/08/09.
 */
class KontainerTest{

    @Before
    fun setUp(){
        Kontainer.clear()
    }

    @Test
    fun get(){

        Kontainer.put("hoge",fun(): TestObject {
           return TestObject(1,"aa")
        })
        Kontainer.put("any",fun(): Any{
            return Any()
        })

        assertEquals(Kontainer.get<TestObject>("hoge"),Kontainer.get<TestObject>("hoge"))
        assertEquals(Kontainer.get<Any>("any"),Kontainer.get<Any>("any"))

    }

    @Test
    fun remakeInstance(){

        Kontainer.put("hoge",fun(): TestObject {
            return TestObject(1,"aa")
        })
        Kontainer.put("any",fun(): Any{
            return Any()
        })

        assertNotEquals(Kontainer.get<TestObject>("hoge"),Kontainer.remakeInstance<TestObject>("hoge"))
        assertNotEquals(Kontainer.get<Any>("any"),Kontainer.remakeInstance<Any>("any"))

    }
}