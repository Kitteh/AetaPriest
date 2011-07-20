package com.Kitteh.AetaPriest.Spells;

import org.bukkit.World;

public class PlayerDeathLocation {
	private int x;
	private int y;
	private int z;
	private boolean isRevived;
	private World world;
	
	/**
	 * Create new PlayerDeathLocation
	 * @param w World
	 * @param x 
	 * @param y
	 * @param z
	 */
	public PlayerDeathLocation(World w, int x, int y, int z){
		this.world = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}
	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}
	/**
	 * @param isRevived the isRevived to set
	 */
	public void setRevived(boolean isRevived) {
		this.isRevived = isRevived;
	}
	/**
	 * @return the isRevived
	 */
	public boolean isRevived() {
		return isRevived;
	}

	/**
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}

}
