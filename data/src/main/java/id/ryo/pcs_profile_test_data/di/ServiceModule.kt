package id.ryo.pcs_profile_test_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ryo.pcs_profile_test_data.source.remote.ProfileHttpService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideProfileHttpService(retrofit: Retrofit): ProfileHttpService {
        return retrofit.create(ProfileHttpService::class.java)
    }

}