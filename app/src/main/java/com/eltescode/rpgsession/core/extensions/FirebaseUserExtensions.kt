package com.eltescode.rpgsession.core.extensions

import com.eltescode.rpgsession.features.user.domain.model.CustomUserDomain
import com.google.firebase.auth.FirebaseUser


fun FirebaseUser.mapToCustomUser() =
    CustomUserDomain(
        name = displayName,
        surname = "",
        photo = photoUrl.toString(),
        uid = uid,
        email = email,
    )