package dev.wacho.basic.board.tablist;

import dev.wacho.basic.Basic;
import dev.wacho.basic.board.tablist.head.Head;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Accessors(chain = true)
@Getter @Setter
public final class TablistLayout {

    private TablistColumn column;
    private int slot;
    private String text;
    private int ping;
    private Head head;

    public TablistLayout(Basic plugin, TablistColumn column, int slot) {
        this(plugin, column, slot, "", -1);
    }

    public TablistLayout(Basic plugin, TablistColumn column, int slot, String text) {
        this(plugin, column, slot, text, -1);
    }

    public TablistLayout(Basic plugin, TablistColumn column, int slot, String text, int ping) {
        this(column, slot, text, ping, plugin.getHeadManager().getDefault());
    }

    public TablistLayout(TablistColumn column, int slot, String text, Head head) {
        this(column, slot, text, -1, head);
    }

    public TablistLayout(TablistColumn column, int slot, String text, Head head, int ping) {
        this(column, slot, text, ping, head);
    }
}