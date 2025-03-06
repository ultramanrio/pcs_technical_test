package id.ryo.pcs_profile_test_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ryo.pcs_profile_test_data.repo_impl.ProfileRepositoryImpl
import id.ryo.pcs_profile_test_data.source.local.ProfileDao
import id.ryo.pcs_profile_test_data.source.remote.ProfileHttpService
import id.ryo.pcs_profile_test_domain.repo_contracts.ProfileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideProfileRepository(profileHttpService: ProfileHttpService,
                                 dao: ProfileDao
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileHttpService, dao)
    }
}