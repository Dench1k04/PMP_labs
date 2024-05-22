class Car(val model: String, val year: Int?, val passengers: Int?) 
{

    /**
     * Перевіряє, чи придатний автомобіль для використання.
     *
     * @return `true`, якщо модель, рік випуску та кількість пасажирів не null, `false` в іншому випадку.
     */
    fun isUsable(): Boolean
     {
        return model != null && year != null && passengers != null
    }

    /**
     * Повертає рядок, що описує автомобіль.
     *
     * @return Рядок з інформацією про модель, рік випуску, кількість пасажирів та придатність.
     */
    override fun toString(): String 
    {
        val usability = if (isUsable()) " (придатний)" else " (непридатний)"
        return "Модель: $model, Рік випуску: $year, Кількість пасажирів: $passengers$usability"
    }
}

fun main() 
{
    val car1 = Car("Toyota Camry", 2023, 5) // Створення нового автомобіля Toyota Camry
    val car2 = Car("BMW X5", null, 7) // Створення нового автомобіля BMW X5 з null роком випуску
    val car3 = Car("Audi А4", 2017, null) // Створення нового автомобіля Audi А4 з null кількістю пасажирів

    println(car1) // Виведення інформації про Toyota Camry
    println(car2) // Виведення інформації про BMW X5
    println(car3) // Виведення інформації про Audi А4
}