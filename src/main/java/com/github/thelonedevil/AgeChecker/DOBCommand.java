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

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("DOB") && sender instanceof Player) {
			Player player = (Player) sender;
			String name = player.getName();
			Date now = new Date();
			Date their = new Date();
			Calendar cal2 = Calendar.getInstance();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(now);
			cal1.add(Calendar.YEAR, -App.age);
			String dob1;
			try {
				dob1 = args[0];
				char y1;
				char y2;
				char y3;
				char y4;
				char m1;
				char m2;
				char d1;
				char d2;
				if (App.dateformat.equalsIgnoreCase("DD/MM/YYYY")) {
					y1 = dob1.charAt(6);
					y2 = dob1.charAt(7);
					y3 = dob1.charAt(8);
					y4 = dob1.charAt(9);
					m1 = dob1.charAt(3);
					m2 = dob1.charAt(4);
					d1 = dob1.charAt(0);
					d2 = dob1.charAt(1);
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
					their.setYear(year3);
					their.setMonth(month3);
					their.setDate(date3);
					cal2.setTime(their);
					App.DOB.put(name, their);
					if (App.allowed.get(name) == null) {
						if (cal1.compareTo(cal2) <= 0) {
							App.allowed.put(name, true);
							if (App.success.equalsIgnoreCase("default")) {
								player.sendMessage("You are old enough to play on this server");
							} else if (!App.success.equalsIgnoreCase("default")) {
								CommandSender sender1 = plugin.getServer().getConsoleSender();
								String cmds = App.success.replace("%target%", name);
								plugin.getServer().dispatchCommand(sender1, cmds);
							}
						} else if (cal1.compareTo(cal2) > 0) {
							App.allowed.put(name, false);
							if (App.failure.equalsIgnoreCase("default")) {
								player.kickPlayer("You are not old enough to play on this server");
							} else if (!App.failure.equalsIgnoreCase("default")) {
								CommandSender sender1 = plugin.getServer().getConsoleSender();
								String cmds = App.failure.replace("%target%", name);
								plugin.getServer().dispatchCommand(sender1, cmds);
							}
						}
					}
				} else if (App.dateformat.equalsIgnoreCase("MM/DD/YYYY")) {
					y1 = dob1.charAt(6);
					y2 = dob1.charAt(7);
					y3 = dob1.charAt(8);
					y4 = dob1.charAt(9);
					m1 = dob1.charAt(0);
					m2 = dob1.charAt(1);
					d1 = dob1.charAt(3);
					d2 = dob1.charAt(4);
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
					their.setYear(year3);
					their.setMonth(month3);
					their.setDate(date3);
					cal2.setTime(their);
					App.DOB.put(name, their);
					if (App.allowed.get(name) == null) {
						if (cal1.compareTo(cal2) <= 0) {
							App.allowed.put(name, true);
							if (App.success.equalsIgnoreCase("default")) {
								player.sendMessage("You are old enough to play on this server");
							} else if (!App.success.equalsIgnoreCase("default")) {
								CommandSender sender1 = plugin.getServer().getConsoleSender();
								String cmds = App.success.replace("%target%", name);
								plugin.getServer().dispatchCommand(sender1, cmds);
							}

						} else if (cal1.compareTo(cal2) > 0) {
							App.allowed.put(name, false);
							if (App.failure.equalsIgnoreCase("default")) {
								player.kickPlayer("You are not old enough to play on this server");
							} else if (!App.failure.equalsIgnoreCase("default")) {
								CommandSender sender1 = plugin.getServer().getConsoleSender();
								String cmds = App.failure.replace("%target%", name);
								plugin.getServer().dispatchCommand(sender1, cmds);
							}
						}
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				plugin.errorLogger();
				e.printStackTrace();
				player.sendMessage("Error entering Date of Birth");

			}
			return true;
		}
		return false;
	}
}
