package net.samongi.Ledger.Attribute.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.samongi.Ledger.Attribute.Modifiers.Modifier;
import net.samongi.Ledger.Attribute.Modifiers.Modifier.Precedence;

public interface AttributeType
{

  /**Returns the name of this AttributeType.
   * This is used with Attributes and Attribute storage for later lookup.
   * 
   * @return The name of the AttributeType
   */
  public String getName();
  
  /**Gets the modifiers for this type
   * 
   * @return The modifiers for this type.
   */
  public Map<Integer, Map<Precedence, List<Modifier>>> getModifiers();
  
}
