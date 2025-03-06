package id.ryo.pcs_profile_test_feature_profile.presentation.profile_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.ryo.pcs_profile_test_core.bases.BaseFragment
import id.ryo.pcs_profile_test_feature_profile.databinding.FragmentProfileListBinding
import id.ryo.pcs_profile_test_feature_profile.presentation.profile_list.adapter.ProfileListAdapter

@AndroidEntryPoint
class ProfileListFragment : BaseFragment<FragmentProfileListBinding>() {
    private val viewModel by viewModels<ProfileListViewModel>()
    private lateinit var binding: FragmentProfileListBinding

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileListBinding {
        return FragmentProfileListBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentProfileListBinding.setupEvent() {

    }

    override suspend fun FragmentProfileListBinding.setupState() {

    }

    override fun FragmentProfileListBinding.initBinding() {
        val adapter = ProfileListAdapter{ it ->
            val action = ProfileListFragmentDirections.actionListFragmentToDetailFragment(it.id)
            findNavController().navigate(action)
        }
        binding.rvProfileList.adapter = adapter

        viewModel.fetchProfiles()
        viewModel.profiles.observe(viewLifecycleOwner) { profiles ->
            adapter.submitList(profiles)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.skeletonView.visibility = View.VISIBLE // Show skeleton
                binding.rvProfileList.visibility = View.GONE
            } else {
                binding.skeletonView.visibility = View.GONE
                binding.rvProfileList.visibility = View.VISIBLE
            }
        }

        // ✅ Observe error message
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}