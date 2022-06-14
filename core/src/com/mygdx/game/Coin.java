package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Coin {
    private final AnimationClass animationClass;
    private Vector2 position;
    private Rectangle rectangle;

    public Coin(Vector2 position) {
        this.animationClass = new AnimationClass("Full Coinss.png", 8, 1, 10, Animation.PlayMode.LOOP);
        this.position = position;
        rectangle = new Rectangle(position.x, position.y, animationClass.getTexture().getRegionWidth(), animationClass.getTexture().getRegionWidth());
    }

    public void draw(SpriteBatch batch, OrthographicCamera camera) {
        animationClass.step(Gdx.graphics.getDeltaTime());
        float cx = (position.x - camera.position.x) / camera.zoom + Gdx.graphics.getWidth() / 2;
        float cy = (position.y - camera.position.y) / camera.zoom + Gdx.graphics.getHeight() / 2;
        batch.draw(animationClass.getTexture(), cx, cy);
    }

    public boolean isOverlaps(Rectangle heroRect, OrthographicCamera camera) {
        float cx = (rectangle.x - camera.position.x) / camera.zoom + Gdx.graphics.getWidth() / 2;
        float cy = (rectangle.y - camera.position.y) / camera.zoom + Gdx.graphics.getHeight() / 2;
        Rectangle rect = new Rectangle(cx, cy, rectangle.width, rectangle.height);
        return rect.overlaps(heroRect);
    }

    public void dispose() {
        animationClass.dispose();
    }
}
