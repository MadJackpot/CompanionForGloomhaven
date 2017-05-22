package com.awesome.dexter.companionforgloomhaven.Activities

import android.app.Activity
import android.os.Bundle
import com.awesome.dexter.companionforgloomhaven.R.layout.activity_character

/**
 * Created by Dan on 5/20/2017.
 */
class CharacterActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_character)

        val character = intent.extras.getInt(CHARACTER_KEY)
    }
}