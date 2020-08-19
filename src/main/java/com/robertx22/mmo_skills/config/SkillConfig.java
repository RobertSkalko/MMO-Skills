package com.robertx22.mmo_skills.config;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SkillConfig {

    public int maximumLevel = 99;

    public int extraExpPerAction = 0;

    public ValidBlocksConfig validBlocksConfig = new ValidBlocksConfig();

    private List<SkillRewardConfig> skillRewards = new ArrayList<>();

    public void addReward(SkillRewardConfig reward) {
        this.skillRewards.add(reward);
    }

    public List<SkillRewardConfig> getRewardConfigs() {
        List<SkillRewardConfig> list = new ArrayList<>(skillRewards);
        list.sort(Comparator.comparingInt(x -> x.priority));
        return list;

    }

}
