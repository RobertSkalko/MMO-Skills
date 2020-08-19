package com.robertx22.mmo_skills.main;

import com.robertx22.mmo_skills.config.MmoConfig;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (screen) -> {
            return AutoConfig.getConfigScreen(MmoConfig.class, screen)
                .get();
        };
    }

    @Override
    public String getModId() {
        return Ref.MODID;
    }
}
