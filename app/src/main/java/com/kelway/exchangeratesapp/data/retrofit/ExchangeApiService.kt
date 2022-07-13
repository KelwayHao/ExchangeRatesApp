package com.kelway.exchangeratesapp.data.retrofit

import com.kelway.exchangeratesapp.data.model.CurrencyResponse
import com.kelway.exchangeratesapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExchangeApiService {
    @GET("{endpoint}")
    fun getExchangeApi(
        @Path("endpoint")
        endpoint: String = "latest",
        @Query("apikey")
        apikey: String = Constants.BASE_API_KEY,
        @Query("base")
        base: String,
    ): Flow<CurrencyResponse>
}