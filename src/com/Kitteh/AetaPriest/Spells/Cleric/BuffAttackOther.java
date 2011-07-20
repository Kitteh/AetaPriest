package com.Kitteh.AetaPriest.Spells.Cleric;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.Kitteh.AetaPriest.Buffs.AttackBuff;
import com.Kitteh.AetaPriest.Buffs.Buff;
import com.Kitteh.AetaPriest.Buffs.BuffController;
import com.Kitteh.AetaPriest.Config.SpellConfig;
import com.Kitteh.AetaPriest.Spells.CastType;
import com.Kitteh.AetaPriest.Spells.Cooldown;
import com.Kitteh.AetaPriest.Spells.Spell;

public class BuffAttackOther extends Spell{
	Buff buff;
	public BuffAttackOther(){
		name = "Buff Attack";
		castType = CastType.PLAYER;
		cooldown = new Cooldown(15);
		buff = new AttackBuff(duration,amount);
	}
	@Override
	public void castSpellonLocation(Location loc, Player caster) {
		
	}

	@Override
	public void castSpellonTarget(Player target, Player caster) {
		SpellConfig.sendPlayer(caster,ChatColor.DARK_GREEN
				+ "You have cast " + name +  " on " + target.getName() );
		SpellConfig.sendPlayer(target,ChatColor.DARK_GREEN
				+ "You have been buffed by " + caster.getName() );
		SpellConfig.sendPlayer(target,ChatColor.RED
				+ "You will now deal more damage!" );
		/** Buff the player */
		addBuff(target);
		this.cooldown.setCooldown();
	}
	
	private void addBuff(Player p){
		BuffController.addBuff(p,buff);
	}
	@Override
	public void castSpellonSelf(Player p) {
		// TODO Auto-generated method stub
		
	}

}
