package id.ryo.pcs_profile_test_feature_profile.presentation.profile_detail

import id.ryo.pcs_profile_test_domain.models.ProfileData
import id.ryo.pcs_profile_test_domain.repo_contracts.ProfileRepository
import javax.inject.Inject

class ProfileDetailUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository
) : ProfileDetailUseCase {

    override suspend fun getProfileDetail(id: String, fromCache: Boolean): ProfileData?{
        return repository.getProfileDetail(id = id, fromCache = fromCache)
    }

}