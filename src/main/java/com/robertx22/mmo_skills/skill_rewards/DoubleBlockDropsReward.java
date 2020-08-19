package com.robertx22.mmo_skills.skill_rewards;

import com.robertx22.mmo_skills.config.SkillRewardConfig;
import com.robertx22.mmo_skills.skill_rewards.base.BaseMoreBlockDrops;
import com.robertx22.mmo_skills.storage.PlayerSkillsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class DoubleBlockDropsReward extends BaseMoreBlockDrops {

    @Override
    public MutableText getChatMessage() {
        return new LiteralText("Double Drop!").formatted(Formatting.GREEN);
    }

    @Override
    public String getId() {
        return "double_block_drops";
    }

    public List<MutableText> getTooltip(SkillRewardConfig config, int level) {
        List<MutableText> list = new ArrayList<>();
        float val = config.getValue(level);
        list.add(new LiteralText("Double block drops chance: " + val + "%").formatted(Formatting.RED));
        return list;
    }

    @Override
    public void modifyStack(PlayerSkillsComponent comp, ItemStack stack) {
        stack.setCount(stack.getCount() * 2);
    }

}
