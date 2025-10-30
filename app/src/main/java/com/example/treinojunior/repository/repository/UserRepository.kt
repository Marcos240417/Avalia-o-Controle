package com.example.treinojunior.repository.repository

import com.example.treinojunior.data.entity.UserEntity

interface UserRepository {
    suspend fun insertUser(user: UserEntity)
    suspend fun getAllUsers(): List<UserEntity>
    suspend fun getByIdUsername(username: String): List<UserEntity>
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
}