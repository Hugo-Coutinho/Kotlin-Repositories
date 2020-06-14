package com.example.kotlinrepositories

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    private val dataSourceFake: DataSourceFake = DataSourceFake()

    @Test
    fun addition_isCorrect() {
        val c = Mockito.mock(dataSourceFake::class.java)

        `when`(c.urlPath()).thenReturn("Passed")

        val result = c.urlPath()

        assertEquals("Passed", result)
    }
}

class DataSourceFake {
    fun urlPath(): String {
        return "fail"
    }
}