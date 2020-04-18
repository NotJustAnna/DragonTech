package com.github.adriantodt.dragontech

import com.github.adriantodt.dragontech.dracony.DraconyVirus.onMutation
import com.github.adriantodt.dragontech.dracony.DraconyVirus.onShadow
import com.github.adriantodt.dragontech.dracony.DraconyVirus.onSunlight
import net.minecraft.block.Blocks.*
import net.minecraft.block.LeavesBlock
import net.minecraft.block.PillarBlock
import net.minecraft.tag.BlockTags

fun initVirus() {
    // World
    //onMutationSpecial(GRASS_BLOCK) { world, blockPos -> convert(world, blockPos.up()) }
    onMutation(GRASS_BLOCK, PODZOL) { DRACONY_GRASS_BLOCK.defaultState }
    onMutation(DIRT, COARSE_DIRT) { DRACONY_DIRT.defaultState }
    onMutation(
        STONE,
        COBBLESTONE,
        MOSSY_COBBLESTONE,
        INFESTED_COBBLESTONE,
        INFESTED_STONE,
        GRANITE,
        ANDESITE,
        DIORITE
    ) { DRACONY_STONE.defaultState }
    onMutation(SNOW) { AIR.defaultState }

    onSunlight(DRACONY_DIRT) { DRACONY_GRASS_BLOCK.defaultState }
    onShadow(DRACONY_GRASS_BLOCK) { DRACONY_DIRT.defaultState }

    //TODO SAND, RED SAND, GRAVEL

    // Trees
    onMutation(BlockTags.LOGS) {
        var state = DRACONY_LOG.defaultState
        if (PillarBlock.AXIS in it) {
            state = state.with(PillarBlock.AXIS, it[PillarBlock.AXIS])
        }
        state
    }
    onMutation(BlockTags.LEAVES) { DRACONY_LEAVES.defaultState }
    onMutation(BlockTags.PLANKS) {
        var state = DRACONY_LOG.defaultState
        if (LeavesBlock.PERSISTENT in it) {
            state = state.with(LeavesBlock.PERSISTENT, it[LeavesBlock.PERSISTENT])
        }
        if (LeavesBlock.DISTANCE in it) {
            state = state.with(LeavesBlock.DISTANCE, it[LeavesBlock.DISTANCE])
        }
        state
    }

    //TODO SAPLINGS

    // Ores
    onMutation(COAL_ORE) { DRACONY_FUEL_ORE.defaultState }
    onMutation(COAL_BLOCK) { DRACONY_FUEL_BLOCK.defaultState }
    onMutation(IRON_ORE) { DRACONY_METAL_ORE.defaultState }
    onMutation(IRON_BLOCK) { DRACONY_METAL_BLOCK.defaultState }
    onMutation(GOLD_ORE) { DRACONY_DRACONITE_ORE.defaultState }
    onMutation(GOLD_BLOCK) { DRACONITE_BLOCK.defaultState }
    onMutation(REDSTONE_ORE) { DRACONY_BLUEPOWDER_ORE.defaultState }
    onMutation(REDSTONE_BLOCK) { BLUEPOWDER_BLOCK.defaultState }
    onMutation(LAPIS_ORE) { DRACONY_LAZULI_ORE.defaultState }
    onMutation(LAPIS_BLOCK) { GOLDEN_LAZULI_BLOCK.defaultState }
    onMutation(DIAMOND_ORE) { DRACONY_GEM_ORE.defaultState }
    onMutation(DIAMOND_BLOCK) { DRACONY_GEM_BLOCK.defaultState }
    onMutation(EMERALD_ORE) { DRACONY_GEM_ORE.defaultState }
    onMutation(EMERALD_BLOCK) { DRACONY_GEM_BLOCK.defaultState }
}