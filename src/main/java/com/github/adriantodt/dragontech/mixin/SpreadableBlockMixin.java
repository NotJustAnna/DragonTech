package com.github.adriantodt.dragontech.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SpreadableBlock.class)
public interface SpreadableBlockMixin {
    @Invoker("canSurvive")
    static boolean canSurvive(BlockState state, WorldView worldView, BlockPos pos) {
        throw new UnsupportedOperationException();
    }
}