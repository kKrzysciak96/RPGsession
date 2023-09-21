package com.eltescode.rpgsession.features.career.domain.model

data class CareerDomainTest(
    val careerName: String = "",
    val mainProfile: MainProfileDomainEnumTestPair = MainProfileDomainEnumTestPair(),
    val secondaryProfile: SecondaryProfileDomainEnumTestPair = SecondaryProfileDomainEnumTestPair(),
    val skills: List<String> = emptyList(),
    val talents: List<String> = emptyList(),
    val trappings: List<String> = emptyList(),
    val careerEntries: List<String> = emptyList(),
    val careerExits: List<String> = emptyList()
)

data class MainProfileDomainEnumTest(
    val ww: MainProfileDomainEnum = MainProfileDomainEnum.ww,
    val us: MainProfileDomainEnum = MainProfileDomainEnum.us,
    val k: MainProfileDomainEnum = MainProfileDomainEnum.k,
    val odp: MainProfileDomainEnum = MainProfileDomainEnum.odp,
    val zr: MainProfileDomainEnum = MainProfileDomainEnum.zr,
    val int: MainProfileDomainEnum = MainProfileDomainEnum.int,
    val sw: MainProfileDomainEnum = MainProfileDomainEnum.sw,
    val ogd: MainProfileDomainEnum = MainProfileDomainEnum.ogd,
)

data class MainProfileDomainEnumTestPair(
    val ww: Pair<String, Int> = MainProfileDomainEnum.ww.toPair(),
    val us: Pair<String, Int> = MainProfileDomainEnum.us.toPair(),
    val k: Pair<String, Int> = MainProfileDomainEnum.k.toPair(),
    val odp: Pair<String, Int> = MainProfileDomainEnum.odp.toPair(),
    val zr: Pair<String, Int> = MainProfileDomainEnum.zr.toPair(),
    val int: Pair<String, Int> = MainProfileDomainEnum.int.toPair(),
    val sw: Pair<String, Int> = MainProfileDomainEnum.sw.toPair(),
    val ogd: Pair<String, Int> = MainProfileDomainEnum.ogd.toPair()
)

data class SecondaryProfileDomainEnumTest(
    val a: Pair<String, Int> = SecondaryProfileDomainEnum.a.toPair(),
    val zyw: Pair<String, Int> = SecondaryProfileDomainEnum.zyw.toPair(),
    val s: Pair<String, Int> = SecondaryProfileDomainEnum.s.toPair(),
    val wt: Pair<String, Int> = SecondaryProfileDomainEnum.wt.toPair(),
    val sz: Pair<String, Int> = SecondaryProfileDomainEnum.sz.toPair(),
    val mag: Pair<String, Int> = SecondaryProfileDomainEnum.mag.toPair(),
    val po: Pair<String, Int> = SecondaryProfileDomainEnum.po.toPair(),
    val pp: Pair<String, Int> = SecondaryProfileDomainEnum.pp.toPair()
)

data class SecondaryProfileDomainEnumTestPair(
    val a: Pair<String, Int> = SecondaryProfileDomainEnum.a.toPair(),
    val zyw: Pair<String, Int> = SecondaryProfileDomainEnum.zyw.toPair(),
    val s: Pair<String, Int> = SecondaryProfileDomainEnum.s.toPair(),
    val wt: Pair<String, Int> = SecondaryProfileDomainEnum.wt.toPair(),
    val sz: Pair<String, Int> = SecondaryProfileDomainEnum.sz.toPair(),
    val mag: Pair<String, Int> = SecondaryProfileDomainEnum.mag.toPair(),
    val po: Pair<String, Int> = SecondaryProfileDomainEnum.po.toPair(),
    val pp: Pair<String, Int> = SecondaryProfileDomainEnum.pp.toPair()
)

enum class MainProfileDomainEnum(val profileName: String, val value: Int) {
    ww("walka wręcz", 5),
    us("umiejętności strzeleckie", 5),
    k("krzepa", 5),
    odp("odporność", 5),
    zr("zręcznośc", 5),
    int("inteligencja", 5),
    sw("siła woli", 5),
    ogd("ogłada", 5);

    fun toPair(): Pair<String, Int> = this.profileName to this.value
}

enum class SecondaryProfileDomainEnum(val profileName: String, val value: Int) {
    a("atak", 5),
    zyw("żywotność", 5),
    s("siła", 5),
    wt("wytrzymałość", 5),
    sz("szybkość", 5),
    mag("magia", 5),
    po("punkty obłędu", 5),
    pp("punkty przeznaczenia", 5);

    fun toPair(): Pair<String, Int> = this.profileName to this.value
}

