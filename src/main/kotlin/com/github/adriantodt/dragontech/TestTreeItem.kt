package com.github.adriantodt.dragontech

import com.github.adriantodt.dragontech.dracony.DraconyTree
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.util.math.Direction

class TestTreeItem(settings: Settings) : Item(settings) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (!context.world.isClient && context.side == Direction.UP) {
            val posRoot = context.blockPos.up()
            if (context.world.isAir(posRoot)) {
                DraconyTree.generateOnWorld(
                    context.world,
                    posRoot
                )
            }
        }
        return super.useOnBlock(context)
    }
}