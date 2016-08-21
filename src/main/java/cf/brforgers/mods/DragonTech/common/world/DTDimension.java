package cf.brforgers.mods.DragonTech.common.world;

import cf.brforgers.core.lib.GridSystem;
import cf.brforgers.mods.DragonTech.common.virus.EnumVirusState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.Map;

public class DTDimension {
    private static final Map<Integer, DTDimension> DIMENSIONS = new HashMap<Integer, DTDimension>() {{
        put(0, //Overworld
                new DTDimension(true, true, true)
                        .setDefaultBiomeWeight(5)
                        .setBiomeWeight(Biomes.EXTREME_HILLS, 8)
                        .setBiomeWeight(Biomes.EXTREME_HILLS_EDGE, 6)
                        .setBiomeWeight(Biomes.EXTREME_HILLS_WITH_TREES, 8)
                        .setBiomeWeight(Biomes.MUTATED_EXTREME_HILLS, 10)
                        .setBiomeWeight(Biomes.MUTATED_EXTREME_HILLS_WITH_TREES, 12)
        );

        put(1, //Nether
                new DTDimension(true, false, false)
                        .setDefaultBiomeWeight(4)
        );

        put(-1, //The End
                new DTDimension(true, true, true)
                        .setDefaultBiomeWeight(8)
        );
        put(-100, //Deep Dark
                new DTDimension(true, false, false)
                        .setDefaultBiomeWeight(8)
        );
        put(6, //Aroma MinerWorld
                new DTDimension(true, false, false)
                        .setDefaultBiomeWeight(6)
        );
    }};
    public GridSystem<EnumVirusState> virusGrid = new GridSystem<EnumVirusState>(8);
    private Map<Biome, Integer> biomeWeight = new HashMap<Biome, Integer>();
    private int defaultWeight = 1;
    private boolean generateOres = false, generateVirus = false, generateIslands = false;

    public DTDimension() {
    }

    public DTDimension(boolean generateOres, boolean generateVirus, boolean generateIslands) {
        this.generateOres = generateOres;
        this.generateVirus = generateVirus;
        this.generateIslands = generateIslands;
    }

    public static void register(int dimension, DTDimension info) {
        DIMENSIONS.put(dimension, info);
    }

    public static DTDimension get(int dimension) {
        if (!DIMENSIONS.containsKey(dimension)) DIMENSIONS.put(dimension, new DTDimension());
        return DIMENSIONS.get(dimension);
    }

    public DTDimension setBiomeWeight(Biome biome, int weight) {
        biomeWeight.put(biome, weight);
        return this;
    }

    public DTDimension setDefaultBiomeWeight(int weight) {
        defaultWeight = weight;
        return this;
    }

    public int getWeight(Biome biome) {
        return biomeWeight.containsKey(biome) ? biomeWeight.get(biome) : defaultWeight;
    }

    public boolean doGenerateOres() {
        return generateOres;
    }

    public DTDimension setGenerateOres(boolean generateOres) {
        this.generateOres = generateOres;
        return this;
    }

    public boolean doGenerateVirus() {
        return generateVirus;
    }

    public DTDimension setGenerateVirus(boolean generateVirus) {
        this.generateVirus = generateVirus;
        return this;
    }

    public boolean doGenerateIslands() {
        return generateIslands;
    }

    public DTDimension setGenerateIslands(boolean generateIslands) {
        this.generateIslands = generateIslands;
        return this;
    }
}
