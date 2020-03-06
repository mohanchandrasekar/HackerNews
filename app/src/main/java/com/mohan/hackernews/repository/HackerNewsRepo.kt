package com.mohan.hackernews.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HackerNewsRepo {
    private var hackerNewsService: HackerNewsService? = null

    private val HTTPS_API = "https://hacker-news.firebaseio.com"

    /**
     * Singleton Instance for api call creation
     *
     * @return hackerNewsService instance
     */
    fun getNewsService(): HackerNewsService? {
        if (hackerNewsService == null) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(HTTPS_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            hackerNewsService = retrofit.create<HackerNewsService>(HackerNewsService::class.java)
        }
        return hackerNewsService
    }
}