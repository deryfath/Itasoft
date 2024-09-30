package com.itasoft.project

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.itasoft.project.data.DataRepository
import com.itasoft.project.dependency.provideApiDataSource
import com.itasoft.project.dependency.provideGson
import com.itasoft.project.dependency.provideLoggingInterceptor
import com.itasoft.project.dependency.provideOkHttpClient
import com.itasoft.project.dependency.provideRequestInterceptor
import com.itasoft.project.module.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        val appModule: Module = module {
            single { DataRepository(get()) }
            single<SharedPreferences> {
                androidContext().getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)
            }
            viewModel { MainViewModel(get()) }
            single { provideRequestInterceptor() }
            single { provideGson() }
            single { provideLoggingInterceptor() }
            single { provideOkHttpClient(get(), get()) }
            single { provideApiDataSource(get(), get()) }

        }

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }
}