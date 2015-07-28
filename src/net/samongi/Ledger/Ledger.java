package net.samongi.Ledger;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Ledger extends JavaPlugin
{
  static private Logger logger;
  static private boolean debug;
  static private boolean warning;
  
  static final public void log(String to_log){logger.info(to_log);}
  static final public void debugLog(String to_log){if(debug) logger.info(to_log);}
  static final public void warningLog(String to_log){if(warning) logger.info(to_log);}
  static final public boolean debug(){return debug;}
  static final public boolean warning(){return warning;}

  public void onEnable()
  {
    // Getting the logger for the plugin
    Ledger.logger = this.getLogger();
    
    // config handling.
    File config_file = new File(this.getDataFolder(),"config.yml");
    if(!config_file.exists())
    {
      Ledger.log("Found no config file, copying over defaults...");
      this.getConfig().options().copyDefaults(true);
      this.saveConfig();
    }
    Ledger.debug = this.getConfig().getBoolean("Debug", true);
    Ledger.warning = this.getConfig().getBoolean("Warning", true);
    
    
  }
  public void onDisbale()
  {
    
  }
}
