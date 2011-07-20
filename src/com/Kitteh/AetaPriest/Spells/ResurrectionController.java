package com.Kitteh.AetaPriest.Spells;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.Kitteh.AetaPriest.Config.SpellConfig;

public final class ResurrectionController {
	private static HashMap<Player, PlayerDeathLocation> deathLocations = new HashMap<Player,PlayerDeathLocation>();

	public HashMap<Player,PlayerDeathLocation> getDeathLocations() {
		return deathLocations;
	}
	
	public static void addDeathLocation(Player p, PlayerDeathLocation l){
		deathLocations.put(p,l);
	}
	
	public static PlayerDeathLocation getPlayerDeathLocation(Player p){
		return deathLocations.get(p);
	}
	
	public static void remove(Player p){
		deathLocations.remove(p);
	}
	
	public static boolean resurrectNearby(Player p, int distance){
		Location loc = p.getLocation();
		PlayerDeathLocation a = new PlayerDeathLocation(
				loc.getWorld(), (int) loc.getX(), (int) loc.getY(), (int) loc.getZ());
		for (Entry<Player, PlayerDeathLocation> entry : deathLocations.entrySet()){
			if (getDistance(a, entry.getValue()) < square(distance)){
				entry.getValue().setRevived(true);
				entry.getValue().getWorld().strikeLightningEffect(new Location(
						entry.getValue().getWorld(),entry.getValue().getX(),entry.getValue().getY(), entry.getValue().getZ()));
				SpellConfig.sendPlayer(p, ChatColor.GOLD + "You have resurrected " + entry.getKey().getName());
				SpellConfig.sendPlayer(entry.getKey(), ChatColor.GOLD + "You have been resurrected by " + p.getName());
				return true;
			}
		}
		return false;
	}
	
	private static int getDistance(PlayerDeathLocation a, PlayerDeathLocation b){
		return square(a.getX() - b.getX()) + square(a.getY() - b.getY()) + square(a.getZ() - b.getZ());
	}
	
	private static int square(int a){
		return a*a;
	}
}
