package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenuScreen implements Screen {
    final DeadLine game;
    Stage stage;
    Texture backgroundMainMenu;
    int teste = 0;

    public MainMenuScreen(final DeadLine game) {
        this.game = game;
        stage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT));

        Assets.buttonAtlas = new TextureAtlas(Gdx.files.local("buttons/buttons.pack"));

        backgroundMainMenu = new Texture(String.valueOf(Assets.backgroundMainMenu));

        CriarBotao botaoJogar = new CriarBotao("NOVO JOGO", Assets.buttonAtlas);
        TextButton jogarBotao = botaoJogar.getBotao();

        CriarBotao botaoSair = new CriarBotao("SAIR", Assets.buttonAtlas);
        TextButton sairBotao = botaoSair.getBotao();

        //Adiciona acoes e efeitos de hover ao botão "Jogar"
        jogarBotao.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(DeadLine.ScreenKey.Cutscene);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                jogarBotao.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("hover-button"));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                jogarBotao.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("botao-normal"));
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
                sairBotao.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("hover-button"));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                sairBotao.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("botao-normal"));
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
        ScreenUtils.clear(Color.BLACK);
        stage.getViewport().apply();
        game.batch.setProjectionMatrix(stage.getViewport().getCamera().combined);

        game.batch.begin();

        game.batch.draw(Assets.backgroundMainMenu, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());

        game.batch.end();

        System.out.println(teste);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        teste += 1;
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
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
