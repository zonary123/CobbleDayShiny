package com.kingpixel.cobbledayshiny.config;

import com.google.gson.Gson;
import com.kingpixel.cobbledayshiny.CobbleDayShiny;
import com.kingpixel.cobbledayshiny.model.DayShiny;
import com.kingpixel.cobbleutils.Model.PokemonData;
import com.kingpixel.cobbleutils.util.Utils;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * @author Carlos Varas Alonso - 29/04/2024 0:14
 */
@Getter
@ToString
public class Config {
  private boolean debug;
  private boolean active;
  private String prefix;
  private List<DayShiny> dayShiny;


  public Config() {
    debug = false;
    active = false;
    prefix = " ";
    dayShiny = List.of(new DayShiny());
  }

  public void init() {
    CompletableFuture<Boolean> futureRead = Utils.readFileAsync(CobbleDayShiny.PATH, "config.json",
      el -> {
        Gson gson = Utils.newGson();
        Config config = gson.fromJson(el, Config.class);
        debug = config.isDebug();
        active = config.isActive();
        prefix = config.getPrefix();
        dayShiny = config.getDayShiny();
        String data = gson.toJson(this);
        CompletableFuture<Boolean> futureWrite = Utils.writeFileAsync(CobbleDayShiny.PATH, "config.json",
          data);
        if (!futureWrite.join()) {
          CobbleDayShiny.LOGGER.fatal("Could not write config.json file for " + CobbleDayShiny.MOD_NAME + ".");
        }
      });

    if (!futureRead.join()) {
      CobbleDayShiny.LOGGER.info("No config.json file found for" + CobbleDayShiny.MOD_NAME + ". Attempting to generate one.");
      Gson gson = Utils.newGson();
      String data = gson.toJson(this);
      CompletableFuture<Boolean> futureWrite = Utils.writeFileAsync(CobbleDayShiny.PATH, "config.json",
        data);

      if (!futureWrite.join()) {
        CobbleDayShiny.LOGGER.fatal("Could not write config.json file for " + CobbleDayShiny.MOD_NAME + ".");
      }
    }
  }

  public Integer getChance(PokemonData pokemonData) {
    if (CobbleDayShiny.config.isDebug()) {
      CobbleDayShiny.LOGGER.info("PokemonData: " + pokemonData);
    }
    for (DayShiny dayShiny : dayShiny) {
      if (dayShiny.getPokemonData().getPokename().equalsIgnoreCase("*")) return dayShiny.getChance();
      if (dayShiny.getPokemonData().getPokename().equalsIgnoreCase(pokemonData.getPokename()) && dayShiny.getPokemonData().getForm().equalsIgnoreCase("*"))
        return dayShiny.getChance();
      if (dayShiny.getPokemonData().equals(pokemonData) && dayShiny.getPokemonData().getForm().equalsIgnoreCase(pokemonData.getForm())) {
        return dayShiny.getChance();
      }
    }
    return null;
  }

}