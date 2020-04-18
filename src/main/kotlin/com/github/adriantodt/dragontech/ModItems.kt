package com.github.adriantodt.dragontech

import net.minecraft.item.Item

val DRAGON_SCALE = Item(itemSettings())

val DRACONY_FUEL = Item(itemSettings())

val DRACONY_METAL_INGOT = Item(itemSettings())

val DRACONY_GEM = Item(itemSettings())

val DRACONITE_INGOT = Item(itemSettings())

val BLUEPOWDER = Item(itemSettings())

val GOLDEN_LAZULI = Item(itemSettings())

val NEUTRAZULI_POWDER = Item(itemSettings())

fun initItems() {
    identifier("dragon_scale").item(DRAGON_SCALE)
    identifier("dracony_fuel").item(DRACONY_FUEL)
    identifier("dracony_metal_ingot").item(DRACONY_METAL_INGOT)
    identifier("dracony_gem").item(DRACONY_GEM)
    identifier("draconite_ingot").item(DRACONITE_INGOT)
    identifier("bluepowder").item(BLUEPOWDER)
    identifier("golden_lazuli").item(GOLDEN_LAZULI)
    identifier("neutrazuli_powder").item(NEUTRAZULI_POWDER)
}