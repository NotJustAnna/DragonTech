package cf.brforgers.mods.DragonScalesEX;

public class Lib {
	private Lib() {}
	public static final String MODID = "DragonScalesEX";
	public static final String MODNAME = "Dragon Scales EX Mod";
	public static final String VERSION = "1.3.1";
	public static final String DEPS = "after:BRCore@[1.0,);after:NotEnoughItems"; // + ";required-after:MODID"; for Each other MOD
	
	//Little Helper, huh?
	public static final String TEXTURE_PATH = MODID + ":";
	public static final String UPDATEURL = "https://raw.githubusercontent.com/BRForgers/DragonScalesEXMod/master/latest.txt";
	
	//Easter Eggs
	public static final String FANCYNAME = "§5§lDragon Scales EX";
	
	public static class Config {
		private Config() {}
		public static int DraconyVirus_SpreadingMultiplier = 1;
		public static int DraconyVirus_ChanceMultiplier = 1;
		public static int BatchExecutor_Timeout = 3;
		public static boolean Debug = false;
		public static boolean DraconyVirus_ForceGeneration = false;
	}
}
