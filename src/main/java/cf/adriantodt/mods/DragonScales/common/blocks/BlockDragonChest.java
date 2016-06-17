package cf.adriantodt.mods.DragonScales.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDragonChest extends BlockContainer
{
	public BlockDragonChest()
	{
		super(Material.wood);
	}
	
	@Override
	public TileEntity createNewTileEntity(World ignored1, int ignored2) {
		return null;
	}
}