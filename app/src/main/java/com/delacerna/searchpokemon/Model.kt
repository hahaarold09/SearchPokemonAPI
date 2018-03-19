package com.delacerna.searchpokemon

/**
 * Created by Harold on 3/13/2018.
 */
class Poke(val id: Int,
           val name: String,
           val sprite: Sprite,
           val height: String,
           val weight: String,
           val types: String,
           val abilities: String,
           val moves1: Move,
           val moves2: Move,
           val moves3: Move,
           val moves4: Move,
           val hp:baseStats,
           val attack:baseStats,
           val defence:baseStats,
           val spAttack:baseStats,
           val spDefence:baseStats,
           val speed:baseStats)

class Sprite(val pokeString: String)

class Move(val pokeMove: String)

class baseStats(val stats: String)

