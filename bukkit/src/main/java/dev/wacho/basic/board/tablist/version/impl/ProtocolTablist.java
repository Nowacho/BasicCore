package dev.wacho.basic.board.tablist.version.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import dev.wacho.basic.Basic;
import dev.wacho.basic.board.tablist.TablistColumn;
import dev.wacho.basic.board.tablist.TablistEntry;
import dev.wacho.basic.board.tablist.head.Head;
import dev.wacho.basic.board.tablist.head.HeadManager;
import dev.wacho.basic.board.tablist.player.PlayerTablist;
import dev.wacho.basic.board.tablist.player.PlayerVersionManager;
import dev.wacho.basic.board.tablist.player.module.PlayerVersion;
import dev.wacho.basic.board.tablist.utilities.ClientUtil;
import dev.wacho.basic.board.tablist.version.TablistVersion;
import dev.wacho.basic.board.tablist.utilities.TablistUtil;
import dev.wacho.basic.utils.CC;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ProtocolTablist implements TablistVersion {

    private final HeadManager headManager;

    @Override
    public void updateText(PlayerTablist playerTablist, TablistEntry tablistEntry, String string) {
        if (tablistEntry.getText().equals(string)) {
            return;
        }
        Player player = playerTablist.getPlayer();
        PlayerVersion playerVersion = PlayerVersionManager.getPlayerVersion(player);
        String[] stringArray = PlayerTablist.splitText(string, tablistEntry.getRawSlot());
        if (playerVersion == PlayerVersion.v1_7) {
            Scoreboard scoreboard = player.getScoreboard();
            int n = tablistEntry.getRawSlot();
            Team team = PlayerTablist.getOrCreateTeam(scoreboard, (String) ClientUtil.NAMES.get(n - 1));
            team.setPrefix(CC.translate(stringArray[0]));
            team.setSuffix(CC.translate(stringArray[1]));
        } else {
            PacketContainer packetContainer = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
            StructureModifier structureModifier = packetContainer.getPlayerInfoAction();
            structureModifier.write(0, EnumWrappers.PlayerInfoAction.UPDATE_DISPLAY_NAME);
            WrappedGameProfile wrappedGameProfile = new WrappedGameProfile(tablistEntry.getUuid(), tablistEntry.getId());
            PlayerInfoData playerInfoData = new PlayerInfoData(wrappedGameProfile, tablistEntry.getLatency(), EnumWrappers.NativeGameMode.NOT_SET, WrappedChatComponent.fromText((String) CC.translate(string)));
            StructureModifier structureModifier2 = packetContainer.getPlayerInfoDataLists();
            PlayerInfoData[] playerInfoDataArray = new PlayerInfoData[1];
            playerInfoDataArray[0] = playerInfoData;
            structureModifier2.write(0, Lists.newArrayList(playerInfoDataArray));
            ProtocolTablist.sendPacket(player, packetContainer);
        }
        tablistEntry.setText(string);
    }

    public HeadManager getHeadManager() {
        return this.headManager;
    }

    @Override
    public void updateLatency(PlayerTablist playerTablist, TablistEntry tablistEntry, Integer n) {
        if (tablistEntry.getLatency() == n.intValue()) {
            return;
        }
        PacketContainer packetContainer = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        StructureModifier structureModifier = packetContainer.getPlayerInfoAction();
        structureModifier.write(0, EnumWrappers.PlayerInfoAction.UPDATE_LATENCY);
        WrappedGameProfile wrappedGameProfile = new WrappedGameProfile(tablistEntry.getUuid(), tablistEntry.getId());
        PlayerInfoData playerInfoData = new PlayerInfoData(wrappedGameProfile, n.intValue(), EnumWrappers.NativeGameMode.NOT_SET, WrappedChatComponent.fromText((String) CC.translate(tablistEntry.getText())));
        StructureModifier structureModifier2 = packetContainer.getPlayerInfoDataLists();
        PlayerInfoData[] playerInfoDataArray = new PlayerInfoData[1];
        playerInfoDataArray[0] = playerInfoData;
        structureModifier2.write(0, Lists.newArrayList(playerInfoDataArray));
        ProtocolTablist.sendPacket(playerTablist.getPlayer(), packetContainer);
        tablistEntry.setLatency(n);
    }

    private static void sendPacket(Player player, PacketContainer packetContainer) {
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, packetContainer);
    }

    @Override
    public Head getHead(Player player) {
        Head head = this.headManager.getHead(player.getUniqueId().toString());
        return head;
    }

    public ProtocolTablist(Basic bamboo) {
        this.headManager = bamboo.getHeadManager();
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new ProtocolPacketAdapter(bamboo, this));
    }

    @Override
    public void updateHeaderAndFooter(Player player, List list, List list2) {
        PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        StructureModifier structureModifier = packetContainer.getChatComponents();
        structureModifier.write(0, WrappedChatComponent.fromText(TablistUtil.getListFromString(CC.translate(list))));
        StructureModifier structureModifier2 = packetContainer.getChatComponents();
        structureModifier2.write(1, WrappedChatComponent.fromText(TablistUtil.getListFromString(CC.translate(list2))));
        ProtocolTablist.sendPacket(player, packetContainer);
    }

    @Override
    public void updateSkin(PlayerTablist playerTablist, TablistEntry tablistEntry, Head head) {
        if (head == null || tablistEntry.getHead().equals(head)) {
            return;
        }
        Player player = playerTablist.getPlayer();
        WrappedGameProfile wrappedGameProfile = new WrappedGameProfile(tablistEntry.getUuid(), tablistEntry.getId());
        PlayerInfoData playerInfoData = new PlayerInfoData(wrappedGameProfile, tablistEntry.getLatency(), EnumWrappers.NativeGameMode.NOT_SET, WrappedChatComponent.fromText((String) CC.translate(tablistEntry.getText())));
        Multimap multimap = playerInfoData.getProfile().getProperties();
        multimap.put("textures", new WrappedSignedProperty("textures", head.getValue(), head.getSignature()));
        PacketContainer packetContainer = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        StructureModifier structureModifier = packetContainer.getPlayerInfoAction();
        structureModifier.write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
        StructureModifier structureModifier2 = packetContainer.getPlayerInfoDataLists();
        PlayerInfoData[] playerInfoDataArray = new PlayerInfoData[1];
        playerInfoDataArray[0] = playerInfoData;
        structureModifier2.write(0, Lists.newArrayList(playerInfoDataArray));
        PacketContainer packetContainer2 = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        StructureModifier structureModifier3 = packetContainer2.getPlayerInfoAction();
        structureModifier3.write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        StructureModifier structureModifier4 = packetContainer2.getPlayerInfoDataLists();
        PlayerInfoData[] playerInfoDataArray2 = new PlayerInfoData[1];
        playerInfoDataArray2[0] = playerInfoData;
        structureModifier4.write(0, Lists.newArrayList(playerInfoDataArray2));
        ProtocolTablist.sendPacket(player, packetContainer);
        ProtocolTablist.sendPacket(player, packetContainer2);
        tablistEntry.setHead(head);
    }

    @Override
    public TablistEntry createEntry(PlayerTablist playerTablist, String string, TablistColumn tablistColumn, Integer n, Integer n2) {
        String string2;
        UUID uUID = UUID.randomUUID();
        Player player = playerTablist.getPlayer();
        PlayerVersion playerVersion = PlayerVersionManager.getPlayerVersion(player);
        PacketContainer packetContainer = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        StructureModifier structureModifier = packetContainer.getPlayerInfoAction();
        structureModifier.write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        if (playerVersion != PlayerVersion.v1_7) {
            string2 = string;
        } else {
            int n3 = n2;
            string2 = ClientUtil.ENTRY.get(n3 - 1);
        }
        WrappedGameProfile wrappedGameProfile = new WrappedGameProfile(uUID, string2);
        PlayerInfoData playerInfoData = new PlayerInfoData(wrappedGameProfile, 1, EnumWrappers.NativeGameMode.NOT_SET, WrappedChatComponent.fromText((String)(playerVersion != PlayerVersion.v1_7 ? "" : wrappedGameProfile.getName())));
        Head head = this.headManager.getDefault();
        if (playerVersion != PlayerVersion.v1_7) {
            Multimap multimap = playerInfoData.getProfile().getProperties();
            multimap.put("textures", new WrappedSignedProperty("textures", head.getValue(), head.getSignature()));
        }
        StructureModifier structureModifier2 = packetContainer.getPlayerInfoDataLists();
        PlayerInfoData[] playerInfoDataArray = new PlayerInfoData[1];
        playerInfoDataArray[0] = playerInfoData;
        structureModifier2.write(0, Lists.newArrayList(playerInfoDataArray));
        ProtocolTablist.sendPacket(player, packetContainer);
        int n4 = n;
        int n5 = n2;
        TablistEntry tablistEntry = new TablistEntry(string, uUID, "", playerTablist, head, tablistColumn, n4, n5, 0);
        return tablistEntry;
    }
}