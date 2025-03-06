package id.ryo.pcs_profile_test_data.source.remote

import id.ryo.pcs_profile_test_domain.models.ProfileData
import retrofit2.Response
import retrofit2.http.GET

interface ProfileHttpService {

    @GET(value = "getData/test")
    suspend fun getList() : Response<List<ProfileData>>

}