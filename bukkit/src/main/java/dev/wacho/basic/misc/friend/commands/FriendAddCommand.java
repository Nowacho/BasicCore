package dev.wacho.basic.misc.friend.commands;

public class FriendAddCommand {
    @Command(names = "friend add", permission = "")
    public static void add(Player sender, @Param(name = "target")UUID uuid) {
        Profile senderProfile = Profile.getByUuid(sender.getUniqueId());
        Profile target = Profile.getByUuid(uuid);

        if (target.getIgnored().contains(sender.getUniqueId())) {
            sender.sendMessage(CC.translate("&cThat player has you ignored."));
            return;
        }

        if (!senderProfile.getFriendCooldown().hasExpired()) {
            sender.sendMessage(CC.translate("&cYou cannot send a friend request for 30 seconds."));
            return;
        }

        if (senderProfile.getFriendRequests().contains(senderProfile.friendRequestByName(sender.getUniqueId()))) {
            sender.sendMessage(CC.translate("&cYou already have a pending friend request outgoing to that player."));
            return;
        }

        if (senderProfile.getUuid() == target.getUuid()) {
            sender.sendMessage(CC.translate("&cYou cannot add yourself as a friend."));
            return;
        }

        if (senderProfile.getFriends().contains(senderProfile.friendByName(target.getUuid()))) {
            sender.sendMessage(CC.translate("&cThat player is already your friend."));
            return;
        }

        if (!target.getOptions().isFriendRequestsEnabled()) {
            sender.sendMessage(CC.translate("&cThat player does not have their friend requests enabled."));
            return;
        }

        senderProfile.setFriendCooldown(new Cooldown(30000));
        senderProfile.save();

        target.getFriendRequests().add(new FriendRequest(senderProfile.getUuid(), target.getUuid()));
        target.save();

        new FriendRequestPacket(senderProfile, target).send();

        sender.sendMessage(CC.translate("&eYou have successfully sent &d" + target.getDisplayName() + "&e a friend request."));
    }
}
