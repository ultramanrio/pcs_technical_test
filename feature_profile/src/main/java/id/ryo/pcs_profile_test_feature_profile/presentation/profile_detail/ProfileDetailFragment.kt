package id.ryo.pcs_profile_test_feature_profile.presentation.profile_detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.ryo.pcs_profile_test_core.bases.BaseFragment
import id.ryo.pcs_profile_test_feature_profile.databinding.FragmentProfileDetailBinding
import id.ryo.pcs_profile_test_feature_profile.databinding.FragmentProfileListBinding
import id.ryo.pcs_profile_test_feature_profile.presentation.profile_list.ProfileListViewModel

@AndroidEntryPoint
class ProfileDetailFragment : BaseFragment<FragmentProfileDetailBinding>(){

    private val viewModel by viewModels<ProfileDetailViewModel>()
    private var id: String? = null
    private lateinit var binding: FragmentProfileDetailBinding
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileDetailBinding {
        return FragmentProfileDetailBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentProfileDetailBinding.setupEvent() {}

    override suspend fun FragmentProfileDetailBinding.setupState() {}

    @SuppressLint("SetTextI18n")
    override fun FragmentProfileDetailBinding.initBinding() {
        val args: ProfileDetailFragmentArgs by navArgs()
        val userId = args.id
        viewModel.fetchData(userId)
        viewModel.profileData.observe(viewLifecycleOwner) { profiles ->
            if(profiles != null){
                Glide.with(binding.imgProfile.context)
                    .load(profiles.avatar)
                    .into(binding.imgProfile)
                binding.txtFirstName.text = profiles.name?.split(" ")?.get(0).orEmpty()
                binding.txtLastName.text = profiles.name?.split(" ")?.get(1).orEmpty()
                binding.txtAddressName.text = profiles.address_no.orEmpty()+
                        ", "+profiles.street.orEmpty()+
                        ", "+profiles.county.orEmpty()+
                        ", "+profiles.zip_code.orEmpty()+
                        ", "+profiles.country.orEmpty()
            }
        }
    }
}