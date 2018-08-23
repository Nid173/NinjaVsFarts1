package com.nodomain.ninjavsfarts.states;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class state {
    static float Mastervol = 1;
    static float Sfxvol = 1;
    static int musicState = 1;
    static int soundState = 1;

    public static int getSoundState() {
        return soundState;
    }

    public static void setSoundState(int soundState) {
        state.soundState = soundState;
    }

    public float getMastervol() {
        return Mastervol;
    }

    public void setMastervol(float mastervol) {
        Mastervol = mastervol;
    }

    public float getSfxvol() {
        return Sfxvol;
    }

    public void setSfxvol(float sfxvol) {
        Sfxvol = sfxvol;
    }

    public int getMusicState() {
        return musicState;
    }

    public void setMusicState(int musicState) {
        this.musicState = musicState;
    }

    protected GameStateManger gsm;



    protected state(GameStateManger gsm){
        this.gsm = gsm;
    }
    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();


}
