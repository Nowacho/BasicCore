package dev.wacho.basic.utils.cooldown;

import dev.wacho.basic.events.CustomEvent;
import dev.wacho.basic.events.PlayerBase;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class CooldownExpiredEvent extends PlayerBase {

    private Cooldown cooldown;
    private boolean forced;

    CooldownExpiredEvent(Player player, Cooldown cooldown) {
        super(player);
        this.cooldown = cooldown;
    }

    public CustomEvent setForced(boolean forced) {
        this.forced = forced;
        return this;
    }
}
