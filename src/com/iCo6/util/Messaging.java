package com.iCo6.util;

import java.util.LinkedHashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Messaging {

    private static CommandSender sender = null;

    /**
     * Converts a list of arguments into points.
     *
     * @param original The original string necessary to convert inside of.
     * @param arguments The list of arguments, multiple arguments are seperated by commas for a single point.
     *
     * @return <code>String</code> - The parsed string after converting arguments to variables (points)
     */
    public static String argument(String original, LinkedHashMap<String, Object> arguments) {
        for (String item: arguments.keySet()) {
            if(item.contains(","))
                for(String i: item.split(","))
                    original = original.replace("+" + i, String.valueOf(arguments.get(item)));
            else {
                original = original.replace("+" + item, String.valueOf(arguments.get(item)));
            }
        }

        return original;
    }

    /**
     * Converts a list of arguments into points.
     *
     * @param original The original string necessary to convert inside of.
     * @param arguments The list of arguments, multiple arguments are seperated by commas for a single point.
     * @param points The point used to alter the argument.
     *
     * @return <code>String</code> - The parsed string after converting arguments to variables (points)
     */
    public static String argument(String original, Object[] arguments, Object[] points) {
        if(arguments.length < 1)
            return original;

        for (int i = 0; i < arguments.length; i++) {
            if (String.valueOf(arguments[i]).contains(",")) {
                for (String arg : String.valueOf(arguments[i]).split(",")) {
                    original = original.replace(arg, String.valueOf(points[i]));
                }
            } else {
                original = original.replace(String.valueOf(arguments[i]), String.valueOf(points[i]));
            }
        }

        return original;
    }

    public static String parse(String original) {
        return colorize(original);

    }

    public static String colorize(String string) {
            /*if(!(sender instanceof Player))
                string = string.replace("`r", "\033[1;31m")                  .replace("`R", "\033[0;31m")
                        .replace("`y", "\033[1;33m")                         .replace("`Y", "\033[0;33m")
                        .replace("`g", "\033[1;32m")                         .replace("`G", "\033[0;32m")
                        .replace("`a", "\033[1;36m")                         .replace("`A", "\033[0;36m")
                        .replace("`b", "\033[1;34m")                         .replace("`B", "\033[0;34m")
                        .replace("`p", "\033[1;35m")                         .replace("`P", "\033[0;35m")
                        .replace("`k", "\033[0;0m")                          .replace("`s", "\033[0;37m")
                        .replace("`S", "\033[1;30m")                         .replace("`w", "\033[1;37m")
                        
                        string.replace("<r>", "\033[0m")                     .replace("`e", "\033[0m")
                        .replace("<silver>", "\033[0;37m")                   .replace("<gray>", "\033[1;30m")
                        .replace("<rose>","\033[1;31m")                      .replace("<lime>","\033[1;32m")
                        .replace("<aqua>","\033[1;36m")                      .replace("<pink>","\033[1;35m")
                        .replace("<yellow>","\033[1;33m")                    .replace("<blue>","\033[1;34m")
                        .replace("<black>", "\033[0;0m")                     .replace("<red>", "\033[0;31m")
                        .replace("<green>", "\033[0;32m")                    .replace("<teal>", "\033[0;36m")
                        .replace("<navy>", "\033[0;34m")                     .replace("<purple>", "\033[0;35m")
                        .replace("<gold>", "\033[0;33m")                     .replace("<white>", "\033[1;37m") + "\033[0m";
                 	*/
            string = string.replace("`e", "")
                        .replace("`r", ChatColor.RED.toString())             .replace("`R", ChatColor.DARK_RED.toString())
                        .replace("`y", ChatColor.YELLOW.toString())          .replace("`Y", ChatColor.GOLD.toString())
                        .replace("`g", ChatColor.GREEN.toString())           .replace("`G", ChatColor.DARK_GREEN.toString())
                        .replace("`a", ChatColor.AQUA.toString())            .replace("`A", ChatColor.DARK_AQUA.toString())
                        .replace("`b", ChatColor.BLUE.toString())            .replace("`B", ChatColor.DARK_BLUE.toString())
                        .replace("`p", ChatColor.LIGHT_PURPLE.toString())    .replace("`P", ChatColor.DARK_PURPLE.toString())
                        .replace("`k", ChatColor.BLACK.toString())           .replace("`s", ChatColor.GRAY.toString())
                        .replace("`S", ChatColor.DARK_GRAY.toString())       .replace("`w", ChatColor.WHITE.toString());

        string = string.replace("<r>", "")
                        .replace("<black>", "\u00A70")                       .replace("<navy>", "\u00A71")
                        .replace("<green>", "\u00A72")                       .replace("<teal>", "\u00A73")
                        .replace("<red>", "\u00A74")                         .replace("<purple>", "\u00A75")
                        .replace("<gold>", "\u00A76")                        .replace("<silver>", "\u00A77")
                        .replace("<gray>", "\u00A78")                        .replace("<blue>", "\u00A79")
                        .replace("<lime>", "\u00A7a")                        .replace("<aqua>", "\u00A7b")
                        .replace("<rose>", "\u00A7c")                        .replace("<pink>", "\u00A7d")
                        .replace("<yellow>", "\u00A7e")                      .replace("<white>", "\u00A7f");

        return string.replace("&", "§");
    }

    /**
     * Save the player to be sent messages later. Ease of use sending messages.
     * <br /><br />
     * Example:
     * <blockquote><pre>
     * Messaging.save(player);
     * Messaging.send("This will go to the player saved.");
     * </pre></blockquote>
     *
     * @param player The player we wish to save for later.
     */
        
    public static void save(CommandSender sender) {
        Messaging.sender = sender;
    }
    
    /*
    public static void send(Player player, String message) {
        player.sendMessage(parse(message));
    }
	*/

    public static void send(CommandSender sender, String message) {
        sender.sendMessage(parse(message));
    }

    public static void send(String message) {
        if (Messaging.sender != null) {
            sender.sendMessage(parse(message));
        }
    }
}
