package com.Kitteh.AetaPriest.Buffs;

public abstract class Buff {
	public int duration;
	public int buffDuration;
	public int amount;
	public BuffType buffType;
	
	public Buff(int t, int a){
		buffDuration = t;
		duration = t;
		amount = a;
	}
	public void progressTimer(){
		duration--;
	}
	
	public int getTimer(){
		return duration;
	}
	public void restartTimer(){
		duration = buffDuration;
	}
	
	public int getAmount(){
		return amount;
	}
	public BuffType getBuffType(){
		return buffType;
	}
}
