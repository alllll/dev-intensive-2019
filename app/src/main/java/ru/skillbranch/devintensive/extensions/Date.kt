package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits=TimeUnits.SECOND) : Date {
    var time = this.time
    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date:Date = Date()): String {
    val secondsBetweenDate = date.time-this.time
    val result = when{
        secondsBetweenDate in 0 .. 1 -> "только что"
        secondsBetweenDate in 1 .. 45 -> "несколько секунд назад"
        secondsBetweenDate in 45 .. 75 -> "минуту назад"
        secondsBetweenDate in 75 .. (45 * MINUTE) -> "${TimeUnits.MINUTE.plural((secondsBetweenDate / MINUTE).toInt())} назад"
        secondsBetweenDate in (45 * MINUTE) .. (75 * MINUTE) -> "час назад"
        secondsBetweenDate in (75 * MINUTE) .. (22 * HOUR) -> "${TimeUnits.HOUR.plural((secondsBetweenDate / HOUR).toInt() )} назад"
        secondsBetweenDate in (22 * HOUR) .. (26 * HOUR) -> "день назад"
        secondsBetweenDate in (26 * HOUR) .. (360 * DAY) -> "${TimeUnits.DAY.plural((secondsBetweenDate / DAY).toInt())} назад"
        secondsBetweenDate > (360 * DAY)  -> "более года назад"
        -secondsBetweenDate in 0 .. 1 -> "только что"
        -secondsBetweenDate in 1 .. 45 -> "через несколько секунд"
        -secondsBetweenDate in 45 .. 75 -> "через минуту"
        -secondsBetweenDate in 75 .. (45 * MINUTE) -> "через ${TimeUnits.MINUTE.plural((-secondsBetweenDate / MINUTE).toInt())}"
        -secondsBetweenDate in (45 * MINUTE) .. (75 * MINUTE) -> "через час"
        -secondsBetweenDate in (75 * MINUTE) .. (22 * HOUR) -> "через ${TimeUnits.HOUR.plural((-secondsBetweenDate / HOUR).toInt())}"
        -secondsBetweenDate in (22 * HOUR) .. (26 * HOUR) -> "через день"
        -secondsBetweenDate in (26 * HOUR) .. (360 * DAY) -> "через ${TimeUnits.DAY.plural((-secondsBetweenDate / DAY).toInt())}"
        -secondsBetweenDate > (360 * DAY)  -> "более чем через год"

        else -> "более года назад"
    }
    return result
}



enum class TimeUnits {
    SECOND{
        override fun plural(i: Int):String {
            return "$i ${decline(i,"секунду","секунды", "секунд")}"
        }
    },
    MINUTE{
        override fun plural(i: Int):String {
            return "$i ${decline(i,"минуту","минуты", "минут")}"
        }
    },
    HOUR{
        override fun plural(i: Int):String {
            return "$i ${decline(i,"час","часа", "часов")}"
        }
    },
    DAY{
        override fun plural(i: Int):String {
            return "$i ${decline(i,"день","дня", "дней")}"
        }
    };

    abstract fun plural(i:Int):String
}

fun decline(i:Int, nominative:String, singular:String, plural:String):String{
    if(i > 10 && ((i % 100)/10) == 1) return plural
    when(i%10){
        1 -> return nominative
        in 2..4 -> return singular
        else -> return plural
    }
}