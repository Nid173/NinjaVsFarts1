package com.nodomain.ninjavsfarts.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Menu extends state {
    private Texture background;
    private Texture startBtn;
    private Texture infoBtn;
    private Texture settingsBtn;
    private Texture title;
    private Rectangle startRec;
    private Rectangle settingsRec;
    private Rectangle infoRec;




    public Menu(GameStateManger gsm) {
        super(gsm);
        background = new Texture("Desert.png");
        startBtn = new Texture("StartBtn.png");
        infoBtn = new Texture ("infoBtn.png");
        settingsBtn = new Texture("settingBtn.png");
        title = new Texture("Title.png");
        startRec = new Rectangle();
        startRec.set(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/3 + Gdx.graphics.getHeight()/8,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8);
        settingsRec = new Rectangle();
        settingsRec.set(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/3 + Gdx.graphics.getHeight()/8 *2,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8);
        infoRec = new Rectangle (Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/3 + Gdx.graphics.getHeight()/8 *3,Gdx.graphics.getWidth()/2 ,Gdx.graphics.getHeight()/8);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            if (startRec.contains(tmp)) {
                gsm.set(new Game(gsm));
                dispose();
            }else if (settingsRec.contains(tmp)) {
                gsm.set(new Settings(gsm));
                dispose();
            }else if (infoRec.contains(tmp)) {
                gsm.set(new info(gsm));
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
        sb.draw(background, 0, 0,background.getWidth() , Gdx.graphics.getHeight());
        sb.draw(title,Gdx.graphics.getWidth()/2 - (float) (Gdx.graphics.getWidth()*0.9)/2, (float) (Gdx.graphics.getHeight()*0.7), (float) (Gdx.graphics.getWidth()*0.9),Gdx.graphics.getHeight()/4);
        sb.draw(startBtn,Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/3 + Gdx.graphics.getHeight()/8,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8);
        sb.draw(settingsBtn,Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/3,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8);
        sb.draw(infoBtn,Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/3 - Gdx.graphics.getHeight()/8,Gdx.graphics.getWidth()/2 ,Gdx.graphics.getHeight()/8);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        startBtn.dispose();
        settingsBtn.dispose();
        infoBtn.dispose();
        title.dispose();
    }
}
