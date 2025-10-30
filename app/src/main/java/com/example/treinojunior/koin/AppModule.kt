package com.example.treinojunior.koin

import androidx.room.Room
import com.example.treinojunior.data.database.AppDatabase
import com.example.treinojunior.repository.repository.UserRepository
import com.example.treinojunior.repository.repositoryImp.UserRepositoryImp
import com.example.treinojunior.ui.viewmodel.UserViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "myDB"
        ).build()
    }
    single {
        get<AppDatabase>().userDao()
    }
    single<UserRepository>{ UserRepositoryImp(get()) }

    viewModel{ UserViewModel(get()) }
}