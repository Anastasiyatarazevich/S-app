package com.example.s_app.repository


class UserRepository {
    private val dataModel = User(1, "John Doe", "john.doe@example.com", 30)

    fun getUserById(id: Int): User? {
        return if (id == dataModel.id) dataModel else null
    }
}