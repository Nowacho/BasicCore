package dev.wacho.basic.utils.lang;

import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.FileConfig;
import lombok.Getter;

@Getter
public class Lang {

    private static final FileConfig messages = Basic.getInst().getMessageFile();
    private static final FileConfig settings = Basic.getInst().getConfigFile();

    public static String SERVER_IP = settings.getString("NAME-MC-CHECK.SERVER-IP");
    public static String SERVER_NAME = settings.getString("SERVER-NAME");

    public static String NO_PERMISSION = messages.getString("MESSAGES.NO-PERMISSIONS");
    public static String NO_CONSOLE = messages.getString("MESSAGES.NO-CONSOLE");
    public static String OFFLINE_PLAYER = messages.getString("MESSAGES.OFFLINE-PLAYER");
    public static String INVALID_NUMBER = messages.getString("MESSAGES.INVALID-NUMBER");

    public static String SERVER_NOT_FOUND = messages.getString("MESSAGES.SERVER.NOT-FOUND");
    public static String SERVER_ALREADY_CONNECTED = messages.getString("MESSAGES.SERVER.ALREADY-CONNECTED");
    public static String SERVER_CONNECTING = messages.getString("MESSAGES.SERVER.CONNECTING");

    public static String COINS = messages.getString("MESSAGES.COINS.COINS");
    public static String COINS_TARGET = messages.getString("MESSAGES.COINS.COINS-TARGET");
    public static String COINS_GIVE = messages.getString("MESSAGES.COINS.GIVE");
    public static String COINS_GIVE_TARGET = messages.getString("MESSAGES.COINS.GIVE-TARGET");
    public static String COINS_SET = messages.getString("MESSAGES.COINS.SET");
    public static String COINS_SET_TARGET = messages.getString("MESSAGES.COINS.SET-TARGET");
    public static String COINS_REMOVED = messages.getString("MESSAGES.COINS.REMOVED");
}
