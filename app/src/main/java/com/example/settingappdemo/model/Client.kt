package com.example.settingappdemo.model

data class Client(
    val id: String,
    val name: String,
){
    fun getFirstNameCharacter(): String{
        val listOfName = name.split(" ")
        val lastName = listOfName[listOfName.size - 1][0]
        return lastName.toString()
    }
}