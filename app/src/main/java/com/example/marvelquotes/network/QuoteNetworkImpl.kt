package com.example.marvelquotes.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteNetworkImpl @Inject constructor(
    private val dispatcher: Dispatchers,
    private val retroObject: QuoteEndPoints
) : QuoteRepo{

    override suspend fun getQuote()
    : ServiceResult<Quote?> {
        return withContext(dispatcher.IO){
            val dataResponse = retroObject.getQuote()
            if(dataResponse.isSuccessful){
                ServiceResult.Succes(dataResponse.body())
            }else{
                ServiceResult.Error(Exception(dataResponse.errorBody().toString()))
            }
        }
    }
}
