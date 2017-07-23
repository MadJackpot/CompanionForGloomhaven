package com.awesome.dexter.companionforgloomhaven.Database

import io.realm.Realm
import com.awesome.dexter.companionforgloomhaven.Characters.*

/**
 * Created by Dan on 7/22/2017.
 */

object CharacterQueries {
    fun GetCharacters(): MutableList<Character> {
        var realm = Realm.getDefaultInstance()
        return realm.where(Character::class.java).findAll().toMutableList()
    }

    fun GetNextID(): Int {
        var realm = Realm.getDefaultInstance()
        return realm.where(Character::class.java).findAll().lastIndex + 1
    }

    fun CreateNewCharacter(name: String = "", race: Int = 0): Character {
        var realm = Realm.getDefaultInstance()
        var character = Character(name, Race.values()[race])
        realm.executeTransaction{
            character = realm.createObject(Character::class.java, CharacterQueries.GetNextID())
            character.name = name
            character.race = race
        }
        return character
    }
}