package com.Kitteh.AetaPriest.Spells.Cleric;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.Kitteh.AetaPriest.Config.SpellConfig;
import com.Kitteh.AetaPriest.Spells.CastType;
import com.Kitteh.AetaPriest.Spells.Spell;

public class HealSelf extends Spell{
	
	public HealSelf(){
		name = "Heal Self";
		castType = CastType.SELF;
	}
	@Override
	public void castSpellonTarget(Player target, Player caster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void castSpellonLocation(Location loc, Player caster) {
		// TODO Auto-generated method stub
		
	}
	
	public void castSpellonSelf(Player caster){
		SpellConfig.sendPlayer(caster,ChatColor.GREEN
				+ "You heal yourself");
		int targetHealth = caster.getHealth();
		int setHealth;
		setHealth = (targetHealth + amount) <= 20 ? targetHealth + amount : 20;
		caster.setHealth(setHealth);
		this.cooldown.setCooldown();
	}
}
