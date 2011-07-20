package com.Kitteh.AetaPriest;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

import com.Kitteh.AetaPriest.Buffs.BuffController;
import com.Kitteh.AetaPriest.Buffs.BuffType;
import com.Kitteh.AetaPriest.Spells.PlayerDeathLocation;
import com.Kitteh.AetaPriest.Spells.ResurrectionController;

/**
 * Handle events for all Entity related events
 * @author Kitteh
 */
public class AetaPriestEntityListener extends EntityListener {

    /*
     * Call via Entity Damage event 
     */
    public void onEntityDamage(EntityDamageEvent e){
    	if (e instanceof EntityDamageByEntityEvent){
    		EntityDamageByEntityEvent edee = (EntityDamageByEntityEvent) e;
    		// Handle Attack buffs on player
    		if (edee.getDamager() instanceof Player){
    			Player p = (Player) edee.getDamager();
    			if (p!=null)
    				if(BuffController.isbuffed(p)){
    					if (BuffController.getBuff(p).getBuffType() == BuffType.ATTACK){
    						e.setDamage(e.getDamage() + BuffController.getBuff(p).getAmount());
    					}
    				}
    		}
    	}
		// Handle Defence buffs on player
		if (e.getEntity() instanceof Player){
			if (BuffController.isbuffed((Player) e.getEntity()))
			if (BuffController.getBuff((Player) e.getEntity()).getBuffType() == BuffType.DEFENCE){
				e.setDamage(
						e.getDamage() - BuffController.getBuff((Player) e.getEntity()).getAmount());
			}
		}
    }
    
    /**
     * Add the player's death location to the Resurrection Handler
     */
    
    public void onEntityDeath(EntityDeathEvent e){
    	if (e.getEntity() instanceof Player){
    		ResurrectionController.addDeathLocation(
    				(Player) e.getEntity(), 
    				new PlayerDeathLocation(
    						      e.getEntity().getWorld(),
    						(int) e.getEntity().getLocation().getX(),
    						(int) e.getEntity().getLocation().getY(),
    						(int) e.getEntity().getLocation().getZ()
    					)
    				);
    	}
    }
}

