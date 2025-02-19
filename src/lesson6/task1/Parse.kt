@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

// Урок 6: разбор строк, исключения
// Максимальное количество баллов = 13
// Рекомендуемое количество баллов = 11
// Вместе с предыдущими уроками (пять лучших, 2-6) = 40/54

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val days = mutableListOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val months = mapOf(
        "января" to "01",
        "февраля" to "02",
        "марта" to "03",
        "апреля" to "04",
        "мая" to "05",
        "июня" to "06",
        "июля" to "07",
        "августа" to "08",
        "сентября" to "09",
        "октября" to "10",
        "ноября" to "11",
        "декабря" to "12"
    )
    val date = str.split(" ").toMutableList()
    if (str.isEmpty() || date.size != 3 || date[1] !in months) return ""
    if (date[0].toIntOrNull() == null || date[2].toIntOrNull() == null || date[0].toInt() <= 0 || date[2].toInt() <= 0) return ""
    if (date[2].toInt() % 4 == 0 && date[2].toInt() % 100 != 0 || date[2].toInt() % 400 == 0) days[2] = 29
    date[1] = months[date[1]].toString()
    if (date[0].toInt() > days[date[1].toInt()]) return ""
    return String.format("%02d.%02d.%d", date[0].toInt(), date[1].toInt(), date[2].toInt())
}

/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val days = mutableListOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val months = mapOf(
        "01" to "января",
        "02" to "февраля",
        "03" to "марта",
        "04" to "апреля",
        "05" to "мая",
        "06" to "июня",
        "07" to "июля",
        "08" to "августа",
        "09" to "сентября",
        "10" to "октября",
        "11" to "ноября",
        "12" to "декабря"
    )
    val date = digital.split(".").toMutableList()
    for (word in date) for (char in word) if (!char.isDigit()) return ""
    if (digital.isEmpty() || date.size != 3 || date[1] !in months) return ""
    if (date[2].toInt() % 4 == 0 && date[2].toInt() % 100 != 0 || date[2].toInt() % 400 == 0) days[2] = 29
    if (date[0].toInt() > days[date[1].toInt()]) return ""
    date[1] = months[date[1]]!!
    return String.format("%d %s %s", date[0].toInt(), date[1], date[2])
}

/**
 * Средняя (4 балла)
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val num = "+0123456789".toSet() 
    if ("()" in phone || ("+" in phone && phone[0] != '+')) return ""
    var l = 0
    var c = 0
    for (i in phone) {
        if (i == '(') {
            l++
            c++
        }
        if (i == ')') l--
        if (c > 1 || l == 1 && i == ' ') return ""
    }
    val n = phone.replace(" ", "").replace("-", "").replace("(", "").replace(")", "")
    for (s in n.toSet()) if (s !in num) return ""
    return n
}

/**
 * Средняя (5 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var max = 0
    val string = jumps.split(" ")
    for (i in string) if (i !in "-%" && (i.toIntOrNull() == null || i.toInt() < 0)) return -1
    val n = jumps.replace("-", "").replace("%", "")
    if (n.isBlank()) return -1
    val x = n.split(" ").toSet()
    for (i in x) if (i.toIntOrNull() != null && i.toInt() > max) max = i.toInt()
    return max
}

/**
 * Сложная (6 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var max = 0
    val string = jumps.split(" ")
    if (string.size % 2 != 0) return -1
    for (i in string) if (i.replace("+", "").replace("-", "").replace("%", "")
            .isNotBlank() && (i.toIntOrNull() == null || i.toInt() < 0 || (i.toIntOrNull() != null && i[0] == '+'))
    ) return -1
    var i = 0
    while (i < string.size - 1) {
        if (string[i + 1][0] == '+' && string[i].toIntOrNull() != null && max < string[i].toInt()) max =
            string[i].toInt()
        i += 2
    }
    return max
}

/**
 * Сложная (6 баллов)
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val str = expression.split(" ")
    val x = expression.replace("+", "").replace("-", "").replace(" ", "")
    for (i in x) if (i !in "+-" && !i.isDigit()) throw IllegalArgumentException()
    if (x.isBlank() || str[0].toIntOrNull() == null || str[0][0] in "+-") throw IllegalArgumentException()
    var end = str[0].toInt()
    var i = 1
    while (i < str.size - 1) {
        if (str[i + 1].toIntOrNull() == null || str[i + 1][0] in "+-") throw IllegalArgumentException()
        if (str[i] == "+") end += str[i + 1].toInt()
        else if (str[i] == "-") end -= str[i + 1].toInt()
        i += 2
    }
    return end
}

/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val words = str.lowercase().split(" ")
    var c = 0
    for (i in 0..words.size - 2) {
        if (words[i] != words[i + 1]) c += words[i].length + 1
        else return c
    }
    return -1
}

/**
 * Сложная (6 баллов)
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше нуля либо равны нулю.
 */
fun mostExpensive(description: String): String {
    if (description.isEmpty()) return ""
    val product = description.split("; ")
    var max = 0.0
    var name = ""
    for (word in product) {
        val w = word.split(" ")
        if (w.size != 2 || w[1].toDoubleOrNull() == null) return ""
        val cost = w[1].toDouble()
        if (max <= cost) {
            max = cost
            name = w[0]
        }
    }
    return name

}

/**
 * Сложная (6 баллов)
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    var c1 = 0
    var c2 = 0
    for (x in commands) {
        if (x !in "><+-[] ") throw IllegalArgumentException()
        if (x == '[') c1++
        if (x == ']') c2++
        if (c2 > c1) throw IllegalArgumentException()
    }
    if (c1 != c2 || commands.indexOf("]") < commands.indexOf("[")) throw IllegalArgumentException()
    if (cells == 0) {
        if (commands.replace(" ", "") == "" || limit == 0) return listOf()
        else throw IllegalStateException()
    }

    val c = mutableListOf<Int>()
    for (i in 0 until cells) c += 0

    var step = 0
    var cell = (cells / 2)
    var commandNumber = 0
    val index = ArrayDeque<Int>()
    val loop = mutableMapOf<Int, Int>()

    while (step < limit && commandNumber < commands.length && cell in 0 until cells) {
        when (commands[commandNumber]) {
            '>' -> cell++
            '<' -> cell--
            '+' -> c[cell]++
            '-' -> c[cell]--
            '[' -> if (c[cell] == 0) {
                commandNumber = loop.getOrPut(commandNumber) {
                    var x = commandNumber
                    var cc = 1
                    while (cc != 0) {
                        x++
                        when (commands[x]) {
                            '[' -> cc++
                            ']' -> cc--
                        }
                    }
                    x
                }

            } else index += commandNumber
            ']' -> if (c[cell] != 0) {
                commandNumber = index.last()
            } else index -= index.last()
            ' ' -> commandNumber += 0
        }
        step++
        commandNumber++
    }

    if (cell !in 0 until cells) throw IllegalStateException()
    return c
}

