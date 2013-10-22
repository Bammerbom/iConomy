package com.iCo6.handlers;

import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.iCo6.iConomy;
import com.iCo6.command.Handler;
import com.iCo6.command.Parser.Argument;
import com.iCo6.command.exceptions.InvalidUsage;
import com.iCo6.system.Account;
import com.iCo6.system.Accounts;
import com.iCo6.util.Messaging;
import com.iCo6.util.Template;

public class Top extends Handler {
	private Accounts Accounts = new Accounts();

	public Top(iConomy plugin) {
		super(plugin, iConomy.Template);
	}

	@Override
	public boolean perform(CommandSender sender, LinkedHashMap<String, Argument> arguments) throws InvalidUsage {
		if(!hasPermissions(sender, "top")) {
			template.noPermission(sender);
			return false;
		}

		Double amount;

        try {
        	amount = arguments.get("pocet").getDoubleValue();
        } catch(NumberFormatException e) {
            throw new InvalidUsage("Invalid <white>amount<rose>, must be double.");
        }

        if(Double.isInfinite(amount) || Double.isNaN(amount))
            throw new InvalidUsage("Invalid <white>amount<rose>, must be double.");
		
		template.set(Template.Node.TOP_OPENING);
		Messaging.send(sender, template.parse());

		template.set(Template.Node.TOP_ITEM);
		
        String pocet = arguments.get("pocet").getStringValue();
        
		List<Account> top;
		if(pocet.equals("0")){
			top = Accounts.getTopAccounts(5);
		} else {		
			top = Accounts.getTopAccounts(Integer.valueOf(pocet));
		}
			
		for (int i = 0; i < top.size(); i++) {
			Account account = top.get(i);
			template.add("i", i + 1);
			template.add("name", account.name);
			template.add("amount", account.getHoldings().toString());
			Messaging.send(sender, template.parse());
		}
		
		return false;
	}
}
