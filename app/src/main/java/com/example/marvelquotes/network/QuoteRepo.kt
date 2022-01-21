package com.example.marvelquotes.network

import kotlinx.coroutines.Dispatchers

interface QuoteRepo {
    suspend fun getQuote(): ServiceResult<Quote?>

    companion object{
        fun provideQuoteRepo(dispatcher: Dispatchers, retroObject: QuoteEndPoints): QuoteRepo{
            return QuoteNetworkImpl(dispatcher, retroObject)
        }
    }
}
