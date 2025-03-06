package id.ryo.pcs_profile_test_domain.models

import java.io.Serializable

class ProfileData(
    val address_no: String? = null,
    val avatar: String? = null,
    val city: String? = null,
    val country: String? = null,
    val county: String? = null,
    val createdAt: String? = null,
    val id: String = "",
    val name: String? = null,
    val street: String? = null,
    val zip_code: String? = null
) : Serializable