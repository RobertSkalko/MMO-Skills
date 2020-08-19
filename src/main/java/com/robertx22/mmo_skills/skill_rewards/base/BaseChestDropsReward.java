package com.robertx22.mmo_skills.skill_rewards.base;

import com.robertx22.mmo_skills.config.SkillRewardConfig;
import com.robertx22.mmo_skills.mixin_methods.LootContextDummy;
import com.robertx22.mmo_skills.mixin_methods.LootType;
import com.robertx22.mmo_skills.storage.PlayerSkills;
import com.robertx22.mmo_skills.storage.PlayerSkillsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

public abstract class BaseChestDropsReward extends SkillReward {

    public abstract void modifyStack(PlayerSkillsComponent comp, ItemStack stack);

    @Override
    public void onLootTableGenItems(PlayerSkillsComponent comp, PlayerSkills skill, SkillRewardConfig config, LootTable table, LootContext context, List<ItemStack> list) {

        if (!config.isUnlocked(skill, comp)) {
            return;
        }
        LootContextDummy dummy = (LootContextDummy) context;
        if (dummy.MMOSKILLSgetLootType() != LootType.CHEST) {
            return;
        }
        if (config.rewardsExp) {
            comp.addExp(skill, skill.getConfig().extraExpPerAction);
        }

        float chance = config.getValue(skill, comp);

        boolean added = false;

        for (ItemStack x : list) {
            if (x.getCount() > 0 && !x.isEmpty()) {
                if (chance >= RandomUtils.nextInt(0, 101)) {
                    modifyStack(comp, x);
                    added = true;
                }
            }
        }

        if (added) {
            comp.player.sendMessage(getChatMessage(), false);

        }

    }

}

