package dev.wacho.basic.handler;

import com.cryptomorin.xseries.messages.Titles;
import dev.wacho.basic.Basic;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import me.activated.core.plugin.AquaCore;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PlayerHandler implements Listener {

    private final Basic plugin = Basic.getInst();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        if (plugin.getConfigFile().getBoolean("JOIN-MESSAGE.SOUND.ENABLED"))
            player.playSound(player.getLocation(), Sound.valueOf(plugin.getConfigFile().getString("JOIN-MESSAGE.SOUND.SOUND").toUpperCase()), 1.0F, 1.0F);

        if (plugin.getConfigFile().getBoolean("JOIN-MESSAGE.ENABLED")) {
            for (String line : plugin.getConfigFile().getStringList("JOIN-MESSAGE.MESSAGE")) {
                player.sendMessage(CC.translate(CC.placeholder(player, line)));
            }
        }

        if (plugin.getConfigFile().getBoolean("JOIN-TITLES.ENABLED")) {
            String path = "JOIN-TITLES.";
            String title = CC.placeholder(player, CC.translate(plugin.getConfigFile().getString(path + ".TITLE")));
            String subTitle = CC.placeholder(player, CC.translate(plugin.getConfigFile().getString(path + ".SUBTITLE")));

            int fadeIn = plugin.getConfigFile().getInt(path + "TIME");
            int stay = plugin.getConfigFile().getInt(path + "TIME");
            int fadeOut = plugin.getConfigFile().getInt(path + "TIME");

            Titles.sendTitle(player, fadeIn, stay, fadeOut, title, subTitle);
        }

        // NameMC Check

        if (!PlayerData.getPlayerData(event.getPlayer().getUniqueId()).isVote() && plugin.getMessageFile().getBoolean("NAME-MC-CHECK.ENABLED")) {
            List<String> voteMessage = CC.translate(plugin.getConfigFile().getStringList("NAME-MC-CHECK.MESSAGE"));
            String voteSound = plugin.getConfigFile().getString("NAME-MC-CHECK.SOUND");
            event.getPlayer().sendMessage(StringUtils.join(voteMessage, "\n"));
            if (voteSound != null || !voteSound.equalsIgnoreCase("none")) {
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf(voteSound), 2F, 2F);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    //Color Sign :)

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onSignChangeEvent(SignChangeEvent event) {
        String[] signLines = event.getLines();
        for (int i = 0; i < signLines.length; ++i) {
            event.setLine(i, CC.translate(signLines[i]));
        }
    }
}
