package com.Kitteh.AetaPriest.Config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;
import org.bukkit.util.config.ConfigurationNode;

import com.Kitteh.AetaPriest.Spells.Cooldown;
import com.Kitteh.AetaPriest.Spells.HolyAura;
import com.Kitteh.AetaPriest.Spells.Resurrect;
import com.Kitteh.AetaPriest.Spells.Spell;
import com.Kitteh.AetaPriest.Spells.SpellType;
import com.Kitteh.AetaPriest.Spells.Cleric.BuffAttackOther;
import com.Kitteh.AetaPriest.Spells.Cleric.BuffDefenceOther;
import com.Kitteh.AetaPriest.Spells.Cleric.HealOther;
import com.Kitteh.AetaPriest.Spells.Cleric.HealSelf;
import com.Kitteh.AetaPriest.Spells.SpellBook.SpellBookHandler;

public class SpellConfig {
	private static List<Spell> spellList;
	Configuration aetaConfig;
	public SpellConfig(Configuration c){
		aetaConfig = c;
		spellList = new ArrayList<Spell>();
	}
	private void setupSpells(){
		//Heal Other Spell
		spellList.add(new HealOther());
		//Heal Self
		spellList.add(new HealSelf());
		//Buff Other Attack Spell
		spellList.add(new BuffAttackOther());
		//Buff Other Defence Spell
		spellList.add(new BuffDefenceOther());
		//Resurrect
		spellList.add(new Resurrect());
		//Holy Aura
		spellList.add(new HolyAura());
	}
	private void loadSpells(){
		ConfigurationNode spells = aetaConfig.getNode("Spells");
		Iterator<Spell> itr = spellList.iterator();
		while (itr.hasNext()){
			Spell s = itr.next();
			s.setAmount(spells.getInt(s.getPermissionNode() + "." + "Amount", 1));
			s.setCooldown(new Cooldown(spells.getInt(s.getPermissionNode() + "." + "Cooldown", 5)));
			s.setDuration(spells.getInt(s.getPermissionNode() + "." + "Duration", 600));
			s.setDistance(spells.getInt(s.getPermissionNode() + "." + "Distance", 100));
		}
		aetaConfig.load();
	}
	
	public void loadConfig(){
		spellList.clear();
		setupSpells();
		loadSpells();
		aetaConfig.load();
	}
	
    public static void sendPlayer(Player p, String s){
    	if (SpellBookHandler.contains(p)){
    		if (SpellBookHandler.getSpellBook(p).getCurrentSpell().spellType == SpellType.DARK){
    			p.sendMessage(ChatColor.BLACK + "[" + ChatColor.DARK_PURPLE + "DarkPriest" + ChatColor.BLACK + "] " + s);
    		}else{
        		p.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "AetaPriest" + ChatColor.WHITE + "] " + s);
        	}
    	}else{
    		p.sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "AetaPriest" + ChatColor.WHITE + "] " + s);
    	}
    }
    public static List<Spell> getSpellList(){
    	return spellList;
    }
    
}
