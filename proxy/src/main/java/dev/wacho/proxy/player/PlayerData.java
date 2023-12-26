package dev.wacho.proxy.player;

public class PlayerData {

    private boolean vip;

    public PlayerData() {
        this.vip = false;
    }

    public boolean isvip() { return vip; }

    public void setvip(boolean vip) {
        this.vip = vip;
    }
}
