@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import kotlin.math.abs

/**
 * Фабричный метод для создания комплексного числа из строки вида x+yi
 */
fun Complex(s: String): Complex {
    try {
        //проверка на наличие мнимой части
        if (!s.endsWith("i")) throw IllegalArgumentException()
        //поиск индекса первого знака '-' или '+'
        var signIndex = s.indexOfFirst { it == '-' || it == '+' }
        //поиск индекса при отрицательной вещественной части
        if (signIndex == 0) {
            signIndex = 1 + s.substring(1, s.length).indexOfFirst { it == '-' || it == '+' }
        }
        //ошибка при отсутствии '-' или '+'
        if (signIndex == -1) throw IllegalArgumentException()
        return Complex(
            s.substring(0, signIndex).toDouble(),
            s.substring(signIndex, s.length - 1).toDouble()
        )
    } catch (e: Exception) {
        throw IllegalArgumentException("Incorrect input")
    }

}


/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(this.re + other.re, this.im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-1 * this.re, -1 * this.im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(this.re - other.re, this.im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex =
        Complex(this.re * other.re - this.im * other.im, this.re * other.im + this.im * other.re)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (this.re * other.re + this.im * other.im) / (other.re * other.re + other.im * other.im),
        (this.im * other.re - this.re * other.im) / (other.re * other.re + other.im * other.im)
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && (this.re == other.re && this.im == other.im)

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        return if (im >= 0) "${this.re}+${this.im}i"
        else "${this.re}-${abs(this.im)}i"
    }
}
