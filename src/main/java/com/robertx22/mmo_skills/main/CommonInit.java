package com.robertx22.mmo_skills.main;

import com.robertx22.mmo_skills.config.MmoConfig;
import com.robertx22.mmo_skills.skill_rewards.base.SkillRewards;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class CommonInit implements ModInitializer {

    @Override
    public void onInitialize() {
        Components.INSTANCE = new Components();

        SkillRewards.init();

        AutoConfig.register(MmoConfig.class, GsonConfigSerializer::new);

        ServerLifecycleEvents.SERVER_STARTED.register(x -> {
            ShowLevelsCommand.register(x.getCommandManager()
                .getDispatcher());
        });

        System.out.println("MMO Skills loaded.");
    }

}
