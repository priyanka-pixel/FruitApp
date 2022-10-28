package com.example.fruitapp.synchronization

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.fruitapp.repository.FruitRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

@HiltWorker
class SyncLocalDataService @AssistedInject constructor(@Assisted context: Context,
                                                       @Assisted params: WorkerParameters,
                                                       fruitRepository: FruitRepository
): CoroutineWorker(context,params){
    companion object{
        const val WORK_NAME = "com.example.fruitapp.synchronization.SynLocalDataService"

    }
    @Inject
    lateinit var fruitRepository: FruitRepository

    override suspend fun doWork(): Result
    {
        try{
            fruitRepository.getFruits()
        } catch (e: Exception){
            Log.e("dataserviceSyncError","" + e.message.toString())
            return Result.retry()
        }
return Result.success()
    }
                                                       }