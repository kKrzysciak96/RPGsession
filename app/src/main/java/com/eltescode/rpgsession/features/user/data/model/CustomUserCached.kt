package com.eltescode.rpgsession.features.user.data.model

import com.eltescode.rpgsession.features.user.domain.model.CustomUserDomain

data class CustomUserCached(
    val photo: String?,
    val name: String?,
    val surname: String?,
    val uid: String,
    val email: String?
) {
    constructor(user: CustomUserDomain) : this(
        photo = user.photo,
        name = user.name,
        surname = user.surname,
        uid = user.uid,
        email = user.email
    )

    fun toCustomUserDomain() = CustomUserDomain(
        photo = photo,
        name = name,
        surname = surname,
        uid = uid,
        email = email
    )
}

