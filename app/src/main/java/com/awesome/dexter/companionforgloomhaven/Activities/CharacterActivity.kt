package com.awesome.dexter.companionforgloomhaven.Activities

import android.app.Activity
import android.os.Bundle
import com.awesome.dexter.companionforgloomhaven.R.layout.activity_character
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.*
import com.awesome.dexter.companionforgloomhaven.Characters.*
import kotlinx.android.synthetic.main.activity_character.*

/**
 * Created by Dan on 5/20/2017.
 */
class CharacterActivity : Activity(){
    var character = Character()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_character)

        FlowManager.init(this)

        val character_id = intent.extras.getInt(CHARACTER_KEY)
        //character = (select from Character::class where ( eq character_id) ).result ?: Character()
        CharacterRace.text;
    }
}