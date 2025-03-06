package id.ryo.pcs_profile_test_feature_profile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ryo.pcs_profile_test_data.repo_impl.ProfileRepositoryImpl
import id.ryo.pcs_profile_test_feature_profile.presentation.profile_detail.ProfileDetailUseCase
import id.ryo.pcs_profile_test_feature_profile.presentation.profile_detail.ProfileDetailUseCaseImpl
import id.ryo.pcs_profile_test_feature_profile.presentation.profile_list.ProfileListUseCase
import id.ryo.pcs_profile_test_feature_profile.presentation.profile_list.ProfileListUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Singleton
    @Provides
    fun provideProfileListUseCase(repository: ProfileRepositoryImpl): ProfileListUseCase {
        return ProfileListUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideProfileDetailUseCase(repository: ProfileRepositoryImpl): ProfileDetailUseCase {
        return ProfileDetailUseCaseImpl(repository)
    }


}