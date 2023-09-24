package com.eltescode.rpgsession.features.profession.presentation.model

import com.eltescode.rpgsession.features.profession.domain.model.CareerDomain
import com.eltescode.rpgsession.features.profession.domain.model.MainProfileDomain
import com.eltescode.rpgsession.features.profession.domain.model.SecondaryProfileDomain


data class CareerDisplayable(
    val careerName: String,
    val mainProfile: MainProfileDisplayable,
    val secondaryProfile: SecondaryProfileDisplayable,
    val skills: List<String>,
    val talents: List<String>,
    val trappings: List<String>,
    val careerEntries: List<String>,
    val careerExits: List<String>
) {
    constructor(career: CareerDomain) : this(
        careerName = career.careerName,
        mainProfile = MainProfileDisplayable(career.mainProfile),
        secondaryProfile = SecondaryProfileDisplayable(career.secondaryProfile),
        skills = career.skills,
        talents = career.talents,
        trappings = career.trappings,
        careerEntries = career.careerEntries,
        careerExits = career.careerExits
    )

    fun toCareerDomain() = CareerDomain(
        careerName = careerName,
        mainProfile = mainProfile.toMainProfileDomain(),
        secondaryProfile = secondaryProfile.toSecondaryProfileDomain(),
        skills = skills,
        talents = talents,
        trappings = trappings,
        careerEntries = careerEntries,
        careerExits = careerExits
    )
}

data class MainProfileDisplayable(
    val ww: Int,
    val us: Int,
    val k: Int,
    val odp: Int,
    val zr: Int,
    val int: Int,
    val sw: Int,
    val ogd: Int,
) {
    constructor(mainProfile: MainProfileDomain) : this(
        ww = mainProfile.ww,
        us = mainProfile.us,
        k = mainProfile.k,
        odp = mainProfile.odp,
        zr = mainProfile.zr,
        int = mainProfile.int,
        sw = mainProfile.sw,
        ogd = mainProfile.ogd
    )

    fun toMainProfileDomain() = MainProfileDomain(
        ww = ww,
        us = us,
        k = k,
        odp = odp,
        zr = zr,
        int = int,
        sw = sw,
        ogd = ogd
    )
}

data class SecondaryProfileDisplayable(
    val a: Int,
    val zyw: Int,
    val s: Int,
    val wt: Int,
    val sz: Int,
    val mag: Int,
    val po: Int,
    val pp: Int
) {
    constructor(secondaryProfile: SecondaryProfileDomain) : this(
        a = secondaryProfile.a,
        zyw = secondaryProfile.zyw,
        s = secondaryProfile.s,
        wt = secondaryProfile.wt,
        sz = secondaryProfile.sz,
        mag = secondaryProfile.mag,
        po = secondaryProfile.po,
        pp = secondaryProfile.pp
    )

    fun toSecondaryProfileDomain() = SecondaryProfileDomain(
        a = a,
        zyw = zyw,
        s = s,
        wt = wt,
        sz = sz,
        mag = mag,
        po = po,
        pp = pp
    )
}