package com.kingpixel.cobbledayshiny.command;

import com.kingpixel.cobbledayshiny.CobbleDayShiny;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

/**
 * @author Carlos Varas Alonso - 10/06/2024 14:08
 */
public class CommandTree {

  public static void register(
    CommandDispatcher<CommandSourceStack> dispatcher,
    CommandBuildContext registry) {
    LiteralArgumentBuilder<CommandSourceStack> base = Commands.literal("cobbledayshiny");
    dispatcher.register(
      base.requires(
        source -> source.hasPermission(2)
      ).then(
        Commands.literal("reload").executes(
          context -> {
            CobbleDayShiny.load();
            return 1;
          }
        )
      )
    );
  }

}
