package com.github.thelonedevil.AgeChecker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

public class App extends JavaPlugin {
	static String error = "An error has happened...... incoming stack trace....";
	static HashMap<String, Calendar> DOB = new HashMap<String, Calendar>();
	static HashMap<String, Boolean> allowed = new HashMap<String, Boolean>();
	
    public void onEnable(){
    	getCommand("DOB").setExecutor(new DOBCommand(this));
    	getServer().getPluginManager().registerEvents(new EListener(), this);
    	getLogger().info("Listeners have been enabled");
    	dobyaml();
    	dobyamls();
    	allowedyaml();
    	allowedyamls();
    	getLogger().info("Plugin enabled"); 
    }
    public void onDisable(){
    	dobyamls();
    	allowedyamls();
    	getLogger().info("Plugin disabled"); 
    }
    
	void errorLogger() {
		getLogger().info(error);
	}
	
	@SuppressWarnings("unchecked")
	void dobyaml(){
		File dobyaml1 = new File("plugins/AgeChecker/DOB.yml");
		if(dobyaml1.exists()){
			try {
				InputStream input = new FileInputStream(dobyaml1);
				Yaml yaml = new Yaml();
			    DOB = (HashMap<String, Calendar>) yaml.load(input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
		}
	}
	@SuppressWarnings("unchecked")
	void allowedyaml(){
		File allowedyaml1 = new File("plugins/AgeChecker/allowed.yml");
		if (allowedyaml1.exists()){
			try {
				InputStream input = new FileInputStream(allowedyaml1);
				Yaml yaml = new Yaml();
			    allowed = (HashMap<String, Boolean>) yaml.load(input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	void dobyamls(){
		File dobyaml1 = new File("plugins/AgeChecker/DOB.yml");
		Yaml yaml = new Yaml();
		String output = yaml.dump(DOB);
		try {
			getDataFolder().mkdirs();
			dobyaml1.createNewFile();
			PrintStream out =  new PrintStream("plugins/AgeChecker/DOB.yml");
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
	void allowedyamls(){
		File allowedyaml1 = new File("plugins/AgeChecker/allowed.yml");
		Yaml yaml = new Yaml();
		String output = yaml.dump(allowed);
		try {
			getDataFolder().mkdirs();
			allowedyaml1.createNewFile();
			PrintStream out =  new PrintStream("plugins/AgeChecker/allowed.yml");
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


    
  /*  void DOBdat() {
		File DOBdat = new File("plugins/AgeChecker/DOB.dat");
		if (DOBdat.exists()) {
			try {
				DOB = (HashMap<String, Calendar>) SLAPI.load("plugins/AgeChecker/DOB.dat");
			} catch (Exception e) {
				errorLogger();
				e.printStackTrace();
			}
			getLogger().info("Login data has been Loaded from disk");
			try {
				SLAPI.save(DOB, "plugins/AgeChecker/DOB.dat");
			} catch (Exception e) {
				errorLogger();
				e.printStackTrace();
			}
		} else if (!DOBdat.exists()) {
			getLogger().info("DOB.dat is missing... creating...");
			try {
				getDataFolder().mkdirs();
				DOBdat.createNewFile();
			} catch (IOException e) {
				errorLogger();
				e.printStackTrace();
			}
			getLogger().info("DOB.dat has been created");
		}
	}*/
    /*void alloweddat() {
		File alloweddat = new File("plugins/AgeChecker/allowed.dat");
		if (alloweddat.exists()) {
			try {
				allowed = (HashMap<String, Boolean>) SLAPI.load("plugins/AgeChecker/allowed.dat");
			} catch (Exception e) {
				errorLogger();
				e.printStackTrace();
			}
			getLogger().info("Login data has been Loaded from disk");
			try {
				SLAPI.save(allowed, "plugins/AgeChecker/allowed.dat");
			} catch (Exception e) {
				errorLogger();
				e.printStackTrace();
			}
		} else if (!alloweddat.exists()) {
			getLogger().info("allowed.dat is missing... creating...");
			try {
				getDataFolder().mkdirs();
				alloweddat.createNewFile();
			} catch (IOException e) {
				errorLogger();
				e.printStackTrace();
			}
			getLogger().info("allowed.dat has been created");
		}
	}*/
}
