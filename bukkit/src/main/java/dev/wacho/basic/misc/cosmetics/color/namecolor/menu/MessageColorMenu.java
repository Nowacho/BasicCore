package dev.wacho.basic.misc.cosmetics.color.namecolor.menu;

import com.google.common.collect.Maps;
import dev.wacho.basic.misc.cosmetics.color.namecolor.MessageColor;
import dev.wacho.basic.misc.cosmetics.color.namecolor.menu.buttons.MessageColorButton;
import dev.wacho.basic.misc.cosmetics.color.namecolor.menu.buttons.MessageColorRemoveButton;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import org.bukkit.entity.Player;

import java.util.Map;

import static dev.wacho.basic.utils.CC.translate;

public class MessageColorMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8MessageColor ┃ 1/1");
    }

    @Override
    public int size(Player player) {
        return 9 * 6;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        buttons.put(0, new MessageColorButton(MessageColor.DARK_RED));
        buttons.put(1, new MessageColorButton(MessageColor.RED));
        buttons.put(2, new MessageColorButton(MessageColor.ORANGE));
        buttons.put(3, new MessageColorButton(MessageColor.YELLOW));
        buttons.put(4, new MessageColorButton(MessageColor.DARK_GREEN));
        buttons.put(5, new MessageColorButton(MessageColor.GREEN));
        buttons.put(6, new MessageColorButton(MessageColor.DARK_BLUE));
        buttons.put(7, new MessageColorButton(MessageColor.BLUE));
        buttons.put(8, new MessageColorButton(MessageColor.DARK_AQUA));
        buttons.put(9, new MessageColorButton(MessageColor.AQUA));
        buttons.put(10, new MessageColorButton(MessageColor.PURPLE));
        buttons.put(11, new MessageColorButton(MessageColor.PINK));
        buttons.put(12, new MessageColorButton(MessageColor.WHITE));
        buttons.put(13, new MessageColorButton(MessageColor.GRAY));
        buttons.put(14, new MessageColorButton(MessageColor.DARK_GRAY));
        buttons.put(15, new MessageColorButton(MessageColor.BLACK));

        buttons.put(49, new MessageColorRemoveButton());

        return buttons;
    }
}
