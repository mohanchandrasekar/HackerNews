package com.mohan.hackernews.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohan.hackernews.R
import com.mohan.hackernews.StoryAdapter
import com.mohan.hackernews.data.Story
import com.mohan.hackernews.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModel()

    val storyAdapter = StoryAdapter { story: Story, i: Int ->
        onItemClick(story, i)
    }

    private fun onItemClick(it: Story, position: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()

        mainViewModel.getTopStories()

        mainViewModel.storyLiveData.observe(this, Observer {
            for (story in it) {
                Log.e("Mohan", story.title)
                storyAdapter.setData(it as ArrayList<Story>)
            }
        })

    }

    private fun setRecyclerView() {
        recycler_top_stories.let { recycler ->
            recycler.layoutManager = LinearLayoutManager(
                baseContext, LinearLayoutManager.VERTICAL, false
            )
            recycler.adapter = storyAdapter
        }
    }
}
