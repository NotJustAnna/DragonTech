package com.github.adriantodt.dragontech.block

import com.github.adriantodt.dragontech.NetheriteCauldronBlockEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.function.BooleanBiFunction
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World

class NetheriteCauldronBlock(settings: Settings) : Block(settings), BlockEntityProvider {
    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape {
        return OUTLINE_SHAPE
    }

    companion object {
        private var OUTLINE_SHAPE: VoxelShape = VoxelShapes.combineAndSimplify(
            VoxelShapes.fullCube(),
            VoxelShapes.union(
                createCuboidShape(0.0, 0.0, 4.0, 16.0, 3.0, 12.0),
                createCuboidShape(4.0, 0.0, 0.0, 12.0, 3.0, 16.0),
                createCuboidShape(2.0, 0.0, 2.0, 14.0, 3.0, 14.0),
                createCuboidShape(2.0, 4.0, 2.0, 14.0, 16.0, 14.0)
            ),
            BooleanBiFunction.ONLY_FIRST
        )
    }

    override fun createBlockEntity(world: BlockView) = NetheriteCauldronBlockEntity()

    override fun onBlockAction(state: BlockState, world: World, pos: BlockPos, channel: Int, value: Int): Boolean {
        val blockEntity = world.getBlockEntity(pos)
        return blockEntity?.onBlockAction(channel, value) ?: false
    }
}