package com.robertx22.mmo_skills.main;

import com.robertx22.mmo_skills.storage.PlayerSkillsComponent;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.util.EntityComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class Components {

    public static Components INSTANCE;

    public ComponentType<PlayerSkillsComponent> SKILLS =
        ComponentRegistry.INSTANCE.registerIfAbsent(
            new Identifier(Ref.MODID, "skills"),
            PlayerSkillsComponent.class)
            .attach(EntityComponentCallback.event(PlayerEntity.class), x -> new PlayerSkillsComponent(x));

    public Components() {
        EntityComponents.setRespawnCopyStrategy(SKILLS, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
