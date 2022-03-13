@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

/**
 * Фабричный метод для создания комплексного числа из строки вида x+yi
 */
fun Complex(s: String): Complex {

    if (!s.matches(Regex("[-]?\\d+\\.?\\d*[+-]\\d+\\.?\\d*i"))) throw IllegalArgumentException("Incorrect number")

    val signIndex = s.indexOfLast { it == '-' || it == '+' }

    val re = s.substring(0, signIndex).toDouble()
    val im = s.substring(signIndex, s.length - 1).toDouble()

    return Complex(re, im)
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
    operator fun div(other: Complex): Complex {
        if (other == Complex(0.0, 0.0)) throw IllegalArgumentException("Not divisible by 0")
        return Complex(
            (this.re * other.re + this.im * other.im) / (other.re * other.re + other.im * other.im),
            (this.im * other.re - this.re * other.im) / (other.re * other.re + other.im * other.im)
        )
    }

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && (this.re == other.re && this.im == other.im)

    /**
     * Преобразование в строку
     */
    override fun toString(): String = if (im >= 0) "${re}+${im}i" else "${re}${im}i"

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}
