package com.awesome.dexter.companionforgloomhaven.Adapters

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.awesome.dexter.companionforgloomhaven.Characters.Character

/**
 * Created by Dan on 5/20/2017.
 */
class CharacterAdapter(context: Context, layoutid: Int, characters: List<Character>):
        ArrayAdapter<Character>(context, layoutid, characters) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView: View = parent.inflate(android.R.layout.simple_list_item_2)
        val name: TextView = rowView.findViewById(android.R.id.text1) as TextView
        val description: TextView = rowView.findViewById(android.R.id.text2) as TextView
        val characterTarget = getItem(position)

        name.text = characterTarget.name
        description.text = "${characterTarget.getCharacterRace()} - Level ${characterTarget.level}"
        return rowView
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}