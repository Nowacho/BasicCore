package dev.wacho.basic.board.tablist;

import com.google.common.collect.Lists;
import dev.wacho.basic.Basic;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.board.tablist.head.HeadManager;
import dev.wacho.basic.utils.Animation;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.Weight;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class TablistProvider implements TablistAdapter {

    private final Basic plugin;
    private final HeadManager headManager;
    @Getter
    private static final LinkedList<Weight> ranks = Lists.newLinkedList();

    public TablistProvider(Basic plugin) {
        this.plugin = plugin;
        this.headManager = plugin.getHeadManager();
    }

    @Override
    public List<String> getHeader(Player player) {
        if (Basic.getInst().getTabFile().getBoolean("HEADER-ANIMATED.ENABLED")) {
            return Collections.singletonList(CC.translate(Animation.getTabListHeader()));
        } else {
            return Collections.singletonList(CC.translate(Basic.getInst().getTabFile().getString("HEADER-TEXT")));
        }
    }

    @Override
    public List<String> getFooter(Player player) {
        if (Basic.getInst().getTabFile().getBoolean("FOOTER-ANIMATED.ENABLED")) {
            return Collections.singletonList(CC.translate(Animation.getTabListFooter()));
        } else {
            return Collections.singletonList(CC.translate(Basic.getInst().getTabFile().getString("FOOTER-TEXT")));
        }
    }

    @Override
    public Set<TablistLayout> getTablist(Player player) {
        Set<TablistLayout> layout = new HashSet<>();
        LinkedList<Weight> ranksSorted = ranks.stream().sorted(Comparator.comparing(Weight::getInteger).reversed()).limit(80L).collect(Collectors.toCollection(LinkedList::new));
        PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

        /*if (playerData.getTabType() == TabType.DEFAULT) {
            for (int i = 1; i <= 20; ++i) {
                layout.add(new TablistLayout(plugin, TablistColumn.LEFT, i)
                        .setText(CC.placeholder(player, replace(player, plugin.getTabFile().getString("LEFT." + i + ".TEXT"))))
                        .setHead(headManager.getHead(player, plugin.getTabFile().getString("LEFT." + i + ".SKIN"))));
                layout.add(new TablistLayout(plugin, TablistColumn.MIDDLE, i)
                        .setText(CC.placeholder(player, replace(player, plugin.getTabFile().getString("MIDDLE." + i + ".TEXT"))))
                        .setHead(headManager.getHead(player, plugin.getTabFile().getString("MIDDLE." + i + ".SKIN"))));
                layout.add(new TablistLayout(plugin, TablistColumn.RIGHT, i)
                        .setText(CC.placeholder(player, replace(player, plugin.getTabFile().getString("RIGHT." + i + ".TEXT"))))
                        .setHead(headManager.getHead(player, plugin.getTabFile().getString("RIGHT." + i + ".SKIN"))));
                layout.add(new TablistLayout(plugin, TablistColumn.FAR_RIGHT, i)
                        .setText(CC.placeholder(player, replace(player, plugin.getTabFile().getString("FAR-RIGHT." + i + ".TEXT"))))
                        .setHead(headManager.getHead(player, plugin.getTabFile().getString("FAR-RIGHT." + i + ".SKIN"))));
            }
        } else if (playerData.getTabType() == TabType.WEIGHT) {
            for (int i = 0; i < ranksSorted.size(); ++i) {
                List<TablistColumn> list = Arrays.asList(TablistColumn.LEFT, TablistColumn.MIDDLE, TablistColumn.RIGHT, TablistColumn.FAR_RIGHT);
                int n = i % 4;
                int n2 = i / 4 + 1;
                if (ranksSorted.get(i) == null) continue;
                Weight weight = ranksSorted.get(i);
                //layout.add(new TablistLayout().text(weight.getFormat()).slot(n2).ping(-1).column(list.get(n)));
            }
        }*/
        return layout;
    }

    private String replace(Player player, String text) {
        text = CC.placeholder(player, text);
        return text;
    }
}
