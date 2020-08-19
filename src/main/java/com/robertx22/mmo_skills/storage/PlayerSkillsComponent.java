package com.robertx22.mmo_skills.storage;

import com.robertx22.library_of_exile.utils.LoadSave;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

public class PlayerSkillsComponent implements EntitySyncedComponent {

    public PlayerEntity player;

    public PlayerSkillsData data = new PlayerSkillsData();

    public PlayerSkillsComponent(PlayerEntity player) {
        this.player = player;
    }

    public int getLevel(PlayerSkills skill) {
        return get(skill).lvl;
    }

    public SingleSkillData get(PlayerSkills skill) {
        if (!data.skills.containsKey(skill)) {
            data.skills.put(skill, new SingleSkillData());
        }
        return data.skills.get(skill);
    }

    @Override
    public Entity getEntity() {
        return player;
    }

    public void addExp(PlayerSkills skill, int exp) {

        String needed = ", " + get(skill).getExp() + "/" + get(skill).getExpNeededForNextLevel();

        player.sendMessage(new LiteralText("+" + exp + " " + skill.name() + " exp" + needed).formatted(Formatting.YELLOW), false);

        if (get(skill).addExp(exp, skill)) {
            player.sendMessage(new LiteralText(skill.name() + " Leveled up!").formatted(Formatting.BOLD, Formatting.GOLD), false);
        }

    }

    @Override
    public void fromTag(CompoundTag nbt) {
        this.data = LoadSave.Load(PlayerSkillsData.class, new PlayerSkillsData(), nbt, "data");
    }

    @Override
    public CompoundTag toTag(CompoundTag nbt) {
        LoadSave.Save(data, nbt, "data");
        return nbt;
    }
}
