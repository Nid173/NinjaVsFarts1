package com.nodomain.ninjavsfarts.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Settings extends state {
    private Texture background;
    private Texture[] MusicOnOf;
    private Texture[] SoundOnOf;
    private Rectangle musicRec;
    private Rectangle soundRec;



    public Settings(GameStateManger gsm) {
        super(gsm);
        background = new Texture("Settingsbg.png");
        MusicOnOf = new Texture[2];
        MusicOnOf[0] = new Texture("musicOffB.png");
        MusicOnOf[1] = new Texture("musicOnB.png");
        SoundOnOf = new Texture[2];
        SoundOnOf[0] = new Texture("audioOffB.png");
        SoundOnOf[1] = new Texture("audioOnB.png");

        musicRec = new Rectangle();
        musicRec.set(Gdx.graphics.getWidth() / 2 - MusicOnOf[musicState].getWidth(),Gdx.graphics.getHeight() / 10 * 3 + MusicOnOf[musicState].getHeight(),MusicOnOf[musicState].getWidth(),MusicOnOf[musicState].getHeight());
        soundRec = new Rectangle();
        soundRec.set(Gdx.graphics.getWidth() / 2 - SoundOnOf[soundState].getWidth(),Gdx.graphics.getHeight() / 10 * 6 + SoundOnOf[soundState].getHeight(),SoundOnOf[soundState].getWidth(),SoundOnOf[soundState].getHeight());


    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            if (musicRec.contains(tmp)) {
                if (getMusicState() == 0) {
                    setMusicState(1);
                    setMastervol(1);
                } else {
                    setMusicState(0);
                    setMastervol(0);
                }

            } else if (soundRec.contains(tmp)) {
                if (getSoundState() == 0) {
                    setSoundState(1);
                    setSfxvol(1);
                } else {
                    setSoundState(0);
                    setSfxvol(0);
                }
            } else {
                gsm.set(new Menu(gsm));
                dispose();
            }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(background, 0, 0,Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        sb.draw(MusicOnOf[musicState],Gdx.graphics.getWidth() / 2 - MusicOnOf[musicState].getWidth(),Gdx.graphics.getHeight() / 10 * 6);
        sb.draw(SoundOnOf[soundState],Gdx.graphics.getWidth() / 2 - SoundOnOf[soundState].getWidth(),Gdx.graphics.getHeight() / 10 * 3);
        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        MusicOnOf[0].dispose();
        MusicOnOf[1].dispose();
        SoundOnOf[0].dispose();
        SoundOnOf[1].dispose();


    }
}
