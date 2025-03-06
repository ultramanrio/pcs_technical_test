package id.ryo.pcs_profile_test_feature_profile.presentation.profile_list

import id.ryo.pcs_profile_test_core.utils.network.ResultWrapper
import id.ryo.pcs_profile_test_domain.models.ProfileData
import id.ryo.pcs_profile_test_domain.repo_contracts.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileListUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository
): ProfileListUseCase {

    override suspend fun getProfileList(): Flow<ResultWrapper<List<ProfileData>>>? {
        return repository.getProfileList(true)
    }

}