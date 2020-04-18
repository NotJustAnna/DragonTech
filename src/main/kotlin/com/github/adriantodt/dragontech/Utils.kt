package com.github.adriantodt.dragontech

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.block.PillarBlock
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

fun identifier(path: String) = Identifier("dragontech", path)

inline fun identifier(path: String, block: Identifier.() -> Unit) = identifier(path).run(block)

fun Identifier.item(item: Item) = apply {
    Registry.register(Registry.ITEM, this, item)
}

fun Identifier.item(block: Block) = apply {
    Registry.register(Registry.ITEM, this, BlockItem(block, Item.Settings().group(DRAGONTECH_GROUP)))
}

fun Identifier.block(block: Block) = apply {
    Registry.register(Registry.BLOCK, this, block)
}

fun Identifier.blockEntityType(bet: BlockEntityType<*>) = apply {
    Registry.register(Registry.BLOCK_ENTITY_TYPE, this, bet)
}

fun itemSettings(): Item.Settings = Item.Settings().group(DRAGONTECH_GROUP)