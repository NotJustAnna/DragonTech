package cf.brforgers.mods.DragonTech;

public class Lib {
	public static final String MODID = "dragontech";
	public static final String MODNAME = "DragonTech";
	public static final String VERSION = "1.0";
	public static final String DEPS = "after:BRCore@[1.0,)"; // + ";required-after:MODID"; for Each other MOD
	//Little Helper, huh?
	public static final String TEXTURE_PATH = MODID + ":";
	//Easter Eggs
	public static final String FANCYNAME = "§5§lDragonTech";

    private Lib() {
    }

    public static class Config {
		public static int DraconyVirus_SpreadingMultiplier = 1;
		public static int DraconyVirus_ChanceMultiplier = 1;
		public static int BatchExecutor_Timeout = 3;
		public static boolean Debug = false;
		public static boolean DraconyVirus_ForceGeneration = false;

        private Config() {
        }
    }
}
