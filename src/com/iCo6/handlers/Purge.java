package com.iCo6.handlers;

import java.util.LinkedHashMap;

import org.bukkit.command.CommandSender;

import com.iCo6.iConomy;
import com.iCo6.command.Handler;
import com.iCo6.command.Parser.Argument;
import com.iCo6.command.exceptions.InvalidUsage;
import com.iCo6.system.Accounts;
import com.iCo6.util.Messaging;
import com.iCo6.util.Template;

public class Purge extends Handler {

    private Accounts Accounts = new Accounts();

    public Purge(iConomy plugin) {
        super(plugin, iConomy.Template);
    }

    @Override
    public boolean perform(CommandSender sender, LinkedHashMap<String, Argument> arguments) throws InvalidUsage {
        if(!hasPermissions(sender, "purge")) {
			template.noPermission(sender);
			return false;
        }

        Accounts.purge();

        String tag = template.color(Template.Node.TAG_MONEY);
        template.set(Template.Node.ACCOUNTS_PURGE);
        Messaging.send(sender, tag + template.parse());

        return false;
    }
}
