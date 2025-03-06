package id.ryo.pcs_profile_test_data.repo_impl

import id.ryo.pcs_profile_test_core.utils.network.ResultWrapper
import id.ryo.pcs_profile_test_core.utils.network.safeApiCall
import id.ryo.pcs_profile_test_core.utils.network.safeApiCallWithCaching
import id.ryo.pcs_profile_test_data.source.local.ProfileDao
import id.ryo.pcs_profile_test_data.source.local.toEntity
import id.ryo.pcs_profile_test_data.source.local.toResponse
import id.ryo.pcs_profile_test_data.source.remote.ProfileHttpService
import id.ryo.pcs_profile_test_domain.models.ProfileData
import id.ryo.pcs_profile_test_domain.repo_contracts.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileHttpService: ProfileHttpService,
    private val dao: ProfileDao
) : ProfileRepository {

    override suspend fun getProfileList(caching: Boolean): Flow<ResultWrapper<List<ProfileData>>> {
        return if (caching) {
            safeApiCallWithCaching(
                apiCall = profileHttpService::getList,
                saveToLocal = { dao.insertAll(it.map {data -> data.toEntity() }) },
                getFromLocal = { dao.getAll().firstOrNull()?.map { it.toResponse() } ?: emptyList() }
            )
        } else safeApiCall (
            apiCall = { profileHttpService.getList() }
        )
    }

    override suspend fun getProfileDetail(
        id: String,
        fromCache: Boolean
    ): ProfileData? {
        return dao.getById(id).firstOrNull()?.toResponse()
    }


}