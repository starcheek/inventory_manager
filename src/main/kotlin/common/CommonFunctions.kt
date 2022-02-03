package common

import java.lang.NumberFormatException

fun List<String>.printAllElements() {
    println()
    forEach { element ->
        println(element)
    }
    println()
}

fun String.convertToInt() = trim().toInt()

fun getIntegerInput(message: String): Int {
    var input = -1
    while (input < 0) {
        try {
            input = getStringInput(message).toInt()
        } catch (exception: NumberFormatException) {
            println(INPUT_ERROR)
        }
    }
    return input
}

fun getStringInput(message: String): String {
    print(message)
    var input = readLine()!!.trim()
    while (input.isBlank()) {
        input = getStringInput(message)
    }
    return input
}
