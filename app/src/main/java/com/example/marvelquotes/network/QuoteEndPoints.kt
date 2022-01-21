package com.example.marvelquotes.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface QuoteEndPoints {

    @GET(".")
    suspend fun getQuote(
        @Header("x-rapidapi-host") host: String = "marvel-Quote-api.p.rapidapi.com",
        @Header("x-rapidapi-key") key: String = "4093b899f7mshd9a518c450bf536p18434ajsn27101561a8ee",
    ): Response<Quote?>
}
