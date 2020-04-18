package com.github.adriantodt.dragontech.mixin;

import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GameRules.IntRule.class)
public interface IntRuleMixin {
    @Invoker("create")
    static GameRules.RuleType<GameRules.IntRule> create(int initialValue) {
        throw new UnsupportedOperationException();
    }
}
