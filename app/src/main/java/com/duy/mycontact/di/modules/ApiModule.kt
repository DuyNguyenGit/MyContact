package com.duy.mycontact.di.modules

import android.util.Log
import com.duy.mycontact.BuildConfig
import com.duy.mycontact.api.ContactApi
import com.duy.mycontact.api.LoginApi
import com.duy.mycontact.api.LoginApiImpl
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val TIME_OUT: Long = 30

val apiModule = module {
    factory { provideHttpLoggingInterceptor() }
    factory { provideGson() }
    single { provideRetrofit(get(), get()) }
    factory { provideOkHttpClient(get()) }
    factory { provideContactApi(get()) }
    factory { provideLoginApi(get()) }
}

fun provideContactApi(retrofit: Retrofit): ContactApi = retrofit.create(ContactApi::class.java)

fun provideLoginApi(retrofit: Retrofit): LoginApi = LoginApiImpl()

fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://reqres.in/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideGson(): Gson {
    return GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setLenient()
        .create()
}

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

    val builder = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        builder.addInterceptor(httpLoggingInterceptor)
    }

    return builder.build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.i("My Contact", "log: $message")
        }
    })
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}