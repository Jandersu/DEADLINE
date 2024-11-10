package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PersonagemTela implements Screen {
    final DeadLine game;
    Image mapButton;
    Image personagemSprite;
    Image exclamacaoSprite;
    Texture backGround;
    Stage stage;
    int personagem;

    public PersonagemTela(final DeadLine game, TextureRegion personagemTexture, Texture backGround, int personagem) {
        this.game = game;
        this.personagem = personagem;

        stage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT));

        this.backGround = backGround;
        personagemSprite = new Image(personagemTexture);
        stage.addActor(personagemSprite);
        personagemSprite.setX(768);

        exclamacaoSprite = new Image(Assets.exclaimIcon);
        stage.addActor(exclamacaoSprite);
        exclamacaoSprite.setPosition(personagemSprite.getX(), personagemSprite.getY()+32);

        personagemSprite.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Oi");
            }
        });

        exclamacaoSprite.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("!");
            }
        });


        mapButton = new Image(Assets.mapIcon);

        mapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(DeadLine.ScreenKey.Map);
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.top().right();
        table.add(mapButton).width(50).height(50);
        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);
        stage.getViewport().apply();
        game.batch.setProjectionMatrix(stage.getViewport().getCamera().combined);

        game.batch.begin();

        float worldWidth = stage.getViewport().getWorldWidth();
        float worldHeight = stage.getViewport().getWorldHeight();

        game.batch.draw(backGround, 0, 0, worldWidth, worldHeight); // draw the background

        /*personagemSprite.setPosition(768, 0);

        personagemSprite.draw(game.batch);

        exclamacaoSprite.setPosition(
            personagemSprite.getX() + personagemSprite.getWidth() / 2 - exclamacaoSprite.getWidth() / 2,
            personagemSprite.getY() + personagemSprite.getHeight()
        );

        exclamacaoSprite.draw(game.batch);*/

        game.batch.end();

        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
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
        dispose();
    }

    @Override
    public void dispose() {

    }
}
