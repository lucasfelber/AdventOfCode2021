package util

import java.net.URL

fun getResource(path: String): URL? {
    return object {}.javaClass.classLoader.getResource(path)
}

fun getResourceAsText(path: String): String {
    return getResource(path)!!.readText()
}