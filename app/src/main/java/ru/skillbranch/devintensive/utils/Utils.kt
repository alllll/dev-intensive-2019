package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{
        //TODO FIX ME!!!
        var firstName:String?
        var lastName:String?

        if(fullName.isNullOrBlank()){
            firstName = null;
            lastName = null;
        }else{
            val parts : List<String>? = fullName?.split(" ")
            firstName = parts?.getOrNull(0)
            lastName = parts?.getOrNull(1)
        }

        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider:String = " "): String {
        TODO("not implemented")
    }

    fun toInitials(firstName: String?, lastName:String?): String? {
        TODO("not implemented")
    }
}