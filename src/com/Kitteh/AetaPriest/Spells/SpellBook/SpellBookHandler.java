package com.Kitteh.AetaPriest.Spells.SpellBook;

import com.Kitteh.AetaPriest.Config.SpellConfig;
import com.Kitteh.AetaPriest.Spells.Spell;
import com.Kitteh.AetaPriest.Spells.SpellBook.SpellBook;
import com.nijiko.permissions.PermissionHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.bukkit.entity.Player;

public class SpellBookHandler
{
  public static PermissionHandler permissionHandler;
  private static HashMap<Player, SpellBook> spellBooks;

  public SpellBookHandler(PermissionHandler pH)
  {
    spellBooks = new HashMap<Player, SpellBook>();
    permissionHandler = pH;
  }

  public void clearBooks() {
    spellBooks.clear();
  }

  public static SpellBook getSpellBook(Player o)
  {
    return spellBooks.get(o);
  }

  public static void addSpellBook(Player p) {
    if ((permissionHandler != null) && 
      (!spellBooks.containsKey(p))) {
      SpellBook spellBook = getPlayerSpells(p);
      if ((spellBook != null) && 
        (!spellBook.isEmpty()))
        spellBooks.put(p, spellBook);
    }
  }

  public static boolean contains(Player p)
  {
    return spellBooks.containsKey(p);
  }
  private static SpellBook getPlayerSpells(Player p) {
    List<Spell> spellList = SpellConfig.getSpellList();
    Iterator<Spell> itr = spellList.iterator();
    SpellBook spellBook = new SpellBook(p);
    while (itr.hasNext()) {
      Spell s = (Spell)itr.next();
      if (s.hasPermission(p)) {
        spellBook.addSpell(s);
      }
    }

    return spellBook;
  }

  public static void removeSpellBook(Player p)
  {
    spellBooks.remove(p);
  }
}