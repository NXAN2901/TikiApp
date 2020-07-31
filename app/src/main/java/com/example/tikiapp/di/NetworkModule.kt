package com.example.tikiapp.di

import com.example.tikiapp.BuildConfig
import com.example.tikiapp.data.remote.api.TikiAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val logLevel =
    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

private const val baseUrl = BuildConfig.BASE_URL

private fun getLogInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
    setLevel(logLevel)
}

private fun retrofitClient(
    httpClient: OkHttpClient
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .client(httpClient)
    .build()

private fun okHttpClient() = OkHttpClient.Builder()
    .addInterceptor(getLogInterceptor()).apply {
        setTimeOutOkHttpClient(
            this
        )
    }
    .build()

private fun headerInterceptor(authHeader: Boolean = false) = Interceptor { chain ->
    chain.proceed(
        chain.request().newBuilder().addHeader("Content-Type", "application/json")
            .build()
    )
}

private fun setTimeOutOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
    okHttpClientBuilder.apply {
        readTimeout(30L, TimeUnit.SECONDS)
        connectTimeout(30L, TimeUnit.SECONDS)
        writeTimeout(30L, TimeUnit.SECONDS)
    }

fun createNetworkClient() = retrofitClient(
    okHttpClient()
)

fun provideTikiApi(retrofit: Retrofit): TikiAPI = retrofit.create(TikiAPI::class.java)

val networkModule = module {
    single { createNetworkClient() }
    factory { provideTikiApi(get()) }
}



