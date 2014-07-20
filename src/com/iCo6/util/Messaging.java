package com.iCo6.util;

import java.util.LinkedHashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        original = colorize(original);
        return original.replaceAll("(&([a-z0-9]))", "\u00A7$2").replace("&&", "&");

    }

    public static String colorize(String string) {
    	
        string = string.replace("<r>", "")
                        .replace("<black>", "\u00A70")                       .replace("<navy>", "\u00A71")
                        .replace("<green>", "\u00A72")                       .replace("<teal>", "\u00A73")
                        .replace("<red>", "\u00A74")                         .replace("<purple>", "\u00A75")
                        .replace("<gold>", "\u00A76")                        .replace("<silver>", "\u00A77")
                        .replace("<gray>", "\u00A78")                        .replace("<blue>", "\u00A79")
                        .replace("<lime>", "\u00A7a")                        .replace("<aqua>", "\u00A7b")
                        .replace("<rose>", "\u00A7c")                        .replace("<pink>", "\u00A7d")
                        .replace("<yellow>", "\u00A7e")                      .replace("<white>", "\u00A7f");

        return string;
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
    
    public static void send(Player player, String message) {
        player.sendMessage(parse(message));
    }

    public static void send(CommandSender sender, String message) {
        sender.sendMessage(parse(message));
    }

    public static void send(String message) {
        if (Messaging.sender != null) {
            sender.sendMessage(parse(message));
        }
    }
}
