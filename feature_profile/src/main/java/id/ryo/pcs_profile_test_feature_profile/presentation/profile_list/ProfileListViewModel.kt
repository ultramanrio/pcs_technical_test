package id.ryo.pcs_profile_test_feature_profile.presentation.profile_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ryo.pcs_profile_test_core.utils.network.ResultWrapper
import id.ryo.pcs_profile_test_domain.models.ProfileData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileListViewModel @Inject constructor(
    private val profileListUseCase: ProfileListUseCase
): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _profiles = MutableLiveData<List<ProfileData>>()
    val profiles: LiveData<List<ProfileData>> = _profiles

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun fetchProfiles() {
        viewModelScope.launch {

            when (val result = profileListUseCase.getProfileList()?.asLiveData()?.value) {
                is ResultWrapper.Success<List<ProfileData>> -> {
                    _isLoading.value = false
                    _profiles.value = result.data
                }
                is ResultWrapper.Error -> {
                    _isLoading.value = false
                    _errorMessage.value = result.message ?: "Unknown error occurred"
                }
                is ResultWrapper.Loading -> {
                    _isLoading.value = true
                }
                is ResultWrapper.NetworkError -> {
                    _isLoading.value = false
                    _errorMessage.value = "Network error occurred"
                }

                null -> {_isLoading.value = false}
            }

            _isLoading.value = false
        }
    }

}