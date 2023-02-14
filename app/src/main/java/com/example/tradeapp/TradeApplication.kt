package com.example.tradeapp

import android.app.Application
import com.example.tradeapp.data.AppContainer
import com.example.tradeapp.data.DefaultAppContainer

// при создании приложения запускается этот class (entry point), который создаёт контейнер
// в контейнере создаются все необходимые dependencies, то есть repository для работы с Retrofit Api
// далее создаётся ui и вместе с ним создаёётся viewModel, в конструктор которого передаётся созданный ранее
// и лежащий в container, repository; viewModel получает данные о бумагах, общаясь с ранее подготовленным repository

// но есть проблема: данные не обновляются автоматически
class TradeApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}