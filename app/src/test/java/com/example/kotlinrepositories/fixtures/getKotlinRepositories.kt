package com.example.kotlinrepositories.fixtures

import java.io.File
import java.io.IOException

fun getJsonDataFromAsset(file: File, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = file.bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}