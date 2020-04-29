package com.example.coroutines

import com.example.coroutines.model.User

object ExampleSingleton {

    val singletonUser: User by lazy {
        User("subeer@gmail.com", "subeer", "image.png")
    }
}