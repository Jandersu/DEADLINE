package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class CutsceneScreen implements Screen {
    final DeadLine game;
    private float cutsceneTimer;
    Stage stage;
    Image image;

    public CutsceneScreen(final DeadLine game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        image = new Image(Assets.backgroundCutsceneTeste);
        stage.addActor(image);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
        stage.act(delta);
        stage.draw();
    }

    public void logic(){
        float delta = Gdx.graphics.getDeltaTime();
        cutsceneTimer += delta;
        if (cutsceneTimer > 3f) {
            cutsceneTimer = 0;
            game.setScreen(new Combate(game));
        }
    }

    public void draw(){
        /*ScreenUtils.clear(Color.GRAY);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();

        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        game.batch.draw(Assets.backgroundCutsceneTeste, 0, 0, worldWidth, worldHeight);

        game.batch.end();*/
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        image.setSize(stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}
