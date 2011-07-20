package com.Kitteh.AetaPriest.Buffs;

public class AttackBuff extends Buff{

	/**
	 * Create a new AttackBuff 
	 * @param t - Buff Time
	 * @param a - Buff Amount
	 */
	public AttackBuff(int t, int a) {
		super(t, a);
		buffType = BuffType.ATTACK;
	}
	
}
