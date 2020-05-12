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

}


