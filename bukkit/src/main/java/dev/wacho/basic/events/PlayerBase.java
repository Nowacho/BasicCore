package dev.wacho.basic.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
public class PlayerBase extends CustomEvent {

    private Player player;
}
