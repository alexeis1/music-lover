import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Description
 * Домашнее задание к занятию «1.2. Отличия от Java: immutability, переменные, типы данных, операторы и приведение типов»
 * Задача №3 - "Меломан"
 */

fun main()
{
//стоимость предыдущих покупок
    //var totalPurchased =    50_000L //   500р
    //var totalPurchased =   150_000L // 1 500р
    var totalPurchased = 1_050_000L   //10 500р

//дата последней покупки
    //var lastPurchased = LocalDate.parse("2021-01-16", DateTimeFormatter.ISO_DATE)
    var lastPurchased = LocalDate.parse("2020-10-16", DateTimeFormatter.ISO_DATE)

//стоимость текущей покупки
    var totalPrice = 100_00L //100р

//стоимость покупки после применения двух скидок
    val priceWithDiscount = calcLoyaltyDiscount(
                     calcTotalPurchasedDiscount(totalPrice, totalPurchased), lastPurchased)

    println(             "Покупка ${totalPrice        / 100L} рублей ${totalPrice        % 100L} копеек")
    println("Цена с учетом скидки ${priceWithDiscount / 100L} рублей ${priceWithDiscount % 100L} копеек")
}

/**
 * Description
 * Рассчитывает новую цену с учетом скидки за общую стоимость покупок
 * totalPrice     - стоимость текущей покупки
 * totalPurchased - стоимость всех предыдущих покупок
 */
fun calcTotalPurchasedDiscount(totalPrice : Long, totalPurchased : Long) : Long
{
    val discount0 = 0      //0% скидка
    val discount1 = 10_000 //100р скидка
    val discount2 = 5      //5% скидка

    return when(totalPurchased)
    {
        in         0..  100_099     -> (totalPrice * (1.0F - discount0 / 100.0F)).toLong()
        in   100_100..1_000_099     -> totalPrice - discount1
        in 1_000_100..Long.MAX_VALUE-> (totalPrice * (1.0F - discount2 / 100.0F)).toLong()
        else                        -> totalPrice
    }
}

/**
 * Description
 * Рассчитывает новую цену с учетом скидки за частоту покупок
 * totalPrice        - стоимость текущей покупки
 * lastPurchasedDate - дата предыдущей покупки
 */
fun calcLoyaltyDiscount(totalPrice : Long, lastPurchasedDate : LocalDate) : Long
{
    val discount = 1 //1% скидка за покупки чаще 1го месяца
    return if (LocalDate.now().minusMonths(1) > lastPurchasedDate)
               (totalPrice * (1.0F - discount / 100.0F)).toLong()
           else
                totalPrice
}