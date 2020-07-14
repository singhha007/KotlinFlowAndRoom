package com.servicetitan.android.platform.kotlinflowroom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.servicetitan.android.platform.kotlinflowroom.R
import com.servicetitan.android.platform.kotlinflowroom.adapter.ShowAdapter
import com.servicetitan.android.platform.kotlinflowroom.provideShowRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var showAdapter: ShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            provideShowRepository().popularShow()
                    .combine(provideShowRepository().genre()) { shows, genre ->
                        shows.forEach { it.updateGenre(genre) }.let { shows }
                    }.collect {
                        showAdapter.updateData(it)
                    }
        }
    }

    private fun setupRecyclerView() {
        showAdapter =
            ShowAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = showAdapter
    }
}