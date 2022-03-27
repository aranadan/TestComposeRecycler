package com.example.testcomposerecycler.data

sealed class Sex {
    object Male : Sex()
    object Female : Sex()

    val value by lazy {
        when (this) {
            is Male -> "Man"
            is Female -> "Woman"
        }
    }
}

data class Person(val name: String, val age: Int, val sex: Sex)


object UserData {
    val users = listOf(
        Person("Jhon Deer", 25, Sex.Male),
        Person("Ivan Dorn", 35, Sex.Male),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Sarah Connor", 21, Sex.Female),
        Person("Madonna", 23, Sex.Female)
    )
}