package com.kingpixel.cobbledayshiny.model;

import com.kingpixel.cobbleutils.Model.PokemonData;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Carlos Varas Alonso - 27/07/2024 9:25
 */
@Getter
@ToString
public class DayShiny {
  PokemonData pokemonData;
  Integer chance;

  public DayShiny(PokemonData pokemonData, Integer chance) {
    this.pokemonData = pokemonData;
    this.chance = chance;
  }

  public DayShiny(String pokemon, String form, Integer chance) {
    this.pokemonData = new PokemonData(pokemon, form);
    this.chance = chance;
  }

  public DayShiny(String pokemon, Integer chance) {
    this.pokemonData = new PokemonData(pokemon, "normal");
    this.chance = chance;
  }

  public DayShiny(String pokemon) {
    this.pokemonData = new PokemonData(pokemon, "normal");
    this.chance = 2048;
  }

  public DayShiny() {
    this.pokemonData = new PokemonData("bulbasaur", "normal");
    this.chance = 2048;
  }

  
}
