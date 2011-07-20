package com.Kitteh.AetaPriest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

import com.Kitteh.AetaPriest.Config.SpellConfig;
import com.Kitteh.AetaPriest.Spells.CastType;
import com.Kitteh.AetaPriest.Spells.Spell;
import com.Kitteh.AetaPriest.Spells.SpellBook.SpellBook;
import com.Kitteh.AetaPriest.Spells.SpellBook.SpellBookHandler;

public class AetaPriestPlayerListener extends PlayerListener {

	/**
	 * Trigger when a player interacts with another player via right click
	 */

	@Override
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if (isPriestSpellEvent(e)) {
			if (e.getRightClicked() instanceof Player){
				/**
				 * Player Target Player Priest Event
				 */
				Player target = (Player) e.getRightClicked();
				Player caster = e.getPlayer();
				SpellBook spellBook = SpellBookHandler.getSpellBook(caster);
				if (spellBook==null)
					return;
				Spell currentSpell = spellBook.getCurrentSpell();
				//Cast the current spell
				if (!(currentSpell.onCooldown())){
					if (currentSpell.isCastableOn(CastType.PLAYER)){
						currentSpell.castSpellonTarget(target, caster);
					}
					return;
				}else {
					//Player is on cooldown
					SpellConfig.sendPlayer(caster, ChatColor.AQUA + "you are on cooldown for another " 
							+  ChatColor.DARK_AQUA + currentSpell.getCooldown().getTimer() + ChatColor.AQUA + " seconds");
				}
			}
		}
		return;
	}

	@Override
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (isPriestSpellEvent(e)) {
			Player caster = e.getPlayer();
			if (caster==null)return;
			SpellBook spellBook = SpellBookHandler.getSpellBook(caster);
			if (spellBook != null) {
				// Change to next Spell
				if (e.getAction() == Action.LEFT_CLICK_AIR)
					spellBook.nextSpell();
				else if (e.getAction() == Action.RIGHT_CLICK_AIR 
						&& spellBook.getCurrentSpell().isCastableOn(CastType.SELF)){
					if (!(spellBook.getCurrentSpell().onCooldown()))
						spellBook.getCurrentSpell().castSpellonSelf(caster);
					else{
						SpellConfig.sendPlayer(caster, ChatColor.AQUA + "you are on cooldown for another " 
								+  ChatColor.DARK_AQUA + spellBook.getCurrentSpell().getCooldown().getTimer() + ChatColor.AQUA + " seconds");
					}
				}
					
			}
		}
	}
	
	private boolean isPriestSpellEvent(Event event) {
		if (event instanceof PlayerInteractEvent) {
			PlayerInteractEvent e = (PlayerInteractEvent) event;
			if (e.getItem() != null)
				if (e.getItem().getType() == Material.BOOK) {
					return true;
				}

		}
		if (event instanceof PlayerInteractEntityEvent) {
			PlayerInteractEntityEvent e = (PlayerInteractEntityEvent) event;
			if (e.getPlayer().getItemInHand() != null)
				if (e.getPlayer().getItemInHand().getType() == Material.BOOK)
					return true;
		}
		return false;
	}
}
