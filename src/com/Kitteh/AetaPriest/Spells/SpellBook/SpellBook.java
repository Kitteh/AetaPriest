package com.Kitteh.AetaPriest.Spells.SpellBook;

import com.Kitteh.AetaPriest.Config.SpellConfig;
import com.Kitteh.AetaPriest.Spells.Spell;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpellBook
{
  private List<Spell> spells;
  private Player player;
  private int spellIndex;
  private SpellBookType bookType;

  public SpellBook(Player o)
  {
    this.spells = new ArrayList<Spell>();
    this.player = o;
    this.spellIndex = 0;
  }

public Spell getCurrentSpell() {
    Spell currentSpell = (Spell)this.spells.get(this.spellIndex);
    return currentSpell;
  }

  public Player getPlayer() {
    return this.player;
  }

  public void addSpell(Spell s) {
    this.spells.add(s);
  }
  public void nextSpell() {
    if (this.spellIndex + 1 < this.spells.size())
      this.spellIndex += 1;
    else {
      this.spellIndex = 0;
    }
    SpellConfig.sendPlayer(this.player, ChatColor.WHITE + "You have selected " + ChatColor.DARK_AQUA + getCurrentSpell().getName());
  }
  public boolean isEmpty() {
    return this.spells.isEmpty();
  }

  public void setBookType(SpellBookType bookType)
  {
    this.bookType = bookType;
  }

  public SpellBookType getBookType()
  {
    return this.bookType;
  }
}