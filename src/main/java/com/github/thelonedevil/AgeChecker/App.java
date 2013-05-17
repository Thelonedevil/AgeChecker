package com.github.thelonedevil.AgeChecker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

public class App extends JavaPlugin {
	static int age = 13;
	File file = new File("plugins/AgeChecker/config.yml");
	static String error = "An error has happened...... incoming stack trace....";
	static HashMap<String, Date> DOB = new HashMap<String, Date>();
	static HashMap<String, Boolean> allowed = new HashMap<String, Boolean>();
	static boolean lock;
	static String success;
	static String failure;
	static boolean birthdaysage;
	static String dateformat;

	public void onEnable() {
		getCommand("DOB").setExecutor(new DOBCommand(this));
		getCommand("bypass").setExecutor(new BypassCommand(this));
		getCommand("birthday").setExecutor(new List(this));
		getCommand("birthdays").setExecutor(new Birthdays(this));
		getServer().getPluginManager().registerEvents(new EListener(this), this);
		getLogger().info("Listeners have been enabled");
		dobyaml();
		dobyamls();
		allowedyaml();
		allowedyamls();
		config();
		task();
		getLogger().info("Plugin enabled");
	}

	public void onDisable() {
		dobyamls();
		allowedyamls();
		getLogger().info("Plugin disabled");
	}

	void errorLogger() {
		getLogger().info(error);
	}

	@SuppressWarnings("unchecked")
	void dobyaml() {
		File dobyaml1 = new File("plugins/AgeChecker/DOB.yml");
		if (dobyaml1.exists()) {
			try {
				InputStream input = new FileInputStream(dobyaml1);
				Yaml yaml = new Yaml();
				DOB = (HashMap<String, Date>) yaml.load(input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	void allowedyaml() {
		File allowedyaml1 = new File("plugins/AgeChecker/allowed.yml");
		if (allowedyaml1.exists()) {
			try {
				InputStream input = new FileInputStream(allowedyaml1);
				Yaml yaml = new Yaml();
				allowed = (HashMap<String, Boolean>) yaml.load(input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	void dobyamls() {
		File dobyaml1 = new File("plugins/AgeChecker/DOB.yml");
		Yaml yaml = new Yaml();
		String output = yaml.dump(DOB);
		try {
			getDataFolder().mkdirs();
			dobyaml1.createNewFile();
			PrintStream out = new PrintStream("plugins/AgeChecker/DOB.yml");
			out.print(output);
			out.close();
		} catch (FileNotFoundException e) {
			errorLogger();
			e.printStackTrace();
		} catch (IOException e) {
			errorLogger();
			e.printStackTrace();
		}
	}

	void allowedyamls() {
		File allowedyaml1 = new File("plugins/AgeChecker/allowed.yml");
		Yaml yaml = new Yaml();
		String output = yaml.dump(allowed);
		try {
			getDataFolder().mkdirs();
			allowedyaml1.createNewFile();
			PrintStream out = new PrintStream("plugins/AgeChecker/allowed.yml");
			out.print(output);
			out.close();
		} catch (FileNotFoundException e) {
			errorLogger();
			e.printStackTrace();
		} catch (IOException e) {
			errorLogger();
			e.printStackTrace();
		}
	}

	void config() {
		this.saveDefaultConfig();
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		age = (Integer) config.get("age");
		lock = (Boolean) config.get("lock");
		success = (String) config.get("succsess");
		failure = (String) config .get("failure");
		birthdaysage = (Boolean) config.get("birthdaysage");
		dateformat = (String) config.get("DateFormat");
	}

	public void task() {
		for (int count = 0; count > 10000; count++) {
			if (count == 9999) {
				dobyaml();
				dobyamls();
				allowedyaml();
				allowedyamls();
				task();
			} else
				continue;
		}
	}
}
