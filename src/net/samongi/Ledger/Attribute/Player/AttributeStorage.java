package net.samongi.Ledger.Attribute.Player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.samongi.Ledger.Attribute.Type.AttributeType;

public class AttributeStorage implements Serializable
{
  private static final long serialVersionUID = -9098366497200499258L;
  
  // Indicates the type of string this is storing.
  private final String type_name;
  private transient final AttributeType type;
  
  private Map<UUID, Attribute> player_attributes = new HashMap<>();
  
  public AttributeStorage(AttributeType type)
  {
    this.type = type;
    this.type_name = type.getName();
  }

}
