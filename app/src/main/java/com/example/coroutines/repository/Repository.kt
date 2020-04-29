package com.example.coroutines.repository

import androidx.lifecycle.LiveData
import com.example.coroutines.api.MyRetrofitBuilder
import com.example.coroutines.model.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var jobs: CompletableJob? = null

    fun getUser(userId: String): LiveData<User> {

        jobs = Job()

        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                jobs?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getUser(userId)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }

                }
            }
        }
    }

    fun cancelJobs() {
        jobs?.cancel()
    }
}