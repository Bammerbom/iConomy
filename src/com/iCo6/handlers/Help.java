package com.iCo6.handlers;

import java.util.LinkedHashMap;

import org.bukkit.command.CommandSender;

import com.iCo6.iConomy;
import com.iCo6.command.Handler;
import com.iCo6.command.Parser.Argument;
import com.iCo6.command.exceptions.InvalidUsage;
import com.iCo6.util.Messaging;

public class Help extends Handler {

	public Help(iConomy plugin) {
		super(plugin, iConomy.Template);
	}

	@Override
	public boolean perform(CommandSender sender, LinkedHashMap<String, Argument> arguments) throws InvalidUsage {
		if(!hasPermissions(sender, "help")) {
			template.noPermission(sender);
			return false;
		}

		// TODO: add support for checking help on single command.

		Messaging.send(sender, " ");
		Messaging.send(sender, "&f iConomy (&eCelty&f)");
		Messaging.send(sender, "&f ");
		Messaging.send(sender, "&8 [] &fRequired, &8() &7Optional");
		Messaging.send(sender, " ");

		for (String action : plugin.Commands.getHelp().keySet()) {
			if(!hasPermissions(sender, action))
				continue;

			String description = plugin.Commands.getHelp(action)[1];
			String command = "";

			if(action.equalsIgnoreCase("money") || action.equalsIgnoreCase("money+"))
				command = "/money &f" + plugin.Commands.getHelp(action)[0] + "&8";
			else
				command = "/money &f" + action + plugin.Commands.getHelp(action)[0] + "&8";
			command = command.replace("[", "&8[&8").replace("]", "&8]").replace("(", "&8(");
			Messaging.send(sender, String.format(" %1$s &e-&e %2$s", command, description));
		}

		return false;
	}
}
