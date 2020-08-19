package com.robertx22.mmo_skills.mixins;

import com.robertx22.mmo_skills.mixin_methods.LootContextDummy;
import com.robertx22.mmo_skills.mixin_methods.LootType;
import net.minecraft.loot.context.LootContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LootContext.class)
public class LootContextMixin implements LootContextDummy {
    @Unique
    public LootType MMOLootType = LootType.ORE_CROP;  // todo this might be buggy??

    @Override
    public LootType MMOSKILLSgetLootType() {
        return MMOLootType;
    }

    @Override
    public void MMOSKILLSSetloottype(LootType bool) {
        MMOLootType = bool;

    }
}
