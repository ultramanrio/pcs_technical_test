package id.ryo.pcs_profile_test_domain.repo_contracts

import id.ryo.pcs_profile_test_core.utils.network.ResultWrapper
import id.ryo.pcs_profile_test_domain.models.ProfileData
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getProfileList(caching: Boolean): Flow<ResultWrapper<List<ProfileData>>>?
    suspend fun getProfileDetail(id: String, fromCache: Boolean): ProfileData?
}