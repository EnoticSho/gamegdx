package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationClass {
    private final Texture texture;
    private final Animation<TextureRegion> animation;
    private float time;

    public AnimationClass(String name, int width, int height, float fps, Animation.PlayMode mode) {
        texture = new Texture(name);
        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegion[][] regions = textureRegion.split(textureRegion.getRegionWidth() / width, textureRegion.getRegionHeight() / height);
        TextureRegion[] regions1 = new TextureRegion[width * height];

        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                regions1[count++] = regions[i][j];
            }
        }
        animation = new Animation<>(1.0f / fps, regions1);
        animation.setPlayMode(mode);
    }


    public void step(float time) {
        this.time += time;
    }

    public TextureRegion getTexture() {
        return animation.getKeyFrame(time);
    }

    public void dispose() {
        texture.dispose();
    }

}
