package com.mohan.hackernews.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohan.hackernews.data.Story
import com.mohan.hackernews.repository.HackerNewsRepo
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    var hackerNewsRepo: HackerNewsRepo
) : ViewModel() {

    val service = HackerNewsRepo.getNewsService()

    private val parentJob = SupervisorJob()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val storiesList = ArrayList<Story>()

    val storyLiveData = MutableLiveData<List<Story>>()

    fun getTopStories() = runBlocking {
        coroutineScope.launch {
            hackerNewsRepo.getNewsService()?.getTopStories()
                ?.enqueue(object : Callback<List<Long>> {
                    override fun onFailure(call: Call<List<Long>>?, t: Throwable?) {
                        Log.e("Mohan", t!!.message.toString());
                    }

                    override fun onResponse(
                        call: Call<List<Long>>?,
                        response: Response<List<Long>>?
                    ) {
                        val topStory = response?.body()
                        for (value in topStory!!.iterator()) {
                            Log.e("Mohan", "value : " + value)
                            coroutineScope.launch {
                                callStory(value)
                                val asyncCall = async { callStory(value) }
                                delay(1000)




                                coroutineScope.launch {
                                    storyLiveData.value = storiesList
                                }
                                asyncCall.await()
                            }
                        }
                    }

                })
        }
    }


    fun callStory(value: Long) {
        hackerNewsRepo.getNewsService()!!.getStoryItem((value)).enqueue(
            object : Callback<Story> {
                override fun onFailure(
                    call: Call<Story>?,
                    t: Throwable?
                ) {
                }

                override fun onResponse(
                    call: Call<Story>?,
                    response: Response<Story>?
                ) {
//                    Log.e("Mohan", response!!.body().title)
                    storiesList.addAll(listOf(response!!.body()))
//                    Log.e("Mohan", " Size " + storiesList.size)
                }
            })
    }
}