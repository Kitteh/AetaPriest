package com.Kitteh.AetaPriest.Spells;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.Kitteh.AetaPriest.AetaPriestPlugin;


public abstract class Spell {
	public Player caster;
	public Cooldown cooldown = new Cooldown(5);
	public String name;
	public CastType castType;
	public int amount = 1;
	public int duration = 600;
	public int distance = 100;
	public SpellType spellType = SpellType.LIGHT;
	public abstract void castSpellonTarget(Player target, Player caster);
	public abstract void castSpellonLocation(Location loc, Player caster);
	public abstract void castSpellonSelf(Player p);
	public boolean isCastableOn(CastType c){
		if ( castType == c)
			return true;
		return false;
	}
	public Cooldown getCooldown(){
		return cooldown;
	}
	public boolean onCooldown(){
		return cooldown.onCooldown();
	}
	public boolean hasPermission(Player p){
		if (AetaPriestPlugin.permissionHandler.has(p, "AetaPriest." + name.replace(" ", "")))
			return true;
		return false;
	}
	public void setCooldown(Cooldown c){
		cooldown = c;
	}
	public void setAmount(int i){
		amount = i;
	}
	public void setDuration(int t){
		duration = t;
	}
	public void setDistance(int d){
		distance = d < 1000 ? d : 1000;
	}
	public String getPermissionNode(){
		return name.replace(" ", "");
	}
	
	public String getName(){
		return name;
	}
}
