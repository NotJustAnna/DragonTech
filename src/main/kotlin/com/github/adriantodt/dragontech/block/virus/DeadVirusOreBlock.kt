package com.github.adriantodt.dragontech.block.virus

import com.github.adriantodt.dragontech.dracony.DraconyVirus
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class DeadVirusOreBlock(settings: Settings, private val expFun: (Random) -> Int = { 0 }) : Block(settings) {
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

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult): ActionResult {
        return DraconyVirus.useOnBlock(state, world, pos, player, hand)
    }
}