package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Label label;
	AnimationClass runMan;
	private int x;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		runMan = new AnimationClass("scottpilgrim_multiple.png", 8, 1, 16, Animation.PlayMode.LOOP);
		label = new Label(36);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x++;
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x--;

		runMan.step(Gdx.graphics.getDeltaTime());

		batch.begin();
		batch.draw(runMan.getTexture(), x, 0);
		label.draw(batch, "паывпвыап");
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		runMan.dispose();
	}
}
