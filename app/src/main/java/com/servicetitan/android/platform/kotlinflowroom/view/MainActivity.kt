package com.servicetitan.android.platform.kotlinflowroom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.servicetitan.android.platform.kotlinflowroom.R
import com.servicetitan.android.platform.kotlinflowroom.adapter.ShowAdapter
import com.servicetitan.android.platform.kotlinflowroom.data.model.Response
import com.servicetitan.android.platform.kotlinflowroom.data.model.Show
import com.servicetitan.android.platform.kotlinflowroom.provideShowRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var showAdapter: ShowAdapter
    private lateinit var mainNotifier: MainNotifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainNotifier = MainNotifier()
        actionNotifier()
        mainNotifier.listenToAction()
        mainNotifier.notifyRefresh()
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
//        Scope1 {
//            val popularShow: Response<Show> = provideShowRepository().popularShowSuspend()
//            val string = provideShowRepository().test()
//            Toast.makeText(this@MainActivity, string, Toast.LENGTH_LONG).show()
//            showAdapter.updateData(popularShow.results)
//        }

        Scope2 {
            provideShowRepository().popularShow()
                .combine(provideShowRepository().genre()) { shows, genre ->
                    shows.forEach { it.updateGenre(genre) }.let { shows }
                }
                .progress()
                .collect { showAdapter.updateData(it) }
        }
    }

    private fun actionNotifier() {
        Scope2 {
            mainNotifier.notifierFlow.collect {
                Toast.makeText(this@MainActivity, it.name, Toast.LENGTH_SHORT).show()
                if(it == MainActivityAction.REFRESH) mainNotifier.notifyProgress()
            }
        }
    }

    private fun LifecycleOwner.Scope1(block: suspend CoroutineScope.() -> Unit) =
        lifecycleScope.launch(block = {
            showProgress(true)
            block.invoke(this)
            showProgress(false)
        })

    private fun LifecycleOwner.Scope2(block: suspend CoroutineScope.() -> Unit) =
        lifecycleScope.launch(block = block)

    private fun <T> Flow<T>.progress() =
        onStart { showProgress(true) }.onCompletion { showProgress(false) }

    private fun showProgress(inProgress: Boolean) {
        progressBar.visibility = if (inProgress) View.VISIBLE else View.GONE
        recyclerView.visibility = if (inProgress) View.GONE else View.VISIBLE
    }

    private fun setupRecyclerView() {
        showAdapter =
            ShowAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = showAdapter
    }
}