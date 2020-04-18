package com.github.adriantodt.dragontech.dracony

import com.github.adriantodt.dragontech.BLUEPOWDER
import com.github.adriantodt.dragontech.NEUTRAZULI_POWDER
import com.github.adriantodt.dragontech.block.virus.VirusBlock
import com.github.adriantodt.dragontech.mixin.GameRulesMixin
import com.github.adriantodt.dragontech.mixin.IntRuleMixin
import com.github.adriantodt.dragontech.mixin.SpreadableBlockMixin
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.SnowBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.tag.Tag
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import net.minecraft.world.WorldView
import net.minecraft.world.chunk.light.ChunkLightProvider

object DraconyVirus {
    private val draconyVirusSpread = GameRulesMixin.register("draconyVirusSpread", IntRuleMixin.create(4))
    private val onMutation = PredicateContainer()
    private val onSunlight = PredicateContainer()
    private val onShadow = PredicateContainer()

    fun onMutation(vararg params: Block, block: (BlockState) -> BlockState) {
        params.forEach { onMutation.forBlock[it] = block }
    }

    fun onMutation(vararg params: BlockState, block: (BlockState) -> BlockState) {
        params.forEach { onMutation.forBlockState[it] = block }
    }

    fun onMutation(vararg params: Tag<Block>, block: (BlockState) -> BlockState) {
        params.forEach { onMutation.lazyForTag += it to block }
    }

    fun onSunlight(vararg params: Block, block: (BlockState) -> BlockState) {
        params.forEach { onSunlight.forBlock[it] = block }
    }

    fun onSunlight(vararg params: BlockState, block: (BlockState) -> BlockState) {
        params.forEach { onSunlight.forBlockState[it] = block }
    }

    fun onSunlight(vararg params: Tag<Block>, block: (BlockState) -> BlockState) {
        params.forEach { onSunlight.lazyForTag += it to block }
    }

    fun onShadow(vararg params: Block, block: (BlockState) -> BlockState) {
        params.forEach { onShadow.forBlock[it] = block }
    }

    fun onShadow(vararg params: BlockState, block: (BlockState) -> BlockState) {
        params.forEach { onShadow.forBlockState[it] = block }
    }

    fun onShadow(vararg params: Tag<Block>, block: (BlockState) -> BlockState) {
        params.forEach { onShadow.lazyForTag += it to block }
    }

    fun onMutationSpecial(vararg params: Block, block: (World, BlockPos) -> Unit) {
        params.forEach { onMutation.forBlockSpecial[it] = block }
    }

    fun onMutationSpecial(vararg params: BlockState, block: (World, BlockPos) -> Unit) {
        params.forEach { onMutation.forBlockStateSpecial[it] = block }
    }

    fun onMutationSpecial(vararg params: Tag<Block>, block: (World, BlockPos) -> Unit) {
        params.forEach { onMutation.lazyForTagSpecial += it to block }
    }

    fun onSunlightSpecial(vararg params: Block, block: (World, BlockPos) -> Unit) {
        params.forEach { onSunlight.forBlockSpecial[it] = block }
    }

    fun onSunlightSpecial(vararg params: BlockState, block: (World, BlockPos) -> Unit) {
        params.forEach { onSunlight.forBlockStateSpecial[it] = block }
    }

    fun onSunlightSpecial(vararg params: Tag<Block>, block: (World, BlockPos) -> Unit) {
        params.forEach { onSunlight.lazyForTagSpecial += it to block }
    }

    fun onShadowSpecial(vararg params: Block, block: (World, BlockPos) -> Unit) {
        params.forEach { onShadow.forBlockSpecial[it] = block }
    }

    fun onShadowSpecial(vararg params: BlockState, block: (World, BlockPos) -> Unit) {
        params.forEach { onShadow.forBlockStateSpecial[it] = block }
    }

    fun onShadowSpecial(vararg params: Tag<Block>, block: (World, BlockPos) -> Unit) {
        params.forEach { onShadow.lazyForTagSpecial += it to block }
    }

    fun virusTick(world: World, pos: BlockPos) {
        val state = world.getBlockState(pos)

        if (!SpreadableBlockMixin.canSurvive(state, world, pos)) {
            onShadow.testAndApply(world, pos)
        } else if (world.getLightLevel(pos.up()) >= 9) {
            onSunlight.testAndApply(world, pos)
        }

        val spread = world.gameRules.getInt(draconyVirusSpread)
        for (i in 0 until spread) {
            convert(
                world,
                pos.add(
                    world.random.nextInt(3) - 1,
                    world.random.nextInt(5) - 1,
                    world.random.nextInt(3) - 1
                )
            )
        }
    }

    fun virusRainTick(world: World, pos: BlockPos) {
        if (!world.isClient && world.random.nextInt(20) == 1) {
            if (world.getBiome(pos).getTemperature(pos) >= 0.15f) {
                val state = world.getBlockState(pos)
                if (VirusBlock.ALIVE !in state || state.get(VirusBlock.ALIVE)) {
                    virusTick(world, pos)
                }
            }
        }
    }

    fun convert(world: World, pos: BlockPos) {
        onMutation.testAndApply(world, pos)
    }

    fun useOnBlock(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand): ActionResult {
        val itemStack = player.getStackInHand(hand)
        if (!itemStack.isEmpty) {
            val alive = state.get(VirusBlock.ALIVE)
            when (itemStack.item) {
                NEUTRAZULI_POWDER -> {
                    if (!world.isClient && alive) {
                        if (!player.abilities.creativeMode) {
                            if (itemStack.count < 2) {
                                player.setStackInHand(hand, ItemStack.EMPTY)
                            } else {
                                itemStack.count = itemStack.count - 1
                            }
                        }
                        world.setBlockState(pos, state.with(VirusBlock.ALIVE, false))
                        world.playSound(null, pos, SoundEvents.BLOCK_FUNGUS_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f)
                        return ActionResult.SUCCESS
                    }
                    return ActionResult.PASS
                }
                BLUEPOWDER -> {
                    if (!world.isClient && !alive) {
                        if (!player.abilities.creativeMode) {
                            if (itemStack.count < 2) {
                                player.setStackInHand(hand, ItemStack.EMPTY)
                            } else {
                                itemStack.count = itemStack.count - 1
                            }
                        }
                        world.setBlockState(pos, state.with(VirusBlock.ALIVE, true))
                        world.playSound(null, pos, SoundEvents.BLOCK_FUNGUS_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f)
                        return ActionResult.SUCCESS
                    }
                    return ActionResult.PASS
                }
            }
        }
        return ActionResult.PASS
    }

    private fun canSurvive(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val up = pos.up()
        val upState = world.getBlockState(pos)
        return when {
            upState.block == Blocks.SNOW && upState.get(SnowBlock.LAYERS) as Int == 1 -> true
            upState.fluidState.fluid != Fluids.EMPTY -> false
            else -> ChunkLightProvider.getRealisticOpacity(
                world, state, pos, upState, up,
                Direction.UP, upState.getOpacity(world, up)
            ) < world.maxLightLevel
        }
    }

    private class PredicateContainer {
        val forBlockState = LinkedHashMap<BlockState, (BlockState) -> BlockState>()
        val forBlock = LinkedHashMap<Block, (BlockState) -> BlockState>()
        val lazyForTag = ArrayList<Pair<Tag<Block>, (BlockState) -> BlockState>>()
        val forBlockStateSpecial = LinkedHashMap<BlockState, (World, BlockPos) -> Unit>()
        val forBlockSpecial = LinkedHashMap<Block, (World, BlockPos) -> Unit>()
        val lazyForTagSpecial = ArrayList<Pair<Tag<Block>, (World, BlockPos) -> Unit>>()

        fun testAndApply(world: World, pos: BlockPos) {
            val state1 = world.getBlockState(pos)
            solve(lazyForTagSpecial, forBlockSpecial)
            forBlockStateSpecial[state1]?.invoke(world, pos) ?: forBlockSpecial[state1.block]?.invoke(world, pos)

            val state2 = world.getBlockState(pos)
            solve(lazyForTag, forBlock)
            forBlockState[state2]?.applyChange(world, pos) ?: forBlock[state2.block]?.applyChange(world, pos)
        }

        private fun <K, V> solve(from: ArrayList<Pair<Tag<K>, V>>, target: LinkedHashMap<K, V>) {
            if (from.isNotEmpty()) {
                from.forEach { (k, v) -> k.values().forEach { target[it] = v } }
                from.clear()
            }
        }

        private fun ((BlockState) -> BlockState).applyChange(world: World, pos: BlockPos) {
            val old = world.getBlockState(pos)
            val new = this(old)
            if (!old.stateEquals(new)) world.setBlockState(pos, new)
        }

        private fun BlockState.stateEquals(other: BlockState): Boolean {
            return block == other.block && entries == other.entries
        }
    }
}