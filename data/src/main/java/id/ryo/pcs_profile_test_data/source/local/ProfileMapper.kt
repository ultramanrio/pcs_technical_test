package id.ryo.pcs_profile_test_data.source.local

import id.ryo.pcs_profile_test_domain.models.ProfileData

fun ProfileEntity.toResponse() = ProfileData(
    id = id,
    createdAt = createdAt,
    name = name,
    avatar = avatar,
    city = city,
    county = county,
    country = country,
    street = street,
    address_no = addressNo,
    zip_code = zipCode
)

fun ProfileData.toEntity() = ProfileEntity(
    id = id,
    createdAt = createdAt,
    name = name,
    avatar = avatar,
    city = city,
    county = county,
    country = country,
    street = street,
    addressNo = address_no,
    zipCode = zip_code
)