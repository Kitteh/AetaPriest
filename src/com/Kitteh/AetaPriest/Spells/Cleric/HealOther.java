/**
 * The basic heal spell heals the target to full health with a cooldown of (user defined)
 */

package com.Kitteh.AetaPriest.Spells.Cleric;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.Kitteh.AetaPriest.Config.SpellConfig;
import com.Kitteh.AetaPriest.Spells.CastType;
import com.Kitteh.AetaPriest.Spells.Spell;


public class HealOther extends Spell{
	public HealOther(){
		name = "Heal";
		castType = CastType.PLAYER;
	}
	@Override
	public void castSpellonTarget(Player target, Player caster) {
		SpellConfig.sendPlayer(caster,ChatColor.GREEN
				+ "You have cast " + name +  " on " + target.getName() );
		SpellConfig.sendPlayer(target,ChatColor.GREEN
				+ "You have been healed by " + caster.getName() );
		int setHealth = (target.getHealth() + amount) <= 20 ? target.getHealth() + amount : 20;
		target.setHealth(setHealth);
		this.cooldown.setCooldown();
	}

	@Override
	public void castSpellonLocation(Location loc, Player caster) {
		// TODO add a castable for location
		return;
	}
	@Override
	public void castSpellonSelf(Player p) {
		return;
	}
}
