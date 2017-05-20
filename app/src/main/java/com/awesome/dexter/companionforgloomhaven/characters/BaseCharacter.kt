package com.awesome.dexter.companionforgloomhaven.characters

/**
 * Created by Dan on 5/20/2017.
 */
open class BaseCharacter (val name: String){
    var level = 1
    var experience = 0
    var gold = 0
    var notes = ""

    open fun getCharacterRace(): String {
        throw CharacterException("An invalid character was created!")
    }

    class CharacterException(message: String) : Exception(message)
}