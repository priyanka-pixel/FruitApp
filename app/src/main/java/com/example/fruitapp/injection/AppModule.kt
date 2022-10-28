package com.example.fruitapp.injection

import android.content.Context
import com.example.fruitapp.local.AppDatabase
import com.example.fruitapp.local.FruitsDAO
import com.example.fruitapp.remote.FruitRemoteDataSource
import com.example.fruitapp.remote.FruitService
import com.example.fruitapp.repository.FruitRepository
import com.example.fruitapp.search.MainRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.fruityvice.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideFruitService(retrofit: Retrofit): FruitService =
        retrofit.create(FruitService::class.java)

    /**
     *
     */
    @Singleton
    @Provides
    fun provideFruitRemoteDataSource(fruitService: FruitService) =
        FruitRemoteDataSource(fruitService)

    /**
     * 4 Local datasource
     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideRocketDAO(appDatabase: AppDatabase) = appDatabase.fruitsDAO()

    /**
     * 5 Repository
     */
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: FruitRemoteDataSource, localDataSource: FruitsDAO) =
        FruitRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideSearchRepository(): MainRepository =
        MainRepository(provideFruitService(provideRetrofit()))
}
