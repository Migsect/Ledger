package net.samongi.Ledger.Attribute.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.samongi.Ledger.Attribute.Modifiers.Modifier;
import net.samongi.Ledger.Attribute.Modifiers.Modifier.Precedence;
import net.samongi.Ledger.Attribute.Type.AttributeType;

public class Attribute implements Serializable
{
  private static final long serialVersionUID = 1935782100617917536L;
  
  // Indicates the type of the Attribute
  private final String type_name;
  private transient final AttributeType type;
  
  // Indicates the owner of the Attribute
  private final UUID player;
  
  // Map of all the modifers for this stat.
  private Modifier base_mod;
  private Map<Integer, Map<Precedence, List<Modifier>>> modifiers = new HashMap<>();
  
  public Attribute(AttributeType type, UUID player)
  {
    this.type = type;
    this.type_name = type.getName();
    this.player = player;
  }
  
  public UUID getPlayerUUID(){return this.player;}
  public Player getPlayer(){return Bukkit.getPlayer(this.player);}
  public AttributeType getAttributeType(){return this.getAttributeType();}
  
  /**Calculates the end value of all the modifiers.
   * @return The end result of all the modifiers.
   */
  public double getValue()
  {
    double value = 0;
    Set<Integer> priorities = modifiers.keySet();
    for(int i : priorities)
    {
      Map<Precedence, List<Modifier>> priority_map = this.getAllModifiers().get(i);
      Set<Precedence> precedences = priority_map.keySet();
      for(Precedence p : precedences)
      {
        List<Modifier> modifiers = priority_map.get(p);
        for(Modifier m : modifiers)value = m.modify(value);
      }
    }
    return value;
  }
  /**Will merge all modifiers from the AttributeType and the
   * attribute. Precedence and priorty will be merged and neither
   * will have 
   * 
   * @return
   */
  private Map<Integer, Map<Precedence, List<Modifier>>> getAllModifiers()
  {
    // the map that contains everything
    Map<Integer, Map<Precedence, List<Modifier>>> new_map = new HashMap<>();
    
    // Adding all the modifiers from this object
    Set<Integer> priorities = modifiers.keySet();
    for(int i : priorities)
    {
      // If the new map doesn't have the key yet
      if(!new_map.containsKey(i)) new_map.put(i, new HashMap<Precedence, List<Modifier>>());
      Map<Precedence, List<Modifier>> new_priority_map = new_map.get(i);
      
      // Time to loop through the prioty map.
      Map<Precedence, List<Modifier>> priority_map = this.modifiers.get(i);
      Set<Precedence> precedences = priority_map.keySet();
      for(Precedence p : precedences)
      {
        // Check if the new priority map has the list for the precedence
        if(!new_priority_map.containsKey(p)) priority_map.put(p, new ArrayList<Modifier>());
        List<Modifier> new_modifiers = priority_map.get(p);
        
        List<Modifier> modifiers = priority_map.get(p);
        new_modifiers.addAll(modifiers);
        
      }
    }
    // Adding all the modifiers from this objects' attribute type
    Set<Integer> type_priorities = this.type.getModifiers().keySet();
    for(int i : type_priorities)
    {
      // If the new map doesn't have the key yet
      if(!new_map.containsKey(i)) new_map.put(i, new HashMap<Precedence, List<Modifier>>());
      Map<Precedence, List<Modifier>> new_priority_map = new_map.get(i);
      
      // Time to loop through the prioty map.
      Map<Precedence, List<Modifier>> priority_map = this.type.getModifiers().get(i);
      Set<Precedence> precedences = priority_map.keySet();
      for(Precedence p : precedences)
      {
        // Check if the new priority map has the list for the precedence
        if(!new_priority_map.containsKey(p)) priority_map.put(p, new ArrayList<Modifier>());
        List<Modifier> new_modifiers = priority_map.get(p);
        
        List<Modifier> modifiers = priority_map.get(p);
        new_modifiers.addAll(modifiers);
      }
    }
    
    // returning the new map
    return new_map;
  }
  
}
