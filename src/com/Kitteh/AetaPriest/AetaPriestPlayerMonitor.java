package com.Kitteh.AetaPriest;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.Kitteh.AetaPriest.Spells.ResurrectionController;
import com.Kitteh.AetaPriest.Spells.SpellBook.SpellBookHandler;

public class AetaPriestPlayerMonitor extends PlayerListener{
	
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if (p!=null){
			SpellBookHandler.addSpellBook(p);
		}
	}
	
	public void onPlayerQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if (p!=null){
			SpellBookHandler.removeSpellBook(p);
		}
	}
	
	public void onPlayerRespawn(PlayerRespawnEvent e){
		if (ResurrectionController.getPlayerDeathLocation(e.getPlayer())!=null)
			if (ResurrectionController.getPlayerDeathLocation(e.getPlayer()).isRevived()){
			e.setRespawnLocation(
					new Location(
							ResurrectionController.getPlayerDeathLocation(e.getPlayer()).getWorld(), 
							ResurrectionController.getPlayerDeathLocation(e.getPlayer()).getX(), 
							ResurrectionController.getPlayerDeathLocation(e.getPlayer()).getY() + 1, 
							ResurrectionController.getPlayerDeathLocation(e.getPlayer()).getZ()));
			}
		ResurrectionController.remove(e.getPlayer());
	}
}
