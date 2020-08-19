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

public class ExtraChestItemDropsReward extends BaseChestDropsReward {

    @Override
    public void modifyStack(PlayerSkillsComponent comp, ItemStack stack) {
        if (stack.getMaxCount() > 1) {
            stack.setCount(stack.getCount() + 1);
        }
    }

    @Override
    public MutableText getChatMessage() {
        return new LiteralText("Extra Loot!").formatted(Formatting.RED);
    }

    @Override
    public String getId() {
        return "extra_chest_loot_drops";
    }

    public List<MutableText> getTooltip(SkillRewardConfig config, int level) {
        List<MutableText> list = new ArrayList<>();
        float val = config.getValue(level);
        list.add(new LiteralText("Extra Chest Loot chance: " + val + "%").formatted(Formatting.RED));
        return list;
    }
}
