package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Label label;
    private AnimationClass runMan;
    private Texture img;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private final List<Coin> coinList = new ArrayList<>();


    @Override
    public void create() {
        map = new TmxMapLoader().load("map2/test.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        batch = new SpriteBatch();
        runMan = new AnimationClass("runRight.png", 8, 1, 16, Animation.PlayMode.LOOP);
        label = new Label(36);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        RectangleMapObject o = (RectangleMapObject) map.getLayers().get("camera").getObjects().get("camera");
        camera.position.x = o.getRectangle().x;
        camera.position.y = o.getRectangle().y;
        camera.update();

        MapLayer ml = map.getLayers().get("монетки");
        if (ml != null) {
            MapObjects mo = ml.getObjects();
            if (mo.getCount() > 0) {
                for (int i = 0; i < mo.getCount(); i++) {
                    RectangleMapObject rect = (RectangleMapObject) ml.getObjects().get(i);
                    coinList.add(new Coin(new Vector2(rect.getRectangle().x, rect.getRectangle().y)));
                }
                for (MapObject object : ml.getObjects()) {
                    RectangleMapObject rect = (RectangleMapObject) object;
                    coinList.add(new Coin(new Vector2(rect.getRectangle().x, rect.getRectangle().y)));
                }
            }
        }
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.position.x++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.position.x--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.position.y++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.position.y--;
        }
        camera.update();

        mapRenderer.setView(camera);
        mapRenderer.render();

        runMan.step(Gdx.graphics.getDeltaTime());

        batch.begin();
        batch.draw(runMan.getTexture(), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        for (Coin coin : coinList) {
            coin.draw(batch, camera);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        runMan.dispose();
        for (Coin coin : coinList) {
            coin.dispose();
        }
    }
}
