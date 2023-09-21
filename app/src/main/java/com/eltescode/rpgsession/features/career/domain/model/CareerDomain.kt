package com.eltescode.rpgsession.features.career.domain.model

data class CareerDomain(
    val careerName: String = "",
    val mainProfile: MainProfileDomain = MainProfileDomain(),
    val secondaryProfile: SecondaryProfileDomain = SecondaryProfileDomain(),
    val skills: List<String> = emptyList(),
    val talents: List<String> = emptyList(),
    val trappings: List<String> = emptyList(),
    val careerEntries: List<String> = emptyList(),
    val careerExits: List<String> = emptyList()
)


data class MainProfileDomain(
    val ww: Int = 0,
    val us: Int = 0,
    val k: Int = 0,
    val odp: Int = 0,
    val zr: Int = 0,
    val int: Int = 0,
    val sw: Int = 0,
    val ogd: Int = 0,
)

data class SecondaryProfileDomain(
    val a: Int = 0,
    val zyw: Int = 0,
    val s: Int = 0,
    val wt: Int = 0,
    val sz: Int = 0,
    val mag: Int = 0,
    val po: Int = 0,
    val pp: Int = 0,
)

