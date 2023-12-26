package dev.wacho.basic.board.tablist.utilities;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class TablistUtil {

    public String getListFromString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            stringBuilder.append(list.get(i));
            if (i != list.size() - 1) {
                stringBuilder.append('\n');
            }
        }
        return stringBuilder.toString();
    }
}
