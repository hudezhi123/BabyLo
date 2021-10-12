package com.hdz.base.http

class Api {
    companion object {
        const val BASE_URL = "http://192.168.2.84:8080/BabyLo/"

        const val LOGIN = BASE_URL + "login"

        const val REGISTER = BASE_URL + "register"

        const val ANIMAL_LIST = BASE_URL + "animalList"

        const val PLANT_LIST = BASE_URL + "plantList"
    }
}