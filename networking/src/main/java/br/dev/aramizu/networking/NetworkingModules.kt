package br.dev.aramizu.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.HttpUrl
import okhttp3.Request

val networkingModule = module {
    factory {
        retrofit()
    }
}

private fun retrofit(): Retrofit {
    val logging = HttpLoggingInterceptor()
    val baseUrl = BuildConfig.baseUrl
    val httpClient = OkHttpClient.Builder()

    logging.level = HttpLoggingInterceptor.Level.BODY

    httpClient.addInterceptor(logging)
    httpClient.interceptors().add( Interceptor { chain ->
        val originalRequest: Request = chain.request()
        val originalHttpUrl: HttpUrl = originalRequest.url()
        val apiKey = BuildConfig.api_key
        
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val requestBuilder: Request.Builder = originalRequest.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        chain.proceed(request)
    })

    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()

    return Retrofit.Builder()
        .callFactory(httpClient.build())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(httpClient.build())
        .build()
}