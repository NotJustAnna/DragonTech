package cf.brforgers.mods.DragonTech.common.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.util.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DTWorldData extends WorldSavedData {
    private static final String DATA_NAME = "DTWorldData";
    private Map<UUID, EnumPlayerState> playerStates = new HashMap<UUID, EnumPlayerState>();

    public DTWorldData() {
        super(DATA_NAME);
    }

    public static DTWorldData get(World world) {
        MapStorage storage = world.getMapStorage();
        DTWorldData instance = (DTWorldData) storage.getOrLoadData(DTWorldData.class, DATA_NAME);

        if (instance == null) {
            instance = new DTWorldData();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

    public static DTWorldData get(EntityPlayer player) {
        return get(player.worldObj);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagList list = nbt.getTagList("dragonplayers", Constants.NBT.TAG_COMPOUND);
        int i = 0;
        NBTBase nbtBase = list.get(0);
        while (nbtBase.getId() != Constants.NBT.TAG_END) {
            try {
                NBTTagCompound compound = (NBTTagCompound) nbtBase;
                playerStates.put(UUID.fromString(compound.getString("uuid")), EnumPlayerState.values()[compound.getInteger("player")]);
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        NBTTagList list = new NBTTagList();
        nbt.setTag("dragonplayers", list);
        for (Map.Entry<UUID, EnumPlayerState> entry : playerStates.entrySet()) {
            if (!entry.getValue().equals(EnumPlayerState.NONE)) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setString("uuid", entry.getKey().toString());
                compound.setInteger("player", entry.getValue().ordinal());
                list.appendTag(compound);
            }
        }
        return nbt;
    }

    public EnumPlayerState getState(EntityPlayer player) {
        return playerStates.get(player.getUniqueID());
    }

    public void setState(EntityPlayer player, EnumPlayerState state) {
        playerStates.put(EntityPlayer.getUUID(player.getGameProfile()), state);
        this.markDirty();
    }
}
