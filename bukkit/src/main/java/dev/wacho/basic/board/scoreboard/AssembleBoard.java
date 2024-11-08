package dev.wacho.basic.board.scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dev.wacho.basic.board.scoreboard.events.AssembleBoardCreatedEvent;
import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

@Getter
public class AssembleBoard {

	// We assign a unique identifier (random string of ChatColor values)
	// to each board entry to: bypass the 32 char limit, using
	// a team2's prefix & suffix and a team2 entry's display name, and to
	// track the order of entries;
	@Getter private final List<AssembleBoardEntry> entries = new ArrayList<>();
	@Getter private final List<String> identifiers = new ArrayList<>();
	@Getter private final UUID uuid;
	private Scoreboard scoreboard;
	private Objective objective;
	private Assemble assemble;

	public AssembleBoard(Player player, Assemble assemble) {
		this.uuid = player.getUniqueId();
		this.assemble = assemble;
		this.setup(player);
		this.assemble = assemble;

	}

	public Scoreboard getScoreboard() {
		Player player = Bukkit.getPlayer(getUuid());
		if (getAssemble().isHook() || player.getScoreboard() != Bukkit.getScoreboardManager().getMainScoreboard()) {
			return player.getScoreboard();
		} else {
			return Bukkit.getScoreboardManager().getNewScoreboard();
		}
	}

	public Objective getObjective() {
		Scoreboard scoreboard = getScoreboard();
		if (scoreboard.getObjective("Assemble") == null) {
			Objective objective = scoreboard.registerNewObjective("Assemble", "dummy");
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			objective.setDisplayName(getAssemble().getAdapter().getTitle(Bukkit.getPlayer(getUuid())));
			return objective;
		} else {
			return scoreboard.getObjective("Assemble");
		}
	}


	private void setup(Player player) {
		Scoreboard scoreboard = getScoreboard();
		player.setScoreboard(scoreboard);
		getObjective();

		//Send Update
		AssembleBoardCreatedEvent createdEvent = new AssembleBoardCreatedEvent(this);
		Bukkit.getPluginManager().callEvent(createdEvent);
	}

	public AssembleBoardEntry getEntryAtPosition(int pos) {
		if (pos >= this.entries.size()) {
			return null;
		} else {
			return this.entries.get(pos);
		}
	}

	public String getUniqueIdentifier(int position) {
		String identifier = getRandomChatColor(position) + ChatColor.WHITE;

		// Aumenta la longitud máxima del identificador permitido
		int maxLength = 70;

		while (this.identifiers.contains(identifier) || identifier.length() > maxLength) {
			identifier += getRandomChatColor(position) + ChatColor.WHITE;
		}

		this.identifiers.add(identifier);

		return identifier;
	}

	// Gets a random ChatColor and returns the String value of it
	private static String getRandomChatColor(int position) {
		return ChatColor.values()[position].toString();
	}

    public List<AssembleBoardEntry> getEntries() {
        return this.entries;
    }
    
    public List<String> getIdentifiers() {
        return this.identifiers;
    }
    
    public UUID getUuid() {
        return this.uuid;
    }
    
    public Assemble getAssemble() {
        return this.assemble;
    }
}

