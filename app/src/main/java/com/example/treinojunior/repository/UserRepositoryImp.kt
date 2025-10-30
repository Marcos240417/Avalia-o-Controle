package com.example.treinojunior.repository

import com.example.treinojunior.repository.dao.UserDao
import com.example.treinojunior.repository.entity.UserEntity

class UserRepositoryImp(private val dao: UserDao): UserRepository {
    override suspend fun insertUser(user: UserEntity) {
        dao.insertUser(user)
    }

    override suspend fun getAllUsers(): List<UserEntity> {
        return dao.getAllUsers()
    }

    override suspend fun getByIdUsername(username: String): List<UserEntity> {
        return dao.getByIdUsername(username)
    }

    override suspend fun updateUser(user: UserEntity) {
        dao.updateUser(user)
    }

    override suspend fun deleteUser(user: UserEntity) {
        dao.deleteUser(user)
    }
}