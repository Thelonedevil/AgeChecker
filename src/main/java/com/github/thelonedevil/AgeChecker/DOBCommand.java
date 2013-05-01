package com.github.thelonedevil.AgeChecker;

import java.util.Calendar;
import java.util.Date;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DOBCommand implements CommandExecutor {

	private App plugin;

	public DOBCommand(App plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("DOB") && sender instanceof Player) {
			Player player = (Player) sender;
			String name = player.getName();
			Date now = new Date();
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(now);
			cal1.add(Calendar.YEAR, -App.age);
			String dob1 = args[0];
			char y1 = dob1.charAt(6);
			char y2 = dob1.charAt(7);
			char y3 = dob1.charAt(8);
			char y4 = dob1.charAt(9);
			char m1 = dob1.charAt(3);
			char m2 = dob1.charAt(4);
			char d1 = dob1.charAt(0);
			char d2 = dob1.charAt(1);
			String y5 = Character.toString(y1);
			String y6 = Character.toString(y2);
			String y7 = Character.toString(y3);
			String y8 = Character.toString(y4);
			String m3 = Character.toString(m1);
			String m4 = Character.toString(m2);
			String d3 = Character.toString(d1);
			String d4 = Character.toString(d2);
			String year2 = y5 + y6 + y7 + y8;
			String month2 = m3 + m4;
			String date2 = d3 + d4;
			int year3 = Integer.parseInt(year2);
			int month3 = Integer.parseInt(month2) - 1;
			int date3 = Integer.parseInt(date2);
			cal2.clear();
			cal2.set(year3, month3, date3);
			App.DOB.put(name, cal2);
			int year = cal1.get(Calendar.YEAR);
			int month = cal1.get(Calendar.MONTH);
			int day = cal1.get(Calendar.DATE);
			int year1 = cal2.get(Calendar.YEAR);
			int month1 = cal2.get(Calendar.MONTH);
			int day1 = cal2.get(Calendar.DATE);
			if (App.allowed.get(name) == null) {
				if (year > year1) {
					App.allowed.put(name, true);
				} else {
					if (year < year1) {
						App.allowed.put(name, false);
						player.kickPlayer("You are not old enough to play on this server");
					}
					if (year == year1) {
						if (month > month1) {
							App.allowed.put(name, true);
						} else {
							if (month < month1) {
								App.allowed.put(name, false);
								player.kickPlayer("You are not old enough to play on this server");
							}
							if (month == month1) {
								if (day >= day1) {
									App.allowed.put(name, true);
								} else if (day < day1) {
									App.allowed.put(name, false);
									player.kickPlayer("You are not old enough to play on this server");
								}
							}
						}

					} 

				}

			}else if (App.allowed.get(name) == false) {
				player.kickPlayer("You are not old enough to play on this server");
			} else if (App.allowed.get(name) == true) {
				player.sendMessage("You are old enough to play on this server");
			}
			return true;
		}
		return false;
	}
}
