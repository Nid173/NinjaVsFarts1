package com.nodomain.ninjavsfarts.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class info extends state{
        private Texture infoPage;

    protected info(GameStateManger gsm) {
        super(gsm);
        infoPage = new Texture("infoPage.png");

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new Menu(gsm));
            dispose();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(infoPage,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        infoPage.dispose();
    }
}
