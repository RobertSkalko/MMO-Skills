package com.robertx22.mmo_skills.skill_rewards.base;

import com.robertx22.mmo_skills.config.SkillRewardConfig;
import com.robertx22.mmo_skills.storage.PlayerSkills;
import com.robertx22.mmo_skills.storage.PlayerSkillsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.text.MutableText;

import java.util.List;

public abstract class SkillReward {
    public abstract MutableText getChatMessage();

    public abstract String getId();

    public abstract List<MutableText> getTooltip(SkillRewardConfig config, int level);

    public void onLootTableGenItems(PlayerSkillsComponent comp, PlayerSkills skill, SkillRewardConfig config, LootTable table, LootContext context, List<ItemStack> list) {

    }

}
