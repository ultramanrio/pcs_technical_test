package id.ryo.pcs_profile_test_feature_profile.presentation.profile_list

import id.ryo.pcs_profile_test_core.utils.network.ResultWrapper
import id.ryo.pcs_profile_test_domain.models.ProfileData
import kotlinx.coroutines.flow.Flow

interface ProfileListUseCase {
    suspend fun getProfileList(): Flow<ResultWrapper<List<ProfileData>>>?
}