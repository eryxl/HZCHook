package com.example.hzchook;
import java.io.IOException;
import org.bukkit.plugin.java.JavaPlugin;
import ch.njol.skript.SkriptPlugin;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

/**
 * HZCHook - A Skript plugin for sending webhooks to external servers
 * 
 * Features:
 * - Send JSON data to any webhook endpoint
 * - Supports complex data structures including maps and lists
 * - Built-in error handling and logging
 * - Compatible with Skript 2.7.3
 */

ExampleMain instance;
SkriptAddon addon;

public class HZCHook extends SkriptPlugin {
    @Override
    public void onEnable() {
        instance = this;
       addon = Skript.registerAddon(this);
       try {
           //This will register all our syntax for us. Explained below
           addon.loadClasses("me.limeglass.addon", "elements");
       } catch (IOException e) {
           e.printStackTrace();
       }
       Bukkit.getLogger().info("[ExampleAddon] has been enabled!");
   }
 
   public ExampleMain getInstance() {
       return instance;
   }
 
   public SkriptAddon getAddonInstance() {
       return addon;
   }
        // Register our webhook syntax
        WebhookSyntax.register(this);
    }
}
