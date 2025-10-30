package com.example.treinojunior.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.treinojunior.data.entity.UserEntity
import com.example.treinojunior.repository.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _user = MutableStateFlow<List<UserEntity>>(emptyList())
    val user: StateFlow<List<UserEntity>> = _user

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getAllUsers()
            _user.value = list
        }
    }

    fun getUsername(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getByIdUsername(username)
            _user.value = result
        }
    }

    fun addUser(id: Int, name: String, email: String){
        viewModelScope.launch(Dispatchers.IO){
            val user = UserEntity(id=id, name=name, email=email)
            repository.insertUser(user)
            loadUsers()
        }
    }

    fun updateUser(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
            loadUsers()
        }
    }

    fun deleteUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user)
            loadUsers()
        }
    }

}