package com.example.stuffcomposable.di

import com.example.stuffcomposable.data.remote.StuffAPI
import com.example.stuffcomposable.repository.StuffRepository
import com.example.stuffcomposable.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideStuffRepository(
	api: StuffAPI
    ) = StuffRepository(api)

    @Singleton
    @Provides
    fun provideStuffApi(): StuffAPI {
	return Retrofit.Builder()
	    .addConverterFactory(GsonConverterFactory.create())
	    .baseUrl(BASE_URL)
	    .client(provideHttpClient())
	    .build()
	    .create(StuffAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
	val builder = OkHttpClient().newBuilder()
	    .addInterceptor(provideInterceptor())
	return builder.build()
    }

    private fun provideInterceptor(): HttpLoggingInterceptor {
	val aLogger = HttpLoggingInterceptor()
	aLogger.level = (HttpLoggingInterceptor.Level.BASIC)
	return aLogger
    }

}