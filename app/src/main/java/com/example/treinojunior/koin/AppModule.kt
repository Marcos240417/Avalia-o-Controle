package com.example.treinojunior.koin

import com.example.treinojunior.repository.dao.UserDao
import com.example.treinojunior.repository.database.AppDatabase
import com.example.treinojunior.repository.UserRepository
import com.example.treinojunior.repository.UserRepositoryImp
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