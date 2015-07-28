package net.samongi.Ledger.Attribute.Modifiers;

import java.io.Serializable;

/**A modifier to an attribute will adjust the attribute prior to it's final value being calculated.
 * Attribute can be calculated based on dynamic situations, allowing certain factors such as armor
 * and health to have an effect on the attribute.
 * 
 * Modifiers are required to be Serializable because they need to be saved with player attributes.
 */
public interface Modifier extends Serializable
{
  /**Returns the priority of the modifers.
   * The higher priorty a modifer has, the later it will modify the statistic
   * 
   * For example, suppose that a "Base" modifier is set to 0, this means that it should return
   * The initial value to modify regardless of the before set amount. Modifiers determine an
   * attribute's value.  Modifiers can also be added to an attribute globally or individually.
   * AttributeTypes handle most of the global situations and Attributes handle their own 
   * unique modifiers.  
   * 
   * It should be noted that 0 is done first.  Anything lower than that is assumed to be a 
   * "wrap around" case in that if something is set as "-1" it will be done last of all the modifiers
   * This allows modifiers to guarentee that they will applied last if they so wish to.
   * 
   * It should also be known that modifiers of the same priority have a secondary priority where
   * Modifier type is taken into account.  The order of precedence for these different types are
   * as follows:
   * 0. Complex (Anything that uses a function more complex than what is prior stated such as sine)
   * 1. Exponential (Anything that uses roots or exponential modify)
   * 2. Multiplication (Anything that uses multiplication or division to modify)
   * 3. Addition (Anything that uses addition or subraction to modify)
   * 4. Setting (Anything that ignores the prior input)
   * 
   * @return
   */
  public int getPriority();
  
  /**This will modify the input based on the modifier.
   * 
   * @param input The input to modify
   * @return The modified input.
   */
  public double modify(double input);
  
  public enum Precedence
  {
    COMPLEX (0),
    EXPONENTIAL (1),
    MULTIPLICATION (2),
    ADDITION (3),
    SETTING (4);
    
    private final int order;
    
    Precedence(int order)
    {
      this.order = order;
    }
    public int getOrder(){return this.order;}
  };
  
  /**Returns the Precedence of the modifier.
   * 
   * @return The precedence enumeration of the modifier.
   */
  public Precedence getPrecedence();
  
  
}
