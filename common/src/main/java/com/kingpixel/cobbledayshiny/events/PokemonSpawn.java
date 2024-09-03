package com.kingpixel.cobbledayshiny.events;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.kingpixel.cobbledayshiny.CobbleDayShiny;
import com.kingpixel.cobbleutils.Model.PokemonData;
import com.kingpixel.cobbleutils.util.Utils;
import kotlin.Unit;

/**
 * @author Carlos Varas Alonso - 27/07/2024 9:06
 */
public class PokemonSpawn {
  public static void register() {
    CobblemonEvents.POKEMON_ENTITY_SPAWN.subscribe(Priority.NORMAL, (evt) -> {
      if (!CobbleDayShiny.config.isActive()) return Unit.INSTANCE;
      Pokemon pokemon = evt.getEntity().getPokemon();
      if (pokemon.getShiny()) {
        return Unit.INSTANCE;
      }


      Integer chance = CobbleDayShiny.config.getChance(PokemonData.from(pokemon));

      if (chance == null) return Unit.INSTANCE;


      if (Utils.RANDOM.nextInt(0, chance) == 0) {
        pokemon.setShiny(true);
      }

      return Unit.INSTANCE;
    });
  }
}
