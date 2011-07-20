package com.Kitteh.AetaPriest.Spells;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.Kitteh.AetaPriest.Config.SpellConfig;

public class Resurrect extends Spell{
	
	public Resurrect(){
		name = "Resurrect";
		castType = CastType.SELF;
		spellType = SpellType.LIGHT;
	}
	@Override
	public void castSpellonLocation(Location loc, Player caster) {
		
		
	}

	@Override
	public void castSpellonTarget(Player target, Player caster) {
		//Cannot be cast on target
		return;
	}
	@Override
	public void castSpellonSelf(Player p) {
		if (ResurrectionController.resurrectNearby(p, distance))
			this.cooldown.setCooldown();
		else
			SpellConfig.sendPlayer(p, ChatColor.GRAY + "There was nobody nearby to resurrect");
	}

}
