package com.github.adriantodt.dragontech.block.virus

import com.github.adriantodt.dragontech.dracony.DraconyVirus
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.StairsBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class VirusStairsBlock(baseBlockState: BlockState, settings: Settings) : StairsBlock(baseBlockState, settings) {
    init {
        defaultState = stateManager.defaultState.with(VirusBlock.ALIVE, true)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(VirusBlock.ALIVE)
        super.appendProperties(builder)
    }

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult): ActionResult {
        return DraconyVirus.useOnBlock(state, world, pos, player, hand)
    }

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        if (state.get(VirusBlock.ALIVE)) {
            DraconyVirus.virusTick(world, pos)
        }
        super.randomTick(state, world, pos, random)
    }

    override fun rainTick(world: World, pos: BlockPos) {
        DraconyVirus.virusRainTick(world, pos)
    }
}