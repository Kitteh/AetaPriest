package com.Kitteh.AetaPriest.Spells.Cleric;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.Kitteh.AetaPriest.Buffs.BuffController;
import com.Kitteh.AetaPriest.Buffs.DefenceBuff;
import com.Kitteh.AetaPriest.Config.SpellConfig;
import com.Kitteh.AetaPriest.Spells.CastType;
import com.Kitteh.AetaPriest.Spells.Spell;

public class BuffDefenceOther extends Spell{
	DefenceBuff buff;
	public BuffDefenceOther(){
		name = "Buff Defence";
		castType = CastType.PLAYER;
		buff = new DefenceBuff(duration, amount);
	}
	public void castSpellonTarget(Player target, Player caster) {
		SpellConfig.sendPlayer(caster,ChatColor.DARK_GREEN
				+ "You have cast " + name +  " on " + target.getName() );
		SpellConfig.sendPlayer(target,ChatColor.DARK_GREEN
				+ "You have been buffed by " + caster.getName() );
		SpellConfig.sendPlayer(target,ChatColor.AQUA
				+ "You will now take less damage" );
		/** Buff the player */
		addBuff(target);
		this.cooldown.setCooldown();
	}
	
	private void addBuff(Player p){
		BuffController.addBuff(p,buff);
	}

	@Override
	public void castSpellonLocation(Location loc, Player caster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void castSpellonSelf(Player p) {
		// TODO Auto-generated method stub
		
	}
}
