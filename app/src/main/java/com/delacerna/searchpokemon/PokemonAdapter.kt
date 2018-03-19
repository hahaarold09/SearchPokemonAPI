package com.delacerna.searchpokemon

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.poke_list_layout.view.*

/**
 * Created by Harold on 3/13/2018.
 */
class PokemonAdapter(private val poke: ArrayList<Poke>) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return poke.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
//        txtPokemonName.text = pokeName.substring(0, 1).toUpperCase() + pokeName.substring(1)

        holder?.view?.txtHeight?.text = poke[position].height
        holder?.view?.txtWeight?.text = poke[position].weight

        holder?.view?.txtType?.text = poke[position].types.substring(0, 1).toUpperCase() +poke[position].types.substring(1)

        holder?.view?.txtAbility?.text = poke[position].abilities.substring(0, 1).toUpperCase() + poke[position].abilities.substring(1)

        holder?.view?.move1?.text = poke[position].moves1.pokeMove.substring(0, 1).toUpperCase() +  poke[position].moves1.pokeMove.substring(1)
        holder?.view?.move2?.text = poke[position].moves2.pokeMove.substring(0, 1).toUpperCase() +  poke[position].moves2.pokeMove.substring(1)
        holder?.view?.move3?.text = poke[position].moves3.pokeMove.substring(0, 1).toUpperCase() +  poke[position].moves3.pokeMove.substring(1)
        holder?.view?.move4?.text = poke[position].moves4.pokeMove.substring(0, 1).toUpperCase() +  poke[position].moves4.pokeMove.substring(1)

        holder?.view?.txtHP?.text = poke[position].hp.stats
        holder?.view?.txtAttack?.text = poke[position].attack.stats
        holder?.view?.txtDefense?.text = poke[position].defence.stats
        holder?.view?.txtSpAttack?.text = poke[position].spAttack.stats
        holder?.view?.txtSpDefense?.text = poke[position].spDefence.stats
        holder?.view?.txtSpeed?.text = poke[position].speed.stats



    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder? {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.poke_list_layout, parent, false)
        return CustomViewHolder(view)
    }


}

class CustomViewHolder(var view: View) : RecyclerView.ViewHolder(view) {


}