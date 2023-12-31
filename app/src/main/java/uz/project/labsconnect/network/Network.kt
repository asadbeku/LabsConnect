package uz.project.labsconnect.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private val okHttpClient = OkHttpClient.Builder()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://slab.gov.uz")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}