package model;

import com.nkasenides.mmog.model.player.TeamPlayer;

public class RCPlayer extends TeamPlayer {

    private String password;

    public RCPlayer(String id, String name, String teamID, String password) {
        super(id, name, teamID);
        this.password = password;
    }

    public RCPlayer(String name, String teamID, String password) {
        super(name, teamID);
        this.password = password;
    }

    public RCPlayer(String teamID, String password) {
        super(teamID);
        this.password = password;
    }

    public RCPlayer(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
