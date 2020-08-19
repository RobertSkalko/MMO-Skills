package com.robertx22.mmo_skills.storage;

import com.robertx22.mmo_skills.config.MmoConfig;
import com.robertx22.mmo_skills.config.SkillConfig;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public enum PlayerSkills {

    MINING() {
        @Override
        public SkillConfig getConfig() {
            return MmoConfig.get().Mining;
        }
    },
    DUNGEONEERING() {
        @Override
        public SkillConfig getConfig() {
            return MmoConfig.get().Dungeoneering;
        }
    },
    FARMING() {
        @Override
        public SkillConfig getConfig() {
            return MmoConfig.get().Farming;
        }
    };

    public abstract SkillConfig getConfig();

    public final List<MutableText> getFullTooltip(PlayerSkillsComponent comp) {

        List<MutableText> list = getShortTooltip(comp);

        getConfig().getRewardConfigs()
            .forEach(x -> {
                list.addAll(x.getReward()
                    .getTooltip(x, comp.getLevel(this)));
            });

        return list;

    }

    public final List<MutableText> getShortTooltip(PlayerSkillsComponent comp) {

        List<MutableText> list = new ArrayList<>();

        MutableText name = new LiteralText(name() + ": ").formatted(Formatting.BLUE);
        MutableText level = new LiteralText("LVL: " + comp.getLevel(this)).formatted(Formatting.YELLOW);
        MutableText exp = new LiteralText(", EXP: " + comp.get(this)
            .getExp() + "/" + comp.get(this)
            .getExpNeededForNextLevel()).formatted(Formatting.GREEN);

        list.add(name.append(level)
            .append(exp));

        return list;

    }

}
