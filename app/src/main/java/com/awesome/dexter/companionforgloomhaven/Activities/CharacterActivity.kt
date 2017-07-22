package com.awesome.dexter.companionforgloomhaven.Activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.awesome.dexter.companionforgloomhaven.Characters.*
import com.awesome.dexter.companionforgloomhaven.Characters.Character_Table.id
import com.awesome.dexter.companionforgloomhaven.R.layout.activity_character
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.*
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.android.synthetic.main.activity_character.*

/**
 * Created by Dan on 5/20/2017.
 */
class CharacterActivity : AppCompatActivity(){
    var character = Character()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_character)
        setSupportActionBar(CharacterToolbar)

        FlowManager.init(this)

        val characterId = intent.extras.getInt(CHARACTER_KEY)
        SQLite.select().from(Character::class)
        character = (select from Character::class where (id eq characterId)).result ?: Character()
        title = character.name
        CharacterRace.text = character.getCharacterRace()
        CharacterLevel.text = character.level.toString()
        CharacterExperience.text = character.experience.toString()
    }
}