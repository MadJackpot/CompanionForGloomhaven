package com.awesome.dexter.companionforgloomhaven.Activities

import android.app.Activity
import android.os.Bundle
import com.awesome.dexter.companionforgloomhaven.Characters.*
import com.awesome.dexter.companionforgloomhaven.Characters.Character_Table.id
import com.awesome.dexter.companionforgloomhaven.R.layout.activity_character
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.*
import com.raizlabs.android.dbflow.sql.language.SQLite

/**
 * Created by Dan on 5/20/2017.
 */
class CharacterActivity : Activity(){
    var character: Character? = Character()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_character)

        FlowManager.init(this)

        val characterId = intent.extras.getInt(CHARACTER_KEY)
        SQLite.select().from(Character::class)
        character = (select from Character::class where (id eq characterId)).result
        title = character?.name ?: "Error"

    }
}