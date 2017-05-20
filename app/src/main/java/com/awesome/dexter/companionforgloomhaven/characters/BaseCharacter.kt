package com.awesome.dexter.companionforgloomhaven.characters

/**
 * Created by Dan on 5/20/2017.
 */
open class BaseCharacter (val name: String){
    var level: Int = 1
    var experience: Int = 0
    var gold: Int = 0
    var notes: String = ""

    open fun getCharacterRace(): String {
        return "Busted"
    }
}