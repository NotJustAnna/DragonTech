package com.github.adriantodt.dragontech

import com.github.adriantodt.dragontech.dracony.DraconyVirus
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack

val DRAGONTECH_GROUP: ItemGroup = FabricItemGroupBuilder.create(identifier("dragon_scale"))
    .icon { ItemStack(DRAGON_SCALE) }
    .build()

fun init() {
    initBlocks()
    initItems()
    initVirus()
    identifier("test").item(TestTreeItem(Item.Settings().group(DRAGONTECH_GROUP)))
}

