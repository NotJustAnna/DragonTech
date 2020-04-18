package com.github.adriantodt.dragontech.dracony

import com.github.adriantodt.dragontech.DRACONY_LEAVES
import com.github.adriantodt.dragontech.DRACONY_LOG
import net.minecraft.block.LeavesBlock
import net.minecraft.block.PillarBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Direction.*
import net.minecraft.world.World
import java.util.*
import kotlin.math.max

data class DraconyTree(
    val trunkLength: Int,
    val northBranchLength: Int,
    val northLeavesSeed: Int,
    val southBranchLength: Int,
    val southLeavesSeed: Int,
    val eastBranchLength: Int,
    val eastLeavesSeed: Int,
    val westBranchLength: Int,
    val westLeavesSeed: Int
) {
    fun generateOnWorld(world: World, root: BlockPos) {
        for (len in 0..trunkLength) {
            world.setBlockState(root.up(len), DRACONY_LOG.defaultState)
        }

        val branchRoot = root.up(trunkLength)
        val leavesState = DRACONY_LEAVES.defaultState
            .with(LeavesBlock.PERSISTENT, true)
            .with(LeavesBlock.DISTANCE, 1)
        world.setBlockState(branchRoot.north(), leavesState)
        world.setBlockState(branchRoot.south(), leavesState)
        world.setBlockState(branchRoot.east(), leavesState)
        world.setBlockState(branchRoot.west(), leavesState)
        world.setBlockState(branchRoot.up(), leavesState)

        generateBranch(world, branchRoot, NORTH, northBranchLength, northLeavesSeed)
        generateBranch(world, branchRoot, SOUTH, southBranchLength, southLeavesSeed)
        generateBranch(world, branchRoot, EAST, eastBranchLength, eastLeavesSeed)
        generateBranch(world, branchRoot, WEST, westBranchLength, westLeavesSeed)
    }

    fun generateBranch(world: World, branchRoot: BlockPos, direction: Direction, length: Int, leavesSeed: Int) {
        val logState = DRACONY_LOG.defaultState.with(PillarBlock.AXIS, direction.axis)
        val leavesState = DRACONY_LEAVES.defaultState
            .with(LeavesBlock.PERSISTENT, true)
            .with(LeavesBlock.DISTANCE, 1)
        val optLeavesState = DRACONY_LEAVES.defaultState
            .with(LeavesBlock.PERSISTENT, true)
            .with(LeavesBlock.DISTANCE, 2)

        for (pos in (1..length).map { branchRoot.up(it).offset(direction, it) }) {
            world.setBlockState(pos, logState)
            world.setBlockState(pos.north(), leavesState)
            world.setBlockState(pos.south(), leavesState)
            world.setBlockState(pos.east(), leavesState)
            world.setBlockState(pos.west(), leavesState)
            world.setBlockState(pos.up(), leavesState)
        }
        val leavesRoot = branchRoot.up(length).offset(direction, length)
        var bit = 0
        for (x in -1..1) {
            for (z in -1..1) {
                if ((x != 0 || z != 0) && leavesSeed and (1 shl bit++) != 0) {
                    world.setBlockState(leavesRoot.add(x, if (x == 0 || z == 0) 1 else 0, z), optLeavesState)
                }
            }
        }
    }

    companion object {
        fun generateOnWorld(world: World, root: BlockPos) {
            generate(world.random).generateOnWorld(world, root)
        }

        fun generate(random: Random): DraconyTree {
            // length of the trunk and each branch
            val baseLen = 2 + random.nextInt(3)
            val tLen = 1 + baseLen
            val nLen = baseLen / 2 + random.nextInt(10) % (baseLen)
            val sLen = baseLen / 2 + random.nextInt(10) % (baseLen)
            val eLen = baseLen / 2 + random.nextInt(10) % (baseLen)
            val wLen = baseLen / 2 + random.nextInt(10) % (baseLen)

            // the optional blocks for each tree
            val nSeed = random.nextInt(512)
            val sSeed = random.nextInt(512)
            val eSeed = random.nextInt(512)
            val wSeed = random.nextInt(512)

            val branches = listOf(listOf(NORTH, SOUTH), listOf(EAST, WEST))
                .shuffled(random)
                .flatMap { it.shuffled(random) }
                .take(2 + max(0, tLen - 4) + random.nextInt(2))

            return DraconyTree(
                tLen,
                if (NORTH in branches) nLen else 0, nSeed,
                if (SOUTH in branches) sLen else 0, sSeed,
                if (EAST in branches) eLen else 0, eSeed,
                if (WEST in branches) wLen else 0, wSeed
            )
        }
    }
}