package dev.wacho.basic.board.tablist.version.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import dev.wacho.basic.Basic;
import dev.wacho.basic.board.tablist.head.Head;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProtocolPacketAdapter extends PacketAdapter {

    private final ProtocolTablist protocolTablist;

    public ProtocolPacketAdapter(Basic plugin, ProtocolTablist protocolTablist) {
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.PLAYER_INFO);
        this.protocolTablist = protocolTablist;
    }

    @Override
    public void onPacketSending(PacketEvent packetEvent) {
        if (packetEvent.getPacketType() != PacketType.Play.Server.PLAYER_INFO) {
            return;
        }

        UUID uUID = packetEvent.getPlayer().getUniqueId();
        PacketContainer packetContainer = packetEvent.getPacket();
        StructureModifier<List<PlayerInfoData>> structureModifier = packetContainer.getPlayerInfoDataLists();
        EnumWrappers.PlayerInfoAction playerInfoAction = packetContainer.getPlayerInfoAction().read(0);

        if (playerInfoAction == EnumWrappers.PlayerInfoAction.ADD_PLAYER) {
            List<PlayerInfoData> playerInfoDataList = structureModifier.read(0);

            for (PlayerInfoData playerInfoData : playerInfoDataList) {
                WrappedGameProfile wrappedGameProfile = playerInfoData.getProfile();
                Optional<WrappedSignedProperty> optional = wrappedGameProfile.getProperties().get("textures").stream().findFirst();

                if (optional.isPresent()) {
                    WrappedSignedProperty wrappedSignedProperty = optional.get();
                    String string = uUID.toString();
                    Head head = new Head();
                    head.setName(string);
                    head.setValue(wrappedSignedProperty.getValue());
                    head.setSignature(wrappedSignedProperty.getSignature());
                    this.protocolTablist.getHeadManager().getHeads().put(string, head);
                }
            }
        }
    }
}