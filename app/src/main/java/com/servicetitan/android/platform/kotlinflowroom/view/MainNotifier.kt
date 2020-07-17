package com.servicetitan.android.platform.kotlinflowroom.view

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

enum class MainActivityAction {
    REFRESH,
    PROGRESS
}

@ExperimentalCoroutinesApi
class MainNotifier {

    private val notifier = BroadcastChannel<MainActivityAction>(1)
    val notifierFlow = notifier.asFlow()

//    private val notifier = Channel<MainActivityAction>()
//    val notifierFlow = notifier.consumeAsFlow()

    fun notifyRefresh() = GlobalScope.launch { notifier.send(MainActivityAction.REFRESH) }

    suspend fun notifyProgress() = GlobalScope.launch { notifier.send(MainActivityAction.PROGRESS) }

    fun listenToAction() {
        GlobalScope.launch {
            notifierFlow.collect {
                Log.d("MainNotifier", it.name)
            }
        }
    }
}