package com.mateuszholik.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.mateuszholik.network.BuildConfig
import com.mateuszholik.network.interceptors.AuthorizationInterceptor
import com.mateuszholik.network.services.CompetitionService
import com.mateuszholik.network.services.MatchesService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesAuthorizationInterceptor(): AuthorizationInterceptor =
        AuthorizationInterceptor()

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        authorizationInterceptor: AuthorizationInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providesMatchesService(retrofit: Retrofit): MatchesService =
        provideService(retrofit)

    @Provides
    @Singleton
    fun providesCompetitionService(retrofit: Retrofit): CompetitionService =
        provideService(retrofit)
}

private inline fun <reified T> provideService(retrofit: Retrofit): T =
    retrofit.create(T::class.java)
