package com.github.thelonedevil.AgeChecker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BypassCommand implements CommandExecutor {

	private App plugin;

	public BypassCommand(App plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bypass") && sender instanceof Player) {
			String perm = "AgeChecker.bypass";
			if (sender.hasPermission(perm) || sender.isOp()) {
				String target = args[0];
				String set1 = args[1];
				if (set1 == "true"){
					App.allowed.put(target, true);
					sender.sendMessage(target+" has been allowed to bypass the age gate");
					if (App.succsess == "default") {
					} else if (App.succsess != "default") {
						CommandSender sender1 = plugin.getServer().getConsoleSender();
						plugin.getServer().dispatchCommand(sender1, App.succsess);
					}
				} else if (set1 == "false"){
					App.allowed.put(target, false);
					sender.sendMessage(target+" has to pass the age gate");
					if (App.failure == "default") {
					} else if (App.failure != "default") {
						CommandSender sender1 = plugin.getServer().getConsoleSender();
						plugin.getServer().dispatchCommand(sender1, App.failure);

					}
				} else if (set1 != "true" || set1 != "false"){
					sender.sendMessage("Invalid option! please use either true or false");
				}

			}
		}
		return true;
	}

}
