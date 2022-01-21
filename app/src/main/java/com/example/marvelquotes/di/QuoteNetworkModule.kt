package com.example.marvelquotes.di

import com.example.marvelquotes.network.QuoteEndPoints
import com.example.marvelquotes.network.QuoteNetworkImpl
import com.example.marvelquotes.network.QuoteRepo
import com.example.marvelquotes.network.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object QuoteNetworkModule{

private const val BASE_URL = "https://marvel-quote-api.p.rapidapi.com/"

    @Singleton
    @Provides
    fun provideRedditRetrofit(): QuoteEndPoints{
        return RetrofitFactory.retrofitProvider(
            QuoteEndPoints::class.java,
            BASE_URL
        )
    }

    @Singleton
    @Provides
    fun provideQuoteRepo(dispatcher: Dispatchers, retroObject: QuoteEndPoints): QuoteRepo{
        return QuoteNetworkImpl(dispatcher, retroObject)
    }
}
