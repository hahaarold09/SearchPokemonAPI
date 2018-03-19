package com.delacerna.searchpokemon


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val POKEMON_KEY_ID = "id"
    private val POKEMON_KEY_NAME = "name"
    private val POKEMON_KEY_SPRITES = "sprites"
    private val POKEMON_KEY_FRONT_DEFAULT = "front_default"
    private val addPokemon = ArrayList<Poke>()
    private val url = "https://pokeapi.co/api/v2/pokemon/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.INVISIBLE

        btnSearch.setOnClickListener {
            addPokemon.clear()
            progressBar.visibility = View.VISIBLE
            scrollView.visibility = View.INVISIBLE
            linearLayout1.visibility = View.INVISIBLE
            imgPokemon.visibility = View.INVISIBLE
            btnFYI.visibility = View.INVISIBLE
            txtPokemonNum.visibility = View.INVISIBLE
            txtPokemonName.visibility = View.INVISIBLE
            searchPokemon()
        }
        btnFYI.setOnClickListener {
            linearLayout1.visibility = View.VISIBLE
            scrollView.visibility = View.VISIBLE

        }

        recyclerView.layoutManager = LinearLayoutManager(this)
    }


    private fun searchPokemon() {

        doAsync {

            var tempPokeName = edtTxtSearchPokemon.text.toString()
            val resultJson = URL(url + tempPokeName).readText()

            val jsonObject = JSONObject(resultJson)
            val pokeID = jsonObject.getInt(POKEMON_KEY_ID)
            val pokeName = jsonObject.getString(POKEMON_KEY_NAME)
            val pokeSprites = jsonObject.getJSONObject(POKEMON_KEY_SPRITES).getString(POKEMON_KEY_FRONT_DEFAULT)


            val pokeHeight = jsonObject.getString("height")
            val pokeWeight = jsonObject.getString("weight")
//type
            val pokeType = jsonObject.getJSONArray("types").getJSONObject(0)
                    .getJSONObject("type").getString("name")
//moves
            val pokeMoves1 = jsonObject.getJSONArray("moves").getJSONObject(0)
                    .getJSONObject("move").getString("name")
            val pokeMoves2 = jsonObject.getJSONArray("moves").getJSONObject(1)
                    .getJSONObject("move").getString("name")
            val pokeMoves3 = jsonObject.getJSONArray("moves").getJSONObject(2)
                    .getJSONObject("move").getString("name")
            val pokeMoves4 = jsonObject.getJSONArray("moves").getJSONObject(3)
                    .getJSONObject("move").getString("name")
//ability
            val pokeAbilities = jsonObject.getJSONArray("abilities").getJSONObject(0)
                    .getJSONObject("ability").getString("name")
//stats
            val pokeHP = jsonObject.getJSONArray("stats").getJSONObject(0).getString("base_stat")
            val pokeAttack = jsonObject.getJSONArray("stats").getJSONObject(1).getString("base_stat")
            val pokeDefence = jsonObject.getJSONArray("stats").getJSONObject(2).getString("base_stat")
            val pokeSpAttack = jsonObject.getJSONArray("stats").getJSONObject(3).getString("base_stat")
            val pokeSpDefence = jsonObject.getJSONArray("stats").getJSONObject(4).getString("base_stat")
            val pokeSpeed = jsonObject.getJSONArray("stats").getJSONObject(5).getString("base_stat")

            uiThread {
                recyclerView.adapter = PokemonAdapter(addPokemon)
                addPokemon.add(Poke(pokeID,
                        pokeName,
                        Sprite(pokeSprites),
                        pokeHeight,
                        pokeWeight,
                        pokeType,
                        pokeAbilities,
                        Move(pokeMoves1),
                        Move(pokeMoves2),
                        Move(pokeMoves3),
                        Move(pokeMoves4),
                        baseStats(pokeHP),
                        baseStats(pokeAttack),
                        baseStats(pokeDefence),
                        baseStats(pokeSpAttack),
                        baseStats(pokeSpDefence),
                        baseStats(pokeSpeed)))

                when {
                    pokeID < 10 -> txtPokemonNum.text = "#00" + pokeID
                    pokeID > 100 -> txtPokemonNum.text = "#" + pokeID
                    pokeID > 10 -> txtPokemonNum.text = "#0" + pokeID
                }

                txtPokemonName.text = pokeName.substring(0, 1).toUpperCase() + pokeName.substring(1)


                Picasso.with(this@MainActivity).load(pokeSprites).into(imgPokemon)

                imgPokemon.visibility = View.VISIBLE
                btnFYI.visibility = View.VISIBLE
                txtPokemonNum.visibility = View.VISIBLE
                txtPokemonName.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE


            }
        }
    }
}
