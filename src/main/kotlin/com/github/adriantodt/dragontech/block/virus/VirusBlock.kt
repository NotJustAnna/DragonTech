package com.github.adriantodt.dragontech.block.virus

import com.github.adriantodt.dragontech.dracony.DraconyVirus
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

open class VirusBlock(settings: Settings) : Block(settings) {
    companion object {
        val ALIVE: BooleanProperty = BooleanProperty.of("alive")
    }

    init {
        defaultState = stateManager.defaultState.with(ALIVE, true)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(ALIVE)
    }

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult): ActionResult {
        return DraconyVirus.useOnBlock(state, world, pos, player, hand)
    }

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        if (state.get(ALIVE)) {
            DraconyVirus.virusTick(world, pos)
        }
    }

    override fun rainTick(world: World, pos: BlockPos) {
        DraconyVirus.virusRainTick(world, pos)
    }
}