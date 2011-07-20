package com.Kitteh.AetaPriest.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipException;

import org.bukkit.util.config.Configuration;
import com.Kitteh.AetaPriest.AetaPriestPlugin;



public class ConfigLoader {
	
	File file;
	Configuration config;
	
	//Define The main plugin class
	SpellConfig aetaConfig;
	
	/**
	 * Define a new Config file using the directory path specified
	 * @param(Directory name,File name, plugin object)
	 */
	public ConfigLoader(String dir, String filename){
		File folder = new File(dir);
		
		if (!folder.exists()){
			folder.mkdir();
		}
		file = new File(dir + filename);
		if (file.exists()){
			if(!(checkVersion(file)))
				createFile(filename);
		}
		else{
			//Need to create the file
			createFile(filename);
		}
		
		config = new Configuration(file);
		if (config!=null){
			config.load();
		}else{
			AetaPriestPlugin.printConsole("Error while reading configuration file, delete it and retry");
		}
		
		/*wjava
		 * Load a new aeta config
		 */
		newConfig();
	}
	
	/*
	 * Create a new file 
	 */
	private void createFile(String n){
		try {
			file.createNewFile();
		} catch (IOException e) {
			AetaPriestPlugin.printConsole(" Error creating new config file, Are you sure the correct file permissions are in place?");
		}
		//Begin copying the file
		try{
			InputStream is = AetaPriestPlugin.class.getClassLoader().getResourceAsStream(n);
			
			if (is!=null){
				
				int len;
				FileOutputStream fos = new FileOutputStream(file);
				byte buf[] = new byte[1024];
				while ( (len=is.read(buf)) > 0 ){
					fos.write(buf,0,len);
				}
				fos.close();
				is.close();
				AetaPriestPlugin.printConsole("new config file written");
				
			}else{
				AetaPriestPlugin.printConsole("Failed to read config file from jar");
			}
		} catch (ZipException e){
			AetaPriestPlugin.printConsole("Error while creating new config file - try restarting your server! and report this error: " + e);
		} catch (IOException e){
			AetaPriestPlugin.printConsole(" Error creating new config file, Are you sure the correct file permissions are in place?" + e);
		} catch (Exception e ){
			AetaPriestPlugin.printConsole("Error while writing config file " + e);
		}
	}
	

	/*
	 * Reload the configuration File
	 */
	public void reloadConfig(){
		config.load();
	}
	
	private boolean checkVersion(File f){
		Configuration c = new Configuration(f);
		c.load();
		double version = c.getDouble("Info.Version", 0);
		if (version < 0.5){
			AetaPriestPlugin.printConsole("Config file is incompatible with current version - Rewriting config file");
			f.delete();
			return false;
		}
		return true;
	}
	
	/*
	 * AetaPriest Specific Configuration
	 */
	
	private void newConfig(){
		aetaConfig = new SpellConfig(config); 
		aetaConfig.loadConfig();
	}
}
