package cf.brforgers.mods.DragonScalesEX.common.world;

public enum EnumVirusState {
    /**
     * When the Chunk hasn't been flagged (can be null instead). Virus can grow by randomTickness
     */
    NONE(false, true, false, false, false),
    /**
     * An chunk forced to randomly generate Spreading. Each time this variable is loaded from disk (A.k.a Server Reset + Chunk Loading) it decreases from SPREAD5 to SPREAD4 until NONE.
     */
    SPREAD1(true, true, false, true, false), SPREAD2(true, true, false, true, false), SPREAD3(true, true, false, true, false), SPREAD4(true, true, false, true, false), SPREAD5(true, true, false, true, false),
    /**
     * An chunk being machine-protected. If Unspreadable, it stops anything happening at the chunk. If protected, it will start to return the Chunk back to normal
     */
    UNSPREADABLE(false, false, false, false, false), PROTECTED(false, false, true, false, true),
    /**
     * Admin-variant of the states. Are persistant across reset. Machines are disabled in the chunk. Each flag corresponds as a normal flag:
     * <br> * ADMIN_NONE: NONE flag.
     * <br> * ADMIN_SPREAD: SPREAD5 flag, but it won't decreases.
     * <br> * ADMIN_UNSPREADABLE: UNSPREADABLE flag.
     * <br> * ADMIN_PROTECTED: PROTECTED flag.
     */
    ADMIN_NONE(true, true, false, false, false), ADMIN_SPREAD(true, true, false, true, false), ADMIN_UNSPREADABLE(true, false, false, false, false), ADMIN_PROTECTED(true, false, true, false, true);

    private boolean persistsWorldReloading, naturalVirusSpread, randomTickUnspread, randomTickSpread;

    EnumVirusState(boolean persists, boolean naturalVirusSpread, boolean naturalVirusUnspread, boolean randomTickSpread, boolean randomTickUnspread) {
        persistsWorldReloading = persists;
    }

    public boolean getPersistance() {
        return persistsWorldReloading;
    }
}
