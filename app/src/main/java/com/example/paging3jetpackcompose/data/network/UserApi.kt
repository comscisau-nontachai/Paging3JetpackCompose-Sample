package com.example.paging3jetpackcompose.data.network

import com.example.paging3jetpackcompose.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("user")
    suspend fun getUsers(@Query("page") page : Int , @Query("limit") limit : Int) : UserResponse

    companion object{
        private val BASE_URL = "https://dummyapi.io/data/v1/"

        operator fun invoke() : UserApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRetrofitClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(UserApi::class.java)
        }

        private fun getRetrofitClient() : OkHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept","application/json")
                    it.addHeader("app-id","62cceaafb592b449c3aad899")
                }.build())
            }.also { client ->
                if(BuildConfig.DEBUG){
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()
    }
}