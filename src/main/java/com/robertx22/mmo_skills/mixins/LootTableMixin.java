package com.robertx22.mmo_skills.mixins;

import com.robertx22.mmo_skills.mixin_methods.LootContextDummy;
import com.robertx22.mmo_skills.mixin_methods.LootTableMethods;
import com.robertx22.mmo_skills.mixin_methods.LootType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LootTable.class)
public class LootTableMixin {

    @ModifyVariable(method = "generateLoot(Lnet/minecraft/loot/context/LootContext;)Ljava/util/List;", at = @At("RETURN"), name = "list")
    public List<ItemStack> lootHook(List<ItemStack> list, LootContext context) {
        LootTable table = (LootTable) (Object) this;

        LootTableMethods.hook(table, context, list);
        return list;
    }

    @Inject(method = "supplyInventory", at = @At("HEAD"))
    public void supplyChest(Inventory inventory, LootContext context, CallbackInfo ci) {
        LootTable table = (LootTable) (Object) this;
        LootContextDummy dummy = (LootContextDummy) context;
        dummy.MMOSKILLSSetloottype(LootType.CHEST);
    }

}



