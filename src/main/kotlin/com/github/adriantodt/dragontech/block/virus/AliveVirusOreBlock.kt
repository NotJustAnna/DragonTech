package com.github.adriantodt.dragontech.block.virus

import com.github.adriantodt.dragontech.dracony.DraconyVirus
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class AliveVirusOreBlock(settings: Settings, private val expFun: (Random) -> Int = { 0 }) : Block(settings) {
    override fun onStacksDropped(state: BlockState, world: World, pos: BlockPos, stack: ItemStack) {
        if (EnchantmentHelper.getLevel(
                Enchantments.SILK_TOUCH,
                stack
            ) == 0) {
            val i = expFun(world.random)
            if (i > 0) {
                dropExperience(world, pos, i)
            }
        }
    }

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        DraconyVirus.virusTick(world, pos)
    }

    override fun rainTick(world: World, pos: BlockPos) {
        DraconyVirus.virusRainTick(world, pos)
    }
}