package com.github.adriantodt.dragontech

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.fluid.Fluid
import net.minecraft.nbt.CompoundTag
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class NetheriteCauldronBlockEntity : BlockEntity(NETHERITE_CAULDRON_BTE) {
    private var fluid: Fluid? = null
    private var amount = 0

    override fun toTag(tag: CompoundTag): CompoundTag {
        return super.toTag(tag).apply {
            putString("fluid", Registry.FLUID.getId(fluid).toString())
            putInt("amount", amount)
        }
    }

    override fun fromTag(state: BlockState, tag: CompoundTag) {
        super.fromTag(state, tag)
        fluid = Registry.FLUID.getOrEmpty(Identifier(tag.getString("fluid"))).orElse(null)
        amount = if (fluid == null) 0 else tag.getInt("amount").coerceIn(0..8)
    }
}