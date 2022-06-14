package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Label {
    private BitmapFont bitmapFont;

    public Label(int size) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("ofont.ru_NeverSmile.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = size;
        fontParameter.characters = "!@#$%^&*()1234567890ЙЦУКЕНГШЩЗХЪЭЖДЛОРПАВЫФЯЧСМИТЬБЮ,йцукенгшщзхъэждлорпавыфячсмитьбю.1234567890";
        bitmapFont = fontGenerator.generateFont(fontParameter);
    }

    public void draw(SpriteBatch batch, String text, int x, int y) {
        bitmapFont.draw(batch, text, x, y + bitmapFont.getLineHeight());
    }
}
