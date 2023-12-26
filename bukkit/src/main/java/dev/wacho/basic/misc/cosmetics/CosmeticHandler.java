package dev.wacho.basic.misc.cosmetics;

import dev.wacho.basic.misc.cosmetics.death.killeffect.EffectCallable;
import dev.wacho.basic.misc.cosmetics.death.killeffect.impl.KillEffects;
import dev.wacho.basic.misc.cosmetics.effects.Effects;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class CosmeticHandler implements Listener {

   @EventHandler
   private void onPlayerJoin(PlayerJoinEvent event) {
      Player player = event.getPlayer();
      PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

      if (playerData.getJoinMessage() != null) {
         for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendMessage(CC.translate(player.getDisplayName() + playerData.getJoinMessage().getMessage()));
         }
      }
   }

   @EventHandler
   public void onPlayerMove(PlayerMoveEvent event) {
      Player player = event.getPlayer();
      PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

      if (playerData.getEffects() != null) {
         Effects effects = PlayerData.getPlayerData(player.getUniqueId()).getEffects();

      }
   }

   @EventHandler
   public void onPlayerDeath(PlayerDeathEvent event) {
      Player victim = event.getEntity();
      Player killer = event.getEntity().getKiller();

      if (killer != null) {
         PlayerData playerData = PlayerData.getPlayerData(killer.getUniqueId());
         KillEffects killEffects = playerData.getKillEffects();

         if (killEffects != null) {
            EffectCallable callable = killEffects.getCallable();
            if (callable != null) {
               callable.call(victim.getLocation());
            }
         }

         if (playerData.getDeathMessage() != null) {
            victim.sendMessage(CC.translate(killer.getDisplayName() + " " + playerData.getDeathMessage().getDisplayName()));
         }
      }
   }

   @EventHandler
   private void onProjectileLaunch(ProjectileLaunchEvent event) {
      Projectile projectile = event.getEntity();

      if (projectile.getType() != EntityType.SPLASH_POTION) {
         if (projectile.getShooter() instanceof Player) {
            Player player = (Player) projectile.getShooter();
            PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

            if (playerData.getProjectiles() != null) {
               playerData.getProjectiles().playEffect(player, projectile);
            }
         }
      }
   }
}
