package com.github.adriantodt.dragontech.block.virus

import net.minecraft.block.BlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

/*
if Blocks.COAL_ORE -> { MathHelper.nextInt(random, 0, 2) }
if BLocks.REDSTONE_ORE -> { 1 + world.random.nextInt(5) }
if Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE -> { MathHelper.nextInt(random, 3, 7) }
if Blocks.LAPIS_ORE, Blocks.NETHER_QUARTZ_ORE -> { MathHelper.nextInt(random, 2, 5) }
if Blocks.NETHER_GOLD_ORE -> { MathHelper.nextInt(random, 0, 1) }
default -> 0
*/

class VirusOreBlock(settings: Settings, private val expFun: (Random) -> Int = { 0 }) : VirusBlock(settings) {
    override fun onStacksDropped(state: BlockState, world: World, pos: BlockPos, stack: ItemStack) {
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            val i = expFun(world.random)
            if (i > 0) {
                dropExperience(world, pos, i)
            }
        }
    }
}

