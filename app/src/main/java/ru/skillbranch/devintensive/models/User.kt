package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id : String,
    val firstName : String?,
    val lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    val lastVisit : Date? = null,
    val isOnline : Boolean = false
){
    constructor(id: String, firstName: String?, lastName: String?) : this(id, firstName, lastName, null)

    constructor(id:String) : this(id, "John", "Doe $id")

    init {
        println("It's Alive!!!")
        println("And his name is $firstName $lastName")
    }

    companion object Factory{
        private var lastId : Int = -1
        fun makeUser(fullName:String?) : User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(
                id = "$lastId",
                firstName = firstName,
                lastName = lastName
            )
        }
    }

    data class Builder(
        var id: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var avatar: String? = null,
        var rating:Int? = null,
        var respect: Int? = null,
        var lastVisit: Date? = null,
        var isOnline: Boolean? = null
    ) {
        fun id(s:String) = apply { this.id = s }
        fun firstName(s: String) = apply { this.firstName = s }
        fun lastName(s: String) = apply { this.lastName = s }
        fun avatar(s: String) = apply { this.avatar = s }
        fun rating(n: Int) = apply { this.rating  = n }
        fun respect(n: Int) = apply { this.respect = n }
        fun lastVisit(d:Date) = apply {this.lastVisit = d}
        fun isOnline(b:Boolean) = apply {this.isOnline = b}
        fun build() = User(id!!,firstName, lastName, avatar, rating!!, respect!!,lastVisit,isOnline!!)
    }


}


