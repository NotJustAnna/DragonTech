package cf.brforgers.mods.DragonScalesEX.common.virus.utils;

import net.minecraft.util.registry.RegistrySimple;

import java.util.Random;

public class GridSystem<T> {
    public final int size;
    /**
     * Create a Chunk-Like Grid System.
     * <br>To be used with DraconyVirus
     *
     * @param size
     * @return
     */

    private final RegistrySimple<Integer, RegistrySimple<Integer, T>> chunkMap = new RegistrySimple<Integer, RegistrySimple<Integer, T>>();

    public GridSystem(int blockSize) {
        size = blockSize;
    }

    public void put(int x, int z, T value) {
        RegistrySimple<Integer, T> innerMap = chunkMap.getObject(x);
        if (innerMap == null) {
            innerMap = new RegistrySimple<Integer, T>();
            chunkMap.putObject(x, innerMap);
        }

        innerMap.putObject(z, value);
    }

    public T get(int x, int z) {
        RegistrySimple<Integer, T> innerMap = chunkMap.getObject(x);
        if (innerMap == null) {
            return null;
        }

        return innerMap.getObject(z);
    }

    public T getRandom(Random random) {
        RegistrySimple<Integer, T> innerMap = chunkMap.getRandomObject(random);
        if (innerMap == null) {
            return null;
        }

        return innerMap.getRandomObject(random);
    }
}
