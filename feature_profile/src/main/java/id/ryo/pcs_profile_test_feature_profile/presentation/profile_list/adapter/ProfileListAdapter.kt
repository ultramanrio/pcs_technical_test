package id.ryo.pcs_profile_test_feature_profile.presentation.profile_list.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ryo.pcs_profile_test_core.utils.formatDate
import id.ryo.pcs_profile_test_domain.models.ProfileData
import id.ryo.pcs_profile_test_feature_profile.databinding.ItemProfileBinding

class ProfileListAdapter(private val onItemClick: (ProfileData) -> Unit) :
    ListAdapter<ProfileData, ProfileListAdapter.ProfileViewHolder>(ProfileDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = getItem(position)
        holder.bind(profile)
    }

    inner class ProfileViewHolder(private val binding: ItemProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(profile: ProfileData) {
            val dob = profile.createdAt.formatDate()
            binding.txtFirstName.text = profile.name
            binding.txtLastName.text = dob
            binding.imgviewProfilePic

            Glide.with(binding.imgviewProfilePic.context)
                .load(profile.avatar)
                .into(binding.imgviewProfilePic)

            // Handle item click
            binding.root.setOnClickListener {
                onItemClick(profile)
            }
        }
    }

    class ProfileDiffCallback : DiffUtil.ItemCallback<ProfileData>() {
        override fun areItemsTheSame(oldItem: ProfileData, newItem: ProfileData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProfileData, newItem: ProfileData): Boolean {
            return oldItem.id == newItem.id
        }
    }
}