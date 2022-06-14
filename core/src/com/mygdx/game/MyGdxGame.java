package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

import java.sql.ResultSet;
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
    private Texture fon;
    private Rectangle heroRect;

    private int[] foreGround, backGround;

    private int score;


    @Override
    public void create() {
        fon = new Texture("fon.png");
        map = new TmxMapLoader().load("maps/map1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        label = new Label(30);

        batch = new SpriteBatch();

        runMan = new AnimationClass("runRight.png", 8, 1, 16, Animation.PlayMode.LOOP);
        heroRect = new Rectangle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, runMan.getTexture().getRegionWidth(), runMan.getTexture().getRegionHeight());

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        RectangleMapObject o = (RectangleMapObject) map.getLayers().get("Слой объектов 2").getObjects().get("camera");
        camera.position.x = o.getRectangle().x;
        camera.position.y = o.getRectangle().y;
        camera.update();

        foreGround = new int[1];
        foreGround[0] = map.getLayers().getIndex("Слой тайлов 2");
        backGround = new int[1];
        backGround[0] = map.getLayers().getIndex("Слой тайлов 1");

        MapLayer ml = map.getLayers().get("coins");
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

        batch.begin();
        batch.draw(fon, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        mapRenderer.setView(camera);
        mapRenderer.render(backGround);

        runMan.step(Gdx.graphics.getDeltaTime());

        batch.begin();
        batch.draw(runMan.getTexture(), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        label.draw(batch, "монеток собрано " + String.valueOf(score), 0, 0);
        for (Coin coin : coinList) {
            coin.draw(batch, camera);
            if (coin.isOverlaps(heroRect, camera)) {
                score++;
//                coinList.remove(coin);
            }
        }
        batch.end();
        mapRenderer.render(foreGround);
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
