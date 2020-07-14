package com.servicetitan.android.platform.kotlinflowroom

import android.content.Context
import com.servicetitan.android.platform.kotlinflowroom.data.ShowApiRepository
import com.servicetitan.android.platform.kotlinflowroom.data.local.ShowDatabaseProvider
import com.servicetitan.android.platform.kotlinflowroom.data.remote.ShowApiProvider

fun Context.provideShowRepository() =
    ShowApiRepository(ShowApiProvider.provideShowApi(), ShowDatabaseProvider.provideShowDatabase(this))