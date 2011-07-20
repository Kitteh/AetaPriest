package com.Kitteh.AetaPriest.Spells;

import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftSkeleton;
import org.bukkit.craftbukkit.entity.CraftZombie;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.Kitteh.AetaPriest.Config.SpellConfig;

public class HolyAura extends Spell{
	
	public HolyAura(){
		name = "Holy Aura";
		castType = CastType.SELF;
		
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
	public void castSpellonSelf(Player caster) {
		List<Entity> nearbyEntities = caster.getNearbyEntities(10.0d, 5.0d, 10.0d);
		if (nearbyEntities.size() > 0){
			boolean hit = false;
			Iterator<Entity> itr = nearbyEntities.iterator();
			while(itr.hasNext()){
				Entity e = itr.next();
				if (e instanceof CraftZombie ||
						e instanceof CraftSkeleton){
					hit = true;
					e.setFireTicks(10);
				}
			}
			if (hit == true){
				SpellConfig.sendPlayer(caster,ChatColor.WHITE
						+ "You purge the area of evil");
				this.cooldown.setCooldown();
			}else{
				SpellConfig.sendPlayer(caster,ChatColor.GRAY
						+ "There were no evil beings nearby");
			}
		}else{
			SpellConfig.sendPlayer(caster,ChatColor.GRAY
					+ "There were no evil beings nearby");
		}
	}

}
