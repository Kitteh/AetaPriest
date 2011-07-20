package com.Kitteh.AetaPriest.Buffs;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.Kitteh.AetaPriest.Config.SpellConfig;

public class BuffController implements Runnable{
	/**
	 * This defines a buff controller runnable which will act as a timer
	 * It will progress each timer for every second it runs
	 */
	private static HashMap<Player,Buff> buffList;
	
	public BuffController(){
		buffList = new HashMap<Player,Buff>();
	}
	
	public static void addBuff(Player p, Buff amount){
		if (!(buffList.containsKey(p)))
			buffList.put(p, amount);
	}
	
	public static void removeBuff(Player p){
		if (buffList.containsKey(p))
			buffList.remove(p);
	}
	
	public static boolean isbuffed(Player p){
		if (buffList.containsKey(p))
			return true;
		else
			return false;
	}
	
	public static Buff getBuff(Player p){
		return buffList.get(p);
	}

	@Override
	public void run() {
		checkBuffs();
	}

	private void checkBuffs() {
		while (true){
			try{
			for (Entry<Player, Buff> entry: buffList.entrySet()){
				Buff buff = entry.getValue();
				if (buff.getTimer() == 0){
					SpellConfig.sendPlayer(entry.getKey(), ChatColor.RED + "Your buff has run out");
					buffList.remove(entry.getKey());
				}
				else{
					buff.progressTimer();
				}
			}
			Thread.sleep(1000);
			} catch (InterruptedException iex){
				buffList.clear();
				Thread.currentThread().interrupt();
			}
		}
	}
	
}
