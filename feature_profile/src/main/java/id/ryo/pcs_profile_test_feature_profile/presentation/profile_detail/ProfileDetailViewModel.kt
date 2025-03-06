package id.ryo.pcs_profile_test_feature_profile.presentation.profile_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ryo.pcs_profile_test_domain.models.ProfileData
import id.ryo.pcs_profile_test_feature_profile.presentation.profile_list.ProfileListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    private val profileDetailUseCase: ProfileDetailUseCase
): ViewModel(){

    private val _profileData = MutableLiveData<ProfileData?>()
    val profileData: LiveData<ProfileData?> get() = _profileData

    fun fetchData(id: String){
        viewModelScope.launch {
            try {
                val result = profileDetailUseCase.getProfileDetail(id, true)
                if (result != null) _profileData.value = result
            }catch (e: Throwable){
                e.stackTrace
            }

        }
    }
}