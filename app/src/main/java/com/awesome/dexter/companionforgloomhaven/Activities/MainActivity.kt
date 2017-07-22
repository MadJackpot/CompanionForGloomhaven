package com.awesome.dexter.companionforgloomhaven.Activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.awesome.dexter.companionforgloomhaven.Adapters.CharacterAdapter
import com.awesome.dexter.companionforgloomhaven.R.id.deleteAllCharacters
import com.awesome.dexter.companionforgloomhaven.R.layout.*
import com.awesome.dexter.companionforgloomhaven.R.menu.*
import com.awesome.dexter.companionforgloomhaven.Characters.Character
import com.awesome.dexter.companionforgloomhaven.Characters.Race
import com.awesome.dexter.companionforgloomhaven.Characters.getDisplayString
import com.awesome.dexter.companionforgloomhaven.Database.CharacterQueries
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_new_character.view.*
import kotlin.properties.Delegates

val CHARACTER_KEY: String = "CHARKEY"

class MainActivity : AppCompatActivity() {
    private var realm: Realm by Delegates.notNull()
    var characters = mutableListOf<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        Realm.init(this)

        title = "Gloomhaven Companion"
        setSupportActionBar(mainToolbar)

        realm = Realm.getDefaultInstance()
        characters = CharacterQueries.GetCharacters()

        if (characters.isEmpty()) {
            realm.beginTransaction()
            val chars = arrayOf(Character("Jonathan", Race.HumanScoundrel),
                Character("Dan", Race.SavvasCragheart),
                Character("Michele", Race.QuatrylTinkerer),
                Character("Kevin", Race.VermlingMindthief),
                Character("Michelle", Race.OrchidSpellweaver),
                Character("Ryan", Race.InoxBrute))
            for (c in chars){
                var realmChar = realm.createObject(Character::class.java, chars.indexOf(c))
                realmChar.name = c.name
                realmChar.race = c.race
                characters.add(realmChar)
            }

            realm.commitTransaction()
        }

        CharacterListView.onItemClickListener = AdapterView.OnItemClickListener{
            _, _, pos, _->
            val charActivity = Intent(this, CharacterActivity::class.java)
            charActivity.putExtra(CHARACTER_KEY, characters[pos].id)
            startActivity(charActivity)
            UpdateCharacterList()
        }

        attachCharacterAddListener()
        UpdateCharacterList()
    }

    /*override fun onDestroy() {
        super.onDestroy()
    }*/

    fun attachCharacterAddListener() {
        addCharacterButton.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Add Character")
                    .setCancelable(true)
            var newCharSetup = LayoutInflater.from(this).inflate(dialog_new_character, null)
            val raceStrings = Race.values().map{ it.getDisplayString() }
            var characterArray = ArrayAdapter(this, android.R.layout.simple_spinner_item, raceStrings)
            newCharSetup.raceSpinner.adapter = characterArray
            builder.setView(newCharSetup)
            builder.setPositiveButton("OK", {
                _, _ ->
                realm.executeTransaction {
                    var character = realm.createObject(Character::class.java, CharacterQueries.GetNextID())
                    character.name = newCharSetup.newCharacterName.text.toString()
                    character.race = newCharSetup.raceSpinner.selectedItemPosition
                    characters.add(character)
                }
                UpdateCharacterList()
            })

            var alert = builder.create()
            alert.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu != null)
            MenuInflater(this).inflate(main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null)
            return true

        when (item.itemId) {
            deleteAllCharacters -> {
                realm.executeTransaction { characters.forEach { it.deleteFromRealm() } }
                characters.clear()
                UpdateCharacterList()
            }
        }

        return true
    }

    private fun UpdateCharacterList(){
        CharacterListView.adapter = CharacterAdapter(this, android.R.layout.simple_list_item_2, characters)
    }
}
