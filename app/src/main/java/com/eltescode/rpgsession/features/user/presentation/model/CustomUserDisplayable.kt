package com.eltescode.rpgsession.features.user.presentation.model

import com.eltescode.rpgsession.features.user.domain.model.CustomUserDomain

data class CustomUserDisplayable(
    val photo: String? = null,
    val name: String? = null,
    val surname: String? = null,
    val uid: String = "",
    val email: String? = null
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