@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

/**
 * Класс "Телефонная книга".
 *
 * Общая сложность задания -- средняя, общая ценность в баллах -- 14.
 * Объект класса хранит список людей и номеров их телефонов,
 * при чём у каждого человека может быть более одного номера телефона.
 * Человек задаётся строкой вида "Фамилия Имя".
 * Телефон задаётся строкой из цифр, +, *, #, -.
 * Поддерживаемые методы: добавление / удаление человека,
 * добавление / удаление телефона для заданного человека,
 * поиск номера(ов) телефона по заданному имени человека,
 * поиск человека по заданному номеру телефона.
 *
 * Класс должен иметь конструктор по умолчанию (без параметров).
 */
class PhoneBook {

    private val phoneBook = HashMap<String, MutableSet<String>>()


    private fun checkNumber(number: String) {
        if (!number.matches(Regex("[0-9+*\\-#]+"))) throw IllegalArgumentException("Incorrect number")
    }

    private fun checkName(name: String) {
        if (!name.matches(Regex("[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+"))) throw IllegalArgumentException("Incorrect name")
    }

    /**
     * Добавить человека.
     * Возвращает true, если человек был успешно добавлен,
     * и false, если человек с таким именем уже был в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun addHuman(name: String): Boolean {
        //проверка на корректность и допустимые символы
        checkName(name)
        //проверка на наличие этого человека в телефонной книге
        return if (phoneBook.containsKey(name)) false
        else {
            phoneBook[name] = mutableSetOf()
            true
        }
    }

    /**
     * Убрать человека.
     * Возвращает true, если человек был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun removeHuman(name: String): Boolean {
        //проверка на допустимые символы
        checkName(name)
        return if (!phoneBook.containsKey(name)) false
        else {
            phoneBook.remove(name)
            true
        }
    }

    /**
     * Добавить номер телефона.
     * Возвращает true, если номер был успешно добавлен,
     * и false, если человек с таким именем отсутствовал в телефонной книге,
     * либо у него уже был такой номер телефона,
     * либо такой номер телефона зарегистрирован за другим человеком.
     */
    fun addPhone(name: String, phone: String): Boolean {
        //проверка на допустимые символы
        checkNumber(phone)
        checkName(name)
        //проверка на наличие имени в телефонной книге
        if (!phoneBook.containsKey(name)) return false
        val phones = phoneBook[name]
        //проверка на наличие телефона в телефонной книге
        if (phones!!.contains(phone)) return false
        //проверка на наличие этого номера у других людей
        for (personPhones in phoneBook.values) {
            if (personPhones.contains(phone)) return false
        }
        phones.add(phone)
        return true
    }

    /**
     * Убрать номер телефона.
     * Возвращает true, если номер был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * либо у него не было такого номера телефона.
     */
    fun removePhone(name: String, phone: String): Boolean {
        //проверка на допустимые символы
        checkNumber(phone)
        checkName(name)
        if (!phoneBook.containsKey(name)) return false
        val phones = phoneBook[name]
        if (!phones!!.contains(phone)) return false
        phones.remove(phone)
        return true
    }

    /**
     * Вернуть все номера телефона заданного человека.
     * Если этого человека нет в книге, вернуть пустой список
     */
    fun phones(name: String): Set<String> {
        //проверка на допустимые символы
        checkName(name)
        //возвращает пустое множество при null
        return phoneBook[name] ?: setOf()
    }

    /**
     * Вернуть имя человека по заданному номеру телефона.
     * Если такого номера нет в книге, вернуть null.
     */
    fun humanByPhone(phone: String): String? {
        //проверка на допустимые символы
        checkNumber(phone)
        for ((name, phones) in phoneBook) {
            if (phones.contains(phone)) return name
        }
        return null
    }

    /**
     * Две телефонные книги равны, если в них хранится одинаковый набор людей,
     * и каждому человеку соответствует одинаковый набор телефонов.
     * Порядок людей / порядок телефонов в книге не должен иметь значения.
     */
    override fun equals(other: Any?): Boolean {
        if (other is PhoneBook) {
            //сопоставление размеров телефонных книг
            if (this.phoneBook.size != other.phoneBook.size) return false
            //перебор имён и номеров, сопоставление с исходной телефонной книгой
            for ((name, phones) in phoneBook) {
                if (other.phoneBook[name] != phones) return false
            }
        }
        return true
    }

    override fun hashCode(): Int = phoneBook.hashCode()
}