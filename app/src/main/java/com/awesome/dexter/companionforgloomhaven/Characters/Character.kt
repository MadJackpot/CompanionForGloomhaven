package com.awesome.dexter.companionforgloomhaven.Characters

import com.awesome.dexter.companionforgloomhaven.Database.GloomhavenDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table

/**
 * Created by Dan on 5/20/2017.
 */
@Table(database = GloomhavenDatabase::class)
open class Character(@Column var name: String = "", race: Race = Race.HumanScoundrel, @PrimaryKey(autoincrement = true) var id: Int = 0){
    @Column var level = 1
    @Column var experience = 0
    @Column var gold = 0
    @Column var notes = ""
    @Column var race = race.ordinal

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