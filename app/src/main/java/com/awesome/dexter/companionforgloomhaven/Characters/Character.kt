package com.awesome.dexter.companionforgloomhaven.Characters

import io.realm.RealmObject
import io.realm.annotations.*

/**
 * Created by Dan on 5/20/2017.
 */
open class Character(var name: String = "", race: Race = Race.HumanScoundrel,  @PrimaryKey var id: Int = 0) : RealmObject() {
    var level = 1
    var experience = 0
    var gold = 0
    var notes = ""
    var race = race.ordinal

    open fun getCharacterRace(): String {
        return Race.values()[race].getDisplayString()
    }
}

enum class Race {HumanScoundrel, InoxBrute, OrchidSpellweaver, QuatrylTinkerer, SavvasCragheart, VermlingMindthief}

fun Race.getDisplayString(): String {
    return when (this) {
        Race.HumanScoundrel -> "Human Scoundrel"
        Race.InoxBrute -> "Inox Brute"
        Race.OrchidSpellweaver -> "Orchid Spellweaver"
        Race.QuatrylTinkerer -> "Quatryl Tinkerer"
        Race.SavvasCragheart -> "Savvas Cragheart"
        Race.VermlingMindthief -> "Vermling Mindthief"
    }
}