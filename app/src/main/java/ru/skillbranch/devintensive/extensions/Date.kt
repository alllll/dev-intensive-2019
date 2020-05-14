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