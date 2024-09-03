package com.kingpixel.cobbledayshiny.fabric;

import com.kingpixel.cobbledayshiny.CobbleDayShiny;
import net.fabricmc.api.ModInitializer;

public class CobbleDayShinyFabric implements ModInitializer {
  @Override
  public void onInitialize() {
    CobbleDayShiny.init();
  }
}
