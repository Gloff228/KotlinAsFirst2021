@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import java.io.File.*
import kotlin.math.*

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt((v.map { it.pow(2) }).sum())

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list.isNotEmpty()) (list.sum() / list.size)
    else 0.0
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val s = list.sum() / list.size
    for ((index, element) in list.withIndex()) {
        list[index] = element - s
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in a.indices) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var c = 0
    for (i in p.indices) {
        c += (p[i] * x.toDouble().pow(i)).toInt()
    }
    return c
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] = list[i] + list[i - 1]
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var end = ""
    var num = n
    var x = num / 1000
    end += "M".repeat(x)
    num %= 1000
    x = num / 100
    end += when (x) {
        9 -> "CM"
        8, 7, 6, 5 -> "D${"C".repeat(x - 5)}"
        4 -> "CD"
        else -> "C".repeat(x)
    }
    num %= 100
    x = num / 10
    end += when (x) {
        9 -> "XC"
        8, 7, 6, 5 -> "L${"X".repeat(x - 5)}"
        4 -> "XL"
        else -> "X".repeat(x)
    }
    num %= 10
    x = num
    end += when (x) {
        9 -> "IX"
        8, 7, 6, 5 -> "V${"I".repeat(x - 5)}"
        4 -> "IV"
        else -> "I".repeat(x)
    }
    return end

}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun first(n: Int): List<String> {
    val a = n / 100
    val b = n / 10 % 10
    val c = n % 10
    var string: List<String> = listOf()
    when (a) {
        1 -> string += "сто"
        2 -> string += "двести"
        3 -> string += "триста"
        4 -> string += "четыреста"
        5 -> string += "пятьсот"
        6 -> string += "шестьсот"
        7 -> string += "семьсот"
        8 -> string += "восемьсот"
        9 -> string += "девятьсот"
    }
    when (b) {
        1 -> when (c) {
            1 -> return (string + "одиннадцать")
            2 -> return (string + "двенадцать")
            3 -> return (string + "тринадцать")
            4 -> return (string + "четырнадцать")
            5 -> return (string + "пятнадцать")
            6 -> return (string + "шестнадцать")
            7 -> return (string + "семнадцать")
            8 -> return (string + "восемнадцать")
            9 -> return (string + "девятнадцать")
            0 -> return (string + "десять")
        }
        2 -> string += "двадцать"
        3 -> string += "тридцать"
        4 -> string += "сорок"
        5 -> string += "пятьдесят"
        6 -> string += "шестьдесят"
        7 -> string += "семьдесят"
        8 -> string += "восемьдесят"
        9 -> string += "девяносто"
    }
    when (c) {
        1 -> return (string + "одна")
        2 -> return (string + "две")
        3 -> return (string + "три")
        4 -> return (string + "четыре")
        5 -> return (string + "пять")
        6 -> return (string + "шесть")
        7 -> return (string + "семь")
        8 -> return (string + "восемь")
        9 -> return (string + "девать")
        0 -> return (string)
    }
    return string
}

fun second(n: Int): List<String> {
    val a = n / 100
    val b = n / 10 % 10
    val c = n % 10
    var string: List<String> = listOf()
    when (a) {
        1 -> string += "сто"
        2 -> string += "двести"
        3 -> string += "триста"
        4 -> string += "четыреста"
        5 -> string += "пятьсот"
        6 -> string += "шестьсот"
        7 -> string += "семьсот"
        8 -> string += "восемьсот"
        9 -> string += "девятьсот"
    }
    when (b) {
        1 -> when (c) {
            1 -> return (string + "одиннадцать")
            2 -> return (string + "двенадцать")
            3 -> return (string + "тринадцать")
            4 -> return (string + "четырнадцать")
            5 -> return (string + "пятнадцать")
            6 -> return (string + "шестнадцать")
            7 -> return (string + "семнадцать")
            8 -> return (string + "восемнадцать")
            9 -> return (string + "девятнадцать")
            0 -> return (string + "десять")
        }
        2 -> string += "двадцать"
        3 -> string += "тридцать"
        4 -> string += "сорок"
        5 -> string += "пятьдесят"
        6 -> string += "шестьдесят"
        7 -> string += "семьдесят"
        8 -> string += "восемьдесят"
        9 -> string += "девяносто"
    }
    when (c) {
        1 -> return (string + "один")
        2 -> return (string + "два")
        3 -> return (string + "три")
        4 -> return (string + "четыре")
        5 -> return (string + "пять")
        6 -> return (string + "шесть")
        7 -> return (string + "семь")
        8 -> return (string + "восемь")
        9 -> return (string + "девать")
        0 -> return (string)
    }
    return string
}

fun russian(n: Int): String {
    val a = n / 1000
    val b = n % 1000
    var string = when {
        a == 0 -> second(b)
        a % 10 == 0 || a % 10 >= 5 || (a % 100 in 10..20) -> first(a) + "тысяч" + second(b)
        a % 10 == 1 -> first(a) + "тысяча" + second(b)
        a % 10 in 2..5 -> first(a) + "тысячи" + second(b)
        else -> second(b)
    }
    return string.joinToString(separator = " ")
}


fun main() {
    println(russian(120123))
}