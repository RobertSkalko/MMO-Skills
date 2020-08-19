package com.robertx22.mmo_skills.skill_rewards.base;

import com.robertx22.mmo_skills.skill_rewards.DoubleBlockDropsReward;
import com.robertx22.mmo_skills.skill_rewards.DoubleChestItemDropsReward;
import com.robertx22.mmo_skills.skill_rewards.ExtraBlockDropsReward;
import com.robertx22.mmo_skills.skill_rewards.ExtraChestItemDropsReward;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillRewards {

    public SkillRewards() {
        register(new DoubleBlockDropsReward());
        register(new ExtraBlockDropsReward());
        register(new DoubleChestItemDropsReward());
        register(new ExtraChestItemDropsReward());
    }

    private void register(SkillReward reward) {
        map.put(reward.getId(), reward);
        list.add(reward);
    }

    public List<SkillReward> getAll() {
        return list;
    }

    public SkillReward get(String id) {
        return map.get(id);
    }

    public static SkillRewards INSTANCE;

    List<SkillReward> list = new ArrayList<>();

    HashMap<String, SkillReward> map = new HashMap<>();

    public static void init() {

        INSTANCE = new SkillRewards();

    }
}
