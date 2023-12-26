package dev.wacho.basic.board.tablist;

import dev.wacho.basic.board.tablist.player.PlayerTablist;
import dev.wacho.basic.board.tablist.head.Head;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class TablistEntry {

    private String id;
    private UUID uuid;
    private String text;
    private PlayerTablist tablist;
    private Head head;
    private TablistColumn column;
    private int slot;
    private int rawSlot;
    private int latency;
}