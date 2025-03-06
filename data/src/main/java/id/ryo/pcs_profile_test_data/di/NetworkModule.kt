package id.ryo.pcs_profile_test_data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.ryo.pcs_profile_test_data.BuildConfig
import id.ryo.pcs_profile_test_data.source.remote.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context, showNotification = true))
            .maxContentLength(250_000L)
            .redactHeaders(emptySet()) // Add sensitive headers here if needed
            .alwaysReadResponseBody(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(chuckerInterceptor) // Only add Chucker in debug mode
                }
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {

        val defaultJson = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
        val converterFactory = defaultJson.asConverterFactory("application/json".toMediaType())

        return Retrofit.Builder()
            .baseUrl("https://66b197c51ca8ad33d4f482c9.mockapi.io/") // Chan
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(ResultCallAdapterFactory()) // Add your Call Adapter// ge this to your API base URL
            .client(client)
            .build()
    }

}