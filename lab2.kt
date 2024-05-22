// Запечатаний(ізольований) клас, що забезпечує основу для різних геометричних фігур
sealed class Figure {

    // Абстрактний метод для обчислення периметра фігури
    abstract fun perimeter(): Double

    // Абстрактний метод для обчислення площі фігури
    abstract fun area(): Double

    // Перевизначений метод toString(), що забезпечує строкове подання фігури
    override fun toString(): String {
        return "Фігура: ${this::class.simpleName}" // Повертає назву класу без пакета
    }
}

// Клас, що представляє коло
data class Circle(private val radius: Double) : Figure() {

    // Обчислення периметра кола за формулою 2 * PI * радіус
    override fun perimeter(): Double {
        return 2 * Math.PI * radius
    }

    // Обчислення площі кола за формулою PI * радіус^2
    override fun area(): Double {
        return Math.PI * radius * radius
    }

    // Строкове подання кола з радіусом
    override fun toString(): String {
        return super.toString() + " (Радіус: $radius)"
    }
}

// Клас, що представляє прямокутник
data class Rectangle(private val width: Double, private val height: Double) : Figure() {

    // Обчислення периметра прямокутника за формулою 2 * (ширина + висота)
    override fun perimeter(): Double {
        return 2 * (width + height)
    }

    // Обчислення площі прямокутника за формулою ширина * висота
    override fun area(): Double {
        return width * height
    }

    // Строкове подання прямокутника із шириною та висотою
    override fun toString(): String {
        return super.toString() + " (Ширина: $width, Висота: $height)"
    }
}

// Клас, що представляє трикутник
data class Triangle(private val a: Double, private val b: Double, private val c: Double) : Figure() {

    // Обчислення периметра трикутника за формулою a + b + c
    override fun perimeter(): Double {
        return a + b + c
    }

    // Обчислення площі трикутника за формулою Герона
    override fun area(): Double {
        val s = perimeter() / 2 // Напівпериметр
        return Math.sqrt(s * (s - a) * (s - b) * (s - c))
    }

    // Строкове подання трикутника зі сторонами a, b та c
    override fun toString(): String {
        return super.toString() + " (Сторони: $a, $b, $c)"
    }
}

// Функція main(), що демонструє роботу з фігурами
fun main() {
    // Список фігур різних типів
    val figures = listOf(
        Circle(3.0),
        Rectangle(12.0, 4.5),
        Triangle(2.4, 3.2, 5.0)
    )

    // Ітерація по списку фігур та виведення інформації про них
    for (figure in figures) {
        // Виведення назви фігури та її параметрів
        println(figure)
        println("Периметр: ${figure.perimeter()}")
        println("Площа: ${figure.area()}")
        println() // Додатковий порожній рядок для візуального відокремлення
    }
}