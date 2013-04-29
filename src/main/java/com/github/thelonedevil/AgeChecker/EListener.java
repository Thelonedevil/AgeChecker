package com.github.thelonedevil.AgeChecker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EListener implements Listener {
	static String name;

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		name = player.getName();

		if (App.DOB.get(name) == null) {
			player.sendMessage("Please verify your age by using the command /DOB <Your date of birth goes here in the format DD/MM/YYYY>");
		} else if (App.DOB.get(name) != null) {
			if (App.allowed.get(name) == true){
				
			}else if (App.allowed.get(name) == false){
				player.kickPlayer("You are not old enough to play on this server");
			}
		}
	}

}
