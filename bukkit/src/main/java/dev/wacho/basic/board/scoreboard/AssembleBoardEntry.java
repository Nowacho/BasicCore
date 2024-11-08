package dev.wacho.basic.board.scoreboard;

import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class AssembleBoardEntry {

	private final AssembleBoard board;
	@Setter private String text, identifier;
	private Team team;
	private int position;

	public AssembleBoardEntry(AssembleBoard board, String text, int position) {
		this.board = board;
		this.text = text;
		this.position = position;
		this.identifier = this.board.getUniqueIdentifier(position);

		this.setup();
	}

	public void setup() {
		final Scoreboard scoreboard = this.board.getScoreboard();

		if (scoreboard == null) {
			return;
		}

		String teamName = this.identifier;

		// Incrementa la longitud máxima del nombre del equipo permitida
		int maxTeamNameLength = 70;

		if (teamName.length() > maxTeamNameLength) {
			teamName = teamName.substring(0, maxTeamNameLength);
		}

		Team team = scoreboard.getTeam(teamName);

		if (team == null) {
			team = scoreboard.registerNewTeam(teamName);
		}

		if (!team.getEntries().contains(this.identifier)) {
			team.addEntry(this.identifier);
		}

		if (!this.board.getEntries().contains(this)) {
			this.board.getEntries().add(this);
		}

		this.team = team;
	}

	public void send(int position) {
		if (this.text.length() > 16) {
			String prefix = this.text.substring(0, 16);
			String suffix;

			if (prefix.charAt(15) == ChatColor.COLOR_CHAR) {
				prefix = prefix.substring(0, 15);
				suffix = this.text.substring(15, this.text.length());
			} else if (prefix.charAt(14) == ChatColor.COLOR_CHAR) {
				prefix = prefix.substring(0, 14);
				suffix = this.text.substring(14, this.text.length());
			} else {
				if (ChatColor.getLastColors(prefix).equalsIgnoreCase(ChatColor.getLastColors(this.identifier))) {
					suffix = this.text.substring(16, this.text.length());
				} else {
					suffix = ChatColor.getLastColors(prefix) + this.text.substring(16, this.text.length());
				}
			}

			if (suffix.length() > 16) {
				suffix = suffix.substring(0, 16);
			}

			this.team.setPrefix(prefix);
			this.team.setSuffix(suffix);
		} else {
			this.team.setPrefix(this.text);
			this.team.setSuffix("");
		}

		Score score = this.board.getObjective().getScore(this.identifier);
		score.setScore(position);
	}

	public void remove() {
		this.board.getIdentifiers().remove(this.identifier);
		this.board.getScoreboard().resetScores(this.identifier);
	}

    public void setText(final String text) {
        this.text = text;
    }
    
    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }
}

