package com.kingpixel.cobbledayshiny;

import com.kingpixel.cobbledayshiny.command.CommandTree;
import com.kingpixel.cobbledayshiny.config.Config;
import com.kingpixel.cobbledayshiny.events.PokemonSpawn;
import com.kingpixel.cobbledayshiny.util.UtilsLogger;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.server.MinecraftServer;

public class CobbleDayShiny {
  public static final String MOD_ID = "cobbledayshiny";
  public static final String PATH = "/config/CobbleDayShiny";
  public static final UtilsLogger LOGGER = new UtilsLogger();
  public static final String MOD_NAME = "CobbleDayShiny";
  public static MinecraftServer server;
  public static Config config = new Config();

  public static void init() {
    events();
  }

  public static void load() {
    files();
    sign();
  }


  private static void files() {
    config.init();
  }

  private static void sign() {

  }

  private static void events() {

    LifecycleEvent.SERVER_STARTED.register(server -> load());


    LifecycleEvent.SERVER_STOPPING.register(server -> {
      LOGGER.info("CobbleDayShiny has been stopped.");
    });

    LifecycleEvent.SERVER_LEVEL_LOAD.register(level -> server = level.getLevel().getServer());

    CommandRegistrationEvent.EVENT.register((dispatcher, dedicated, commandSelection) -> {
      CommandTree.register(dispatcher, dedicated);
    });

    PokemonSpawn.register();
  }

}
