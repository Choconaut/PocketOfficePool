package com.example.pocketofficepool.pool;

import com.example.pocketofficepool.BaseEntity;
import com.example.pocketofficepool.BettingType;
import com.example.pocketofficepool.Status;
import com.example.pocketofficepool.game.Game;
import com.example.pocketofficepool.gamegroup.GameGroup;
import com.example.pocketofficepool.pick.Pick;
import com.example.pocketofficepool.pooltype.PoolType;
import com.example.pocketofficepool.user.User;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Pool extends BaseEntity {

    private String name;

    @ManyToOne
    private User owner;

    @Enumerated(EnumType.STRING)
    private BettingType bettingType; // Straight up, spread

    @OneToMany(mappedBy = "pool", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pick> picks;

    @OneToMany(mappedBy = "pool", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> games; // Games in this pool

    @OneToMany(mappedBy = "pool", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameGroup> gameGroups; // Groups of games in this pool

    private ZonedDateTime openTime; // When picks can be made

    private ZonedDateTime closeTime; // When picks can no longer be made

    private Status status;

    @ManyToOne
    private PoolType poolType;

    public ZonedDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(ZonedDateTime openTime) {
        this.openTime = openTime;
    }

    public ZonedDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(ZonedDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PoolType getPoolType() {
        return poolType;
    }

    public void setPoolType(PoolType poolType) {
        this.poolType = poolType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public BettingType getBettingType() {
        return bettingType;
    }

    public void setBettingType(BettingType bettingType) {
        this.bettingType = bettingType;
    }

    public List<Pick> getPicks() {
        return picks;
    }

    public void setPicks(List<Pick> picks) {
        this.picks = picks;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<GameGroup> getGameGroups() {
        return gameGroups;
    }

    public void setGameGroups(List<GameGroup> gameGroups) {
        this.gameGroups = gameGroups;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public void addGameGroup(GameGroup gameGroup) {
        this.gameGroups.add(gameGroup);
    }
}
