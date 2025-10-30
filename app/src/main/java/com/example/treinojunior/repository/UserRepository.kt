package com.example.treinojunior.repository

import com.example.treinojunior.repository.entity.UserEntity

interface UserRepository {
    suspend fun insertUser(user: UserEntity)
    suspend fun getAllUsers(): List<UserEntity>
    suspend fun getByIdUsername(username: String): List<UserEntity>
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
}