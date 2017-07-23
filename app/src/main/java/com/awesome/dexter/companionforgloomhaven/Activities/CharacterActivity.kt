package com.awesome.dexter.companionforgloomhaven.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.awesome.dexter.companionforgloomhaven.Characters.Character
import com.awesome.dexter.companionforgloomhaven.R.layout.activity_character
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_character.*
import kotlin.properties.Delegates

/**
 * Created by Dan on 5/20/2017.
 */
class CharacterActivity : AppCompatActivity(){
    var character = Character()
    private var realm: Realm by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_character)
        setSupportActionBar(CharacterToolbar)
        realm = Realm.getDefaultInstance()

        val characterId = intent.extras.getInt(CHARACTER_KEY)
        character = realm.where(Character::class.java).findAll().filter{it.id == characterId}.firstOrNull() ?: Character()
        title = character.name
        CharacterRace.text = character.getCharacterRace()
        CharacterLevel.text = character.level.toString()
        CharacterExperience.text = character.experience.toString()
    }
}