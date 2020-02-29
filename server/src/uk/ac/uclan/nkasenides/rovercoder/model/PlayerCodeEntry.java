package uk.ac.uclan.nkasenides.rovercoder.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.beans.Transient;

@Entity
public class PlayerCodeEntry {

    @Id private Long id;
    @Index private String playerID;
    private String playerName;
    private String code;
    private String workspace;
    @Index private long uploadedOn = -1;
    @Index private boolean played = false;
    @Index private int points = 0;

    private PlayerCodeEntry() { }

    public PlayerCodeEntry(String playerID, String playerName, String code, String workspace) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.code = code;
        this.workspace = workspace;
        this.uploadedOn = System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(long uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    @Transient
    public Key<PlayerCodeEntry> getKey() {
        return Key.create(PlayerCodeEntry.class, getId());
    }

}
