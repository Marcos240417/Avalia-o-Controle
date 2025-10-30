package com.example.treinojunior.koin

import androidx.room.Room
import com.example.treinojunior.data.dao.UserDao
import com.example.treinojunior.data.database.AppDatabase
import com.example.treinojunior.repository.repository.UserRepository
import com.example.treinojunior.repository.repositoryImp.UserRepositoryImp
import com.example.treinojunior.ui.viewmodel.UserViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ✅ Banco de dados
    single { AppDatabase.getDatabase(get()) }

    // ✅ DAO
    single<UserDao> { get<AppDatabase>().userDao() }

    // ✅ Repository
    single<UserRepository> { UserRepositoryImp(get()) }

    // ✅ ViewModel
    viewModel { UserViewModel(get()) }
}