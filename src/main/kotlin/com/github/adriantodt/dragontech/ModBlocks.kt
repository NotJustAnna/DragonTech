package com.github.adriantodt.dragontech

import com.github.adriantodt.dragontech.block.NetheriteCauldronBlock
import com.github.adriantodt.dragontech.block.virus.*
import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.math.MathHelper
import net.minecraft.util.registry.Registry
import java.util.function.Supplier


val INERT_STONE = Block(
    FabricBlockSettings.of(Material.STONE)
        .strength(0.8F, 3.0F)
        .build()
)

val DRACONY_GRASS_BLOCK = VirusBlock(
    FabricBlockSettings.of(Material.ORGANIC)
        .ticksRandomly()
        .strength(0.6F, 0.6F)
        .sounds(BlockSoundGroup.GRASS)
        .build()
)

val DRACONY_DIRT = VirusBlock(
    FabricBlockSettings.of(Material.EARTH)
        .ticksRandomly()
        .strength(0.5F, 0.5F)
        .sounds(BlockSoundGroup.GRAVEL)
        .build()
)

val DRACONY_STONE = VirusBlock(
    FabricBlockSettings.of(Material.STONE)
        .ticksRandomly()
        .strength(1.5F, 6.0F)
        .build()
)

val DRACONY_STONE_STAIRS = VirusStairsBlock(
    DRACONY_STONE.defaultState, FabricBlockSettings.copy(DRACONY_STONE).build()
)

val DRACONY_LOG = VirusPillarBlock(
    FabricBlockSettings.of(Material.WOOD, MaterialColor.PURPLE)
        .ticksRandomly()
        .strength(2.0f, 2.0f)
        .sounds(BlockSoundGroup.WOOD)
        .build()
)

val DRACONY_WOOD = VirusPillarBlock(
    FabricBlockSettings.of(Material.WOOD, MaterialColor.PURPLE)
        .ticksRandomly()
        .strength(2.0f, 2.0f)
        .sounds(BlockSoundGroup.WOOD)
        .build()
)

val DRACONY_PLANKS = VirusBlock(
    FabricBlockSettings.of(Material.WOOD, MaterialColor.SPRUCE)
        .ticksRandomly()
        .strength(2.0f, 3.0f)
        .sounds(BlockSoundGroup.WOOD)
        .build()
)

val DRACONY_PLANKS_STAIRS = VirusStairsBlock(
    DRACONY_PLANKS.defaultState, FabricBlockSettings.copy(DRACONY_PLANKS).build()
)

val DRACONY_LEAVES = VirusLeavesBlock(
    FabricBlockSettings.of(Material.LEAVES)
        .nonOpaque()
        .ticksRandomly()
        .sounds(BlockSoundGroup.GRASS)
        .build()
        .allowsSpawning { _, _, _, _ -> false }
        .suffocates { _, _, _ -> false }
        .blockVision { _, _, _ -> false }
)

val DRACONY_METAL_ORE = VirusOreBlock(
    FabricBlockSettings.of(Material.STONE)
        .ticksRandomly()
        .strength(3.0f, 3.0f)
        .build()
)

val DRACONY_FUEL_ORE = VirusOreBlock(
    FabricBlockSettings.of(Material.STONE)
        .ticksRandomly()
        .strength(3.0f, 3.0f)
        .build()
) { MathHelper.nextInt(it, 0, 2) }

val DRACONY_GEM_ORE = VirusOreBlock(
    FabricBlockSettings.of(Material.STONE)
        .ticksRandomly()
        .strength(3.0f, 3.0f)
        .build()
) { MathHelper.nextInt(it, 3, 7) }

val DRACONY_LAZULI_ORE = DeadVirusOreBlock(
    FabricBlockSettings.of(Material.STONE)
        .strength(3.0f, 3.0f)
        .build()
) { MathHelper.nextInt(it, 2, 5) }

val DRACONY_BLUEPOWDER_ORE = AliveVirusOreBlock(
    FabricBlockSettings.of(Material.STONE)
        .ticksRandomly()
        .lightLevel(9)
        .strength(3.0f, 3.0f)
        .build()
) { MathHelper.nextInt(it, 1, 6) }

val DRACONY_DRACONITE_ORE = VirusOreBlock(
    FabricBlockSettings.of(Material.STONE)
        .ticksRandomly()
        .strength(3.0f, 3.0f)
        .build()
)

val NETHERITE_CAULDRON = NetheriteCauldronBlock(
    FabricBlockSettings.of(Material.METAL, MaterialColor.STONE)
        .strength(4.0F, 4.0F)
        .nonOpaque()
        .build()
)

val DRACONY_FUEL_BLOCK = Block(
    FabricBlockSettings.of(Material.STONE, MaterialColor.BLACK)
        .strength(5.0F, 6.0F)
        .build()
)

val DRACONY_METAL_BLOCK = Block(
    FabricBlockSettings.of(Material.METAL, MaterialColor.IRON)
        .strength(5.0F, 6.0F)
        .sounds(BlockSoundGroup.METAL)
        .build()
)

val DRACONY_GEM_BLOCK = Block(
    FabricBlockSettings.of(Material.METAL, MaterialColor.EMERALD)
        .strength(5.0F, 6.0F)
        .sounds(BlockSoundGroup.METAL)
        .build()
)

val GOLDEN_LAZULI_BLOCK = Block(
    FabricBlockSettings.of(Material.METAL, MaterialColor.LAPIS)
        .strength(3.0f, 3.0f)
        .build()
)

val BLUEPOWDER_BLOCK = Block(
    FabricBlockSettings.of(Material.METAL)
        .strength(5.0F, 6.0F)
        .sounds(BlockSoundGroup.METAL)
        .build()
)

val DRACONITE_BLOCK = Block(
    FabricBlockSettings.of(Material.METAL, MaterialColor.IRON)
        .strength(5.0F, 6.0F)
        .sounds(BlockSoundGroup.METAL)
        .build()
)

val NETHERITE_CAULDRON_BTE: BlockEntityType<NetheriteCauldronBlockEntity> = BlockEntityType.Builder.create(
    Supplier(::NetheriteCauldronBlockEntity), NETHERITE_CAULDRON
).build(null)


fun initBlocks() {
    identifier("netherite_cauldron").block(NETHERITE_CAULDRON).item(NETHERITE_CAULDRON)
        .blockEntityType(NETHERITE_CAULDRON_BTE)
    identifier("inert_stone").block(INERT_STONE).item(INERT_STONE)

    identifier("dracony_fuel_block").block(DRACONY_FUEL_BLOCK).item(DRACONY_FUEL_BLOCK)
    identifier("dracony_metal_block").block(DRACONY_METAL_BLOCK).item(DRACONY_METAL_BLOCK)
    identifier("dracony_gem_block").block(DRACONY_GEM_BLOCK).item(DRACONY_GEM_BLOCK)
    identifier("draconite_block").block(DRACONITE_BLOCK).item(DRACONITE_BLOCK)
    identifier("bluepowder_block").block(BLUEPOWDER_BLOCK).item(BLUEPOWDER_BLOCK)
    identifier("golden_lazuli_block").block(GOLDEN_LAZULI_BLOCK).item(GOLDEN_LAZULI_BLOCK)

    identifier("dracony_grass_block").block(DRACONY_GRASS_BLOCK).item(DRACONY_GRASS_BLOCK)
    identifier("dracony_dirt").block(DRACONY_DIRT).item(DRACONY_DIRT)
    identifier("dracony_stone").block(DRACONY_STONE).item(DRACONY_STONE)
    identifier("dracony_stone_stairs").block(DRACONY_STONE_STAIRS).item(DRACONY_STONE_STAIRS)
    identifier("dracony_log").block(DRACONY_LOG).item(DRACONY_LOG)
    identifier("dracony_wood").block(DRACONY_WOOD).item(DRACONY_WOOD)
    identifier("dracony_leaves").block(DRACONY_LEAVES).item(DRACONY_LEAVES)
    identifier("dracony_planks").block(DRACONY_PLANKS).item(DRACONY_PLANKS)
    identifier("dracony_planks_planks").block(DRACONY_PLANKS_STAIRS).item(DRACONY_PLANKS_STAIRS)
    identifier("dracony_fuel_ore").block(DRACONY_FUEL_ORE).item(DRACONY_FUEL_ORE)
    identifier("dracony_metal_ore").block(DRACONY_METAL_ORE).item(DRACONY_METAL_ORE)
    identifier("dracony_gem_ore").block(DRACONY_GEM_ORE).item(DRACONY_GEM_ORE)
    identifier("dracony_lazuli_ore").block(DRACONY_LAZULI_ORE).item(DRACONY_LAZULI_ORE)
    identifier("dracony_bluepowder_ore").block(DRACONY_BLUEPOWDER_ORE).item(DRACONY_BLUEPOWDER_ORE)
    identifier("dracony_draconite_ore").block(DRACONY_DRACONITE_ORE).item(DRACONY_DRACONITE_ORE)
}