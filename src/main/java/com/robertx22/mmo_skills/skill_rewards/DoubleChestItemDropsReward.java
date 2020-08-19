package com.robertx22.mmo_skills.skill_rewards;

import com.robertx22.mmo_skills.config.SkillRewardConfig;
import com.robertx22.mmo_skills.skill_rewards.base.BaseChestDropsReward;
import com.robertx22.mmo_skills.storage.PlayerSkillsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class DoubleChestItemDropsReward extends BaseChestDropsReward {
    @Override
    public void modifyStack(PlayerSkillsComponent comp, ItemStack stack) {
        if (stack.getMaxCount() > 1) {
            stack.setCount(stack.getCount() * 2);
        }

    }

    @Override
    public MutableText getChatMessage() {
        return new LiteralText("Double Loot!").formatted(Formatting.RED);
    }

    @Override
    public String getId() {
        return "double_chest_loot_drops";
    }

    public List<MutableText> getTooltip(SkillRewardConfig config, int level) {
        List<MutableText> list = new ArrayList<>();
        float val = config.getValue(level);
        list.add(new LiteralText("Double Chest Loot chance: " + val + "%").formatted(Formatting.RED));
        return list;
    }
}
