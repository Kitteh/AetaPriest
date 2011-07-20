package com.Kitteh.AetaPriest.Spells;

public class Cooldown {
	private long lastCast;
	private int cooldownTime;
	/**
	 * Defines a new cooldown
	 * @param c - timer in seconds
	 */
	
	public Cooldown(int c){
		cooldownTime = c*1000;
	}
	
	public boolean onCooldown(){
		if (System.currentTimeMillis() < lastCast + cooldownTime)
			return true;
		return false;
	}
	/**
	 * Put the ability on cooldown on a timer
	 * @param c Cooldown time in seconds 
	 */
	public void setCooldown(){
		lastCast = System.currentTimeMillis();
	}
	public int getTimer(){
		return (int) ((lastCast + cooldownTime) - System.currentTimeMillis())/1000;
	}
}
