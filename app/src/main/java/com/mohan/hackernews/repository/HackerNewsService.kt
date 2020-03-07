package com.mohan.hackernews.repository

import com.mohan.hackernews.data.Comments
import com.mohan.hackernews.data.Story
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {
    /**
     * Return a list of the latest post IDs.
     */
    @GET("/v0/topstories.json")
    fun getTopStories(): Call<List<Long>>

    /**
     * Return story item.
     */
    @GET("/v0/item/{itemId}.json")
    fun getStoryItem(@Path("itemId") itemId: Long?): Call<Story>

    /**
     * Returns a comment item.
     */
    @GET("/v0/item/{itemId}.json")
    fun getCommentItem(@Path("itemId") itemId: Long?): Call<Comments>
}