package dev.wacho.basic.rank;

import java.util.List;

public class RankHandler {
    private List<Rank> ranks;

    public RankHandler(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public Rank getRankByName(String name) {
        return ranks.stream()
                .filter(rank -> rank.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // You can add more methods here for handling ranks, such as getting a list of all ranks, etc.
}
