package com.robertx22.mmo_skills.mixin_methods;

import com.robertx22.mmo_skills.config.SkillConfig;
import com.robertx22.mmo_skills.main.Components;
import com.robertx22.mmo_skills.skill_rewards.base.SkillReward;
import com.robertx22.mmo_skills.storage.PlayerSkills;
import com.robertx22.mmo_skills.storage.PlayerSkillsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;

import java.util.List;

public class LootTableMethods {

    public static void hook(LootTable table, LootContext context, List<ItemStack> list) {

        if (!(context.get(LootContextParameters.THIS_ENTITY) instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity player = (PlayerEntity) context.get(LootContextParameters.THIS_ENTITY);

        PlayerSkillsComponent comp = Components.INSTANCE.SKILLS.get(player);

        for (PlayerSkills skill : PlayerSkills.values()) {

            SkillConfig config = skill.getConfig();

            config.getRewardConfigs()
                .forEach(rewardConfig -> {
                    SkillReward reward = rewardConfig.getReward();
                    reward.onLootTableGenItems(comp, skill, rewardConfig, table, context, list);
                });

        }
    }
}
