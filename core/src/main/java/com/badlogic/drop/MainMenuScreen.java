package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {
    final Drop game;
    Stage stage;
    TextureAtlas buttonAtlas;

    public MainMenuScreen(final Drop game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        buttonAtlas = new TextureAtlas(Gdx.files.local("buttons/buttons.pack"));

        CriarBotao botaoJogar = new CriarBotao("MINIGAME", buttonAtlas);
        TextButton jogarBotao = botaoJogar.getBotao();

        CriarBotao botaoSair = new CriarBotao("SAIR", buttonAtlas);
        TextButton sairBotao = botaoSair.getBotao();

        //Adiciona acoes e efeitos de hover ao botão "Jogar"
        jogarBotao.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game));
                game.setScreen(new Combate(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                jogarBotao.getStyle().up = new TextureRegionDrawable(buttonAtlas.findRegion("hover-button"));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                jogarBotao.getStyle().up = new TextureRegionDrawable(buttonAtlas.findRegion("botao-normal"));
            }
        });

        //Adiciona acoes e efeitos de hover ao botão "Sair"
        sairBotao.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                sairBotao.getStyle().up = new TextureRegionDrawable(buttonAtlas.findRegion("hover-button"));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                sairBotao.getStyle().up = new TextureRegionDrawable(buttonAtlas.findRegion("botao-normal"));
            }
        });

        //Organizar os botões em uma tabela
        Table table = new Table();
        table.setFillParent(true);
        table.add(jogarBotao).padBottom(10).row();
        table.add(sairBotao).padBottom(10);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
       //ScreenUtils.clear(Color.BLACK);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
