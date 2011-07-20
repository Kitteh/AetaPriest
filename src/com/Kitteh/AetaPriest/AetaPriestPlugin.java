package com.Kitteh.AetaPriest;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.Kitteh.AetaPriest.Buffs.BuffController;
import com.Kitteh.AetaPriest.Config.SpellConfig;
import com.Kitteh.AetaPriest.Config.ConfigLoader;
import com.Kitteh.AetaPriest.Spells.SpellBook.SpellBookHandler;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

/**
 * aetaHeal for Bukkit
 *
 * @author Kitteh
 */
public class AetaPriestPlugin extends JavaPlugin {
    private final AetaPriestPlayerListener playerListener = new AetaPriestPlayerListener();
    private final AetaPriestEntityListener entityListener = new AetaPriestEntityListener();
    private final AetaPriestPlayerMonitor playerMonitor = new AetaPriestPlayerMonitor();
    
    public static PermissionHandler permissionHandler;
    
    public ConfigLoader configLoader;
    public SpellConfig config;
    public SpellBookHandler spellbookHandler;
    private Thread buffThread;
    public static BuffController buffController;
    
    int cooldownTask;
    public void onEnable() {
        // Register events
        PluginManager pm = getServer().getPluginManager();
        if (setupPermissions()){
        	/* Check the config */
        	configLoader = new ConfigLoader("plugins/AetaPriest/", "config.yml");
        	/* Setup SpellBook */
        	spellbookHandler = new SpellBookHandler(permissionHandler);
        	/* Setup the buffs */
        	setupBuffs();
        	getPlayerList();
        	//The following Event Requires CB747+
	        pm.registerEvent(Type.PLAYER_INTERACT_ENTITY,playerListener , Priority.Normal, this);
	        pm.registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
	        pm.registerEvent(Type.ENTITY_DAMAGE,entityListener , Priority.Normal, this);
	        pm.registerEvent(Type.PLAYER_JOIN, playerMonitor, Priority.Monitor, this);
	        pm.registerEvent(Type.PLAYER_QUIT, playerMonitor, Priority.Monitor, this);
	        pm.registerEvent(Type.ENTITY_DEATH, entityListener, Priority.Normal, this);
	        pm.registerEvent(Type.PLAYER_RESPAWN, playerMonitor, Priority.Monitor, this);
	        
	        PluginDescriptionFile pdfFile = this.getDescription();
	        System.out.println( "[" + pdfFile.getName() + "] version " + pdfFile.getVersion() + " by " + pdfFile.getAuthors() + " is enabled!" );
        }else{
        	printConsole("Permissions not detected, disabling plugin");
        	pm.disablePlugin(this);
        }
    }

	private void setupBuffs() {
		 buffController = new BuffController();
		 buffThread = new Thread(buffController);
		 buffThread.start();
	}


	public void onDisable() {
		printConsole("Shutting down controller");
		//Shut down threads
		buffThread.interrupt();
		this.getServer().getPluginManager().disablePlugin(this);
        printConsole(" disabled!");
    }
    
	public static void printConsole(String m){
		String message = "[" + "AetaPriest" + "] " + m;
		System.out.println(message);
	}
	
	private boolean setupPermissions() {
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

        if (permissionHandler  == null) {
            if (permissionsPlugin != null) {
                permissionHandler = ((Permissions) permissionsPlugin).getHandler();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
	
    private void getPlayerList() {
		Player[] playerList = this.getServer().getOnlinePlayers();
		spellbookHandler.clearBooks();
		for(Player key : playerList){
			SpellBookHandler.addSpellBook(key);
		}
	}
}

