package com.mohan.hackernews

import com.mohan.hackernews.repository.HackerNewsRepo
import com.mohan.hackernews.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { HackerNewsRepo }
    viewModel { MainViewModel(get()) }

}