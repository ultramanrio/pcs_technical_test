package id.ryo.pcs_profile_test_feature_profile.presentation.profile_detail

import id.ryo.pcs_profile_test_domain.models.ProfileData

interface ProfileDetailUseCase {

    suspend fun getProfileDetail(id: String, fromCache: Boolean): ProfileData?

}