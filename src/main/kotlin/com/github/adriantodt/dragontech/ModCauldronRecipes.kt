package com.github.adriantodt.dragontech

import net.minecraft.fluid.BaseFluid
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemStack

data class CauldronRecipe(
    val fluidInput: FluidInput?, val itemInput: ItemStack?, val fluidOutput: FluidOutput?, val itemOutput: ItemStack?
) {
    data class FluidInput(val fluid: BaseFluid?, val amount: Int, val consume: Int)
    data class FluidOutput(val fluid: BaseFluid?, val amount: Int, val add: Int)
}

fun initCauldronRecipes() {
}