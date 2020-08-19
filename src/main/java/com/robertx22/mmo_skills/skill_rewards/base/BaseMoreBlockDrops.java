package com.robertx22.mmo_skills.skill_rewards.base;

import com.robertx22.mmo_skills.config.SkillRewardConfig;
import com.robertx22.mmo_skills.mixin_methods.LootContextDummy;
import com.robertx22.mmo_skills.mixin_methods.LootType;
import com.robertx22.mmo_skills.storage.PlayerSkills;
import com.robertx22.mmo_skills.storage.PlayerSkillsComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

public abstract class BaseMoreBlockDrops extends SkillReward {

    public abstract void modifyStack(PlayerSkillsComponent comp, ItemStack stack);

    @Override
    public void onLootTableGenItems(PlayerSkillsComponent comp, PlayerSkills skill, SkillRewardConfig config, LootTable table, LootContext context, List<ItemStack> list) {

        if (!config.isUnlocked(skill, comp)) {
            return;
        }
        LootContextDummy dummy = (LootContextDummy) context;
        if (dummy.MMOSKILLSgetLootType() != LootType.ORE_CROP) {
            return;
        }

        if (context.hasParameter(LootContextParameters.BLOCK_STATE)) {
            BlockState state = context.get(LootContextParameters.BLOCK_STATE);
            Block block = state.getBlock();

            if (list.stream()
                .anyMatch(x -> x.getItem() != block
                    .asItem())) {

                if (skill.getConfig().validBlocksConfig
                    .isValidForSkill(block)) {

                    if (config.rewardsExp) {
                        comp.addExp(skill, skill.getConfig().validBlocksConfig
                            .getExp(block));
                    }

                    float chance = config.getValue(skill, comp);

                    if (chance >= RandomUtils.nextInt(0, 101)) {

                        comp.player.sendMessage(getChatMessage(), false);

                        list.stream()
                            .forEach(x -> modifyStack(comp, x));
                    }
                }
            }
        }
    }
}

