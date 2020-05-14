package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

fun User.toUserView(): UserView{

    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName,lastName)
    val status = if(lastVisit ==null) "Еще ниразу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()};"
    return UserView(id,
    fullName = "$firstName $lastName",
    nickName = nickName,
    initials = initials,
    avatar = avatar,
    status = status)
}

fun Date.humanizeDiff(date:Date = Date()): String {
    val secondsBetweenDate = date.time - this.time
    val result = when{
        secondsBetweenDate in 0 .. (1 * SECOND) -> "только что"
        secondsBetweenDate in (1 * SECOND) .. (45 * SECOND) -> "несколько секунд назад"
        secondsBetweenDate in (45 * SECOND) .. (75 * SECOND) -> "минуту назад"
        secondsBetweenDate in (75 * SECOND) .. (45 * MINUTE) -> "${TimeUnits.MINUTE.plural((secondsBetweenDate / MINUTE).toInt())} назад"
        secondsBetweenDate in (45 * MINUTE) .. (75 * MINUTE) -> "час назад"
        secondsBetweenDate in (75 * MINUTE) .. (22 * HOUR) -> "${TimeUnits.HOUR.plural((secondsBetweenDate / HOUR).toInt() )} назад"
        secondsBetweenDate in (22 * HOUR) .. (26 * HOUR) -> "день назад"
        secondsBetweenDate in (26 * HOUR) .. (360 * DAY) -> "${TimeUnits.DAY.plural((secondsBetweenDate / DAY).toInt())} назад"
        secondsBetweenDate > (360 * DAY)  -> "более года назад"
        -secondsBetweenDate in 0 .. (1 * SECOND) -> "только что"
        -secondsBetweenDate in (1 * SECOND) .. (45 * SECOND) -> "через несколько секунд"
        -secondsBetweenDate in (45 * SECOND) .. (75 * SECOND) -> "через минуту"
        -secondsBetweenDate in (75 * SECOND) .. (45 * MINUTE) -> "через ${TimeUnits.MINUTE.plural((-secondsBetweenDate / MINUTE).toInt())}"
        -secondsBetweenDate in (45 * MINUTE) .. (75 * MINUTE) -> "через час"
        -secondsBetweenDate in (75 * MINUTE) .. (22 * HOUR) -> "через ${TimeUnits.HOUR.plural((-secondsBetweenDate / HOUR).toInt())}"
        -secondsBetweenDate in (22 * HOUR) .. (26 * HOUR) -> "через день"
        -secondsBetweenDate in (26 * HOUR) .. (360 * DAY) -> "через ${TimeUnits.DAY.plural((-secondsBetweenDate / DAY).toInt())}"
        -secondsBetweenDate > (360 * DAY)  -> "более чем через год"

        else -> "более года назад"
    }
    return result
}