package com.nodomain.ninjavsfarts;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nodomain.ninjavsfarts.states.GameStateManger;
import com.nodomain.ninjavsfarts.states.Menu;

public class NinjaVsFartsGC extends ApplicationAdapter {
	SpriteBatch batch;
	private GameStateManger gsm;
    //data

	@Override
	public void create () {
		gsm = new GameStateManger();
		batch = new SpriteBatch();
		gsm.push(new Menu(gsm));
        Gdx.gl.glClearColor(1, 0, 0, 1);
    }

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
