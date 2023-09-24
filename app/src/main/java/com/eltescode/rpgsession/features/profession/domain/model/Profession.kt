package com.eltescode.rpgsession.features.profession.domain.model

data class Profession(
    val professionName: String = "",
    val mainProfile: MainProfile = MainProfile(),
    val secondaryProfile: SecondaryProfile = SecondaryProfile(),
    val skills: String,
    val trappings: String,
    val professionEntries: String,
    val professionExits: String
)


data class MainProfile(
    val ww: Int = 0,
    val us: Int = 0,
    val k: Int = 0,
    val odp: Int = 0,
    val zr: Int = 0,
    val int: Int = 0,
    val sw: Int = 0,
    val ogd: Int = 0,
)

data class SecondaryProfile(
    val a: Int = 0,
    val zyw: Int = 0,
    val s: Int = 0,
    val wt: Int = 0,
    val sz: Int = 0,
    val mag: Int = 0,
    val po: Int = 0,
    val pp: Int = 0,
)

