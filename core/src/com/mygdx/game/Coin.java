package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Coin {
    private final AnimationClass animationClass;
    private Vector2 position;

    public Coin(Vector2 position) {
        this.animationClass = new AnimationClass("Full Coinss.png", 8, 1, 10 , Animation.PlayMode.LOOP);
        this.position = position;
    }

    public void draw(SpriteBatch batch, OrthographicCamera camera) {
        animationClass.step(Gdx.graphics.getDeltaTime());
        float cx = (position.x - camera.position.x)/camera.zoom + Gdx.graphics.getWidth()/2;
        float cy = (position.y - camera.position.y)/camera.zoom + Gdx.graphics.getHeight()/2;
        batch.draw(animationClass.getTexture(), cx, cy);
    }

    public void dispose() {
        animationClass.dispose();
    }
}
