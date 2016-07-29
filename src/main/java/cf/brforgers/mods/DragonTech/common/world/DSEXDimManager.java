package cf.brforgers.mods.DragonTech.common.world;

import cf.brforgers.mods.DragonTech.common.virus.EnumVirusState;
import cf.brforgers.mods.DragonTech.common.virus.utils.GridSystem;

import java.util.HashMap;
import java.util.Map;

public class DSEXDimManager {
    //BITMASKS
    public static final int BIT_ORES = 1;  //0001
    public static final int BIT_VIRUS = 2; //0010
    public static final int BIT_ISLES = 4; //0100
    public static Map<Integer, DSEXDimManager> dims = new HashMap<Integer, DSEXDimManager>() {{
        dims.put(0, new DSEXDimManager(true, true, true)); //Overworld
        dims.put(1, new DSEXDimManager(true, false, false)); //Nether
        dims.put(-1, new DSEXDimManager(true, true, true)); //The End
        dims.put(-100, new DSEXDimManager(true, false, false)); //Deep Dark
        dims.put(6, new DSEXDimManager(true, false, false)); //Aroma MinerWorld
    }};
    public GridSystem<EnumVirusState> virusGrid = new GridSystem<EnumVirusState>(8);
    private int bits = 0;

    public DSEXDimManager(boolean genOres, boolean genVirus, boolean genIsles) {
        setOreGen(genOres);
        setVirus(genVirus);
        setIsles(genIsles);
    }

    private static boolean getFromMask(int integer, int bitmask) { //Helper function to Bitwise operations
        return (integer & bitmask) == bitmask;
    }

    private static int setFromMask(int integer, int bitmask, boolean value) { //Helper function to Bitwise operations
        if (value) return integer | bitmask;
        return integer ^ (integer & bitmask);
    }

    public void setOreGen(boolean value) {
        bits = setFromMask(bits, BIT_ORES, value);
    }

    public boolean isOreGenEnabled() {
        return getFromMask(bits, BIT_ORES);
    }

    public void setVirus(boolean value) {
        bits = setFromMask(bits, BIT_VIRUS, value);
    }

    public boolean isVirusEnabled() {
        return getFromMask(bits, BIT_VIRUS);
    }

    public void setIsles(boolean value) {
        bits = setFromMask(bits, BIT_ISLES, value);
    }

    public boolean isIslesEnabled() {
        return getFromMask(bits, BIT_ISLES);
    }
}
