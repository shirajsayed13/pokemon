package com.example.harajtask.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.harajtask.webservice.ListingWebService
import com.example.harajtask.network.service.AppListingWebService
import com.example.harajtask.network.service.RetrofitListingWebService
import com.shiraj.network.AndroidNetworkConnectionMonitor
import com.shiraj.network.NetworkConnectionMonitor
import com.shiraj.network.NetworkConnectionMonitorInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(
        builder: Retrofit.Builder,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return builder
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()

    @Provides
    @Singleton
    internal fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient =
        builder.build()

    @Provides
    @Singleton
    internal fun provideOkHttpClientBuilder(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkConnectionMonitorInterceptor: NetworkConnectionMonitorInterceptor,
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
            addNetworkInterceptor(networkConnectionMonitorInterceptor)
        }
    }

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    @Singleton
    internal fun provideNetworkConnectionMonitor(@ApplicationContext context: Context): NetworkConnectionMonitor =
        AndroidNetworkConnectionMonitor(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)


    @Provides
    @Singleton
    internal fun provideRetrofitListingWebService(retrofit: Retrofit): RetrofitListingWebService =
        retrofit.create(RetrofitListingWebService::class.java)


    @Module
    @InstallIn(SingletonComponent::class)
    internal interface AppWebService {
        @Binds
        @Singleton
        abstract fun bindListingWebService(service: AppListingWebService): ListingWebService
    }

}
