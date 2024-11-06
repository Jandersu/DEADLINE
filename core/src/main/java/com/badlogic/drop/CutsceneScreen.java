package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class CutsceneScreen implements Screen {
    final DeadLine game;
    private float cutsceneTimer;
    Stage stage;
    Image image;
    private BitmapFont font;
    private Container<Label> container;

    public CutsceneScreen(final DeadLine game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        image = new Image(Assets.backgroundCutsceneTeste);
        stage.addActor(image);

        font = new BitmapFont();

        Label text = new Label("TESTE", new Label.LabelStyle(font, Color.YELLOW));
        container = new Container<Label>(text);
        container.setTransform(true);
        container.size(100, 60);
        container.setOrigin(container.getWidth() / 2, container.getHeight() / 2);
        container.setPosition(500, 300);
        container.setScale(1);

        stage.addActor(container);
        container.addAction(Actions.parallel(Actions.moveTo(500, 300, 2.0f), Actions.scaleTo(3f, 3f, 2.0f)));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        logic();
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
