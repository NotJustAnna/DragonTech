package cf.brforgers.mods.DragonTech.common.world;

import cf.brforgers.core.lib.GridSystem;
import cf.brforgers.mods.DragonTech.common.virus.EnumVirusState;

import java.util.HashMap;
import java.util.Map;

public class DTDimManager {
    public static final DTDimManager DEFAULT = new DTDimManager(false, false, false);
    public static Map<Integer, DTDimManager> dims = new HashMap<Integer, DTDimManager>() {{
        dims.put(0, new DTDimManager(true, true, true)); //Overworld
        dims.put(1, new DTDimManager(true, false, false)); //Nether
        dims.put(-1, new DTDimManager(true, true, true)); //The End
        dims.put(-100, new DTDimManager(true, false, false)); //Deep Dark
        dims.put(6, new DTDimManager(true, false, false)); //Aroma MinerWorld
    }};
    public GridSystem<EnumVirusState> virusGrid = new GridSystem<EnumVirusState>(8);
    private boolean generateOres, generateVirus, generateIslands;

    public DTDimManager(boolean generateOres, boolean generateVirus, boolean generateIslands) {
        this.generateOres = generateOres;
        this.generateVirus = generateVirus;
        this.generateIslands = generateIslands;
    }

    public static DTDimManager getDimension(int id) {
        if (dims.containsKey(id)) return dims.get(id);
        return DEFAULT;
    }

    public boolean doGenerateOres() {
        return generateOres;
    }

    public DTDimManager setGenerateOres(boolean generateOres) {
        this.generateOres = generateOres;
        return this;
    }

    public boolean doGenerateVirus() {
        return generateVirus;
    }

    public DTDimManager setGenerateVirus(boolean generateVirus) {
        this.generateVirus = generateVirus;
        return this;
    }

    public boolean doGenerateIslands() {
        return generateIslands;
    }

    public DTDimManager setGenerateIslands(boolean generateIslands) {
        this.generateIslands = generateIslands;
        return this;
    }
}
