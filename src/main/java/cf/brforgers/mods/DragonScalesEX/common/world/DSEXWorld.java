package cf.brforgers.mods.DragonScalesEX.common.world;

import cf.brforgers.mods.DragonScalesEX.common.utils.GridSystem;

public class DSEXWorld {
    public static GridSystem<EnumVirusState> virusGrid = new GridSystem<EnumVirusState>(8);

    public static void initWorld() {
        boolean biome = true, virus = true;

        if (biome) initBiome();
        if (virus) initVirus();
    }

    private static void initBiome() {

    }

    private static void initVirus() {

    }
}
