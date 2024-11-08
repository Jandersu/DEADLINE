package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HubScreen implements Screen {
    final DeadLine game;
    Stage stage;
    boolean bobo = false;
    float timer;

    Image davi;
    Image mapButton;
    Image studyButton;
    Image configButton;
    Image exitButton;

    public HubScreen(final DeadLine game){
        this.game = game;
        stage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT));

        // Tabela que adiciona o Davi na tela
        Table table = new Table();
        table.setFillParent(true);
        table.left().bottom();
        table.setDebug(true); // Debug pra ver as celulas da tabela

        davi = new Image(Assets.daviNeutro);
        table.add(davi).width(150).height(150);

        // Configurando um charme pro Davi. Bobinho
        davi.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                swapTextureRegion(davi, Assets.daviRaiva);
                bobo = true;
            }
        });

        // Tabela dos botoes do hub. Vou usar imagens para detectar os clicks porque e mais facil de montar
        Table buttonTable = new Table();
        buttonTable.setFillParent(true);
        buttonTable.right().top();
        buttonTable.setDebug(true);

        mapButton = new Image(Assets.mapIcon);
        configButton = new Image(Assets.configIcon);
        studyButton = new Image(Assets.studyIcon);
        exitButton = new Image(Assets.exitIcon);

        buttonTable.add(mapButton).width(100).height(100).expandY();
        buttonTable.row();
        buttonTable.add(studyButton).width(100).height(100).expandY();
        buttonTable.row();
        buttonTable.add(configButton).width(100).height(100).expandY();
        buttonTable.row();
        buttonTable.add(exitButton).width(100).height(100).expandY();

        // Configurando os "botoes"
        mapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Mapa");
            }
        });
        studyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Estudar");
                game.setScreen(new Combate(game));
            }
        });
        configButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Configuracoes");
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(DeadLine.ScreenKey.MainMenu);
            }
        });


        stage.addActor(table);
        stage.addActor(buttonTable);
    }

    public void swapTextureRegion(Image image, TextureRegion newTexture) {
        image.setDrawable(new SpriteDrawable(new Sprite(newTexture)));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        logic(delta);
        ScreenUtils.clear(Color.BLACK);
        stage.getViewport().apply();
        game.batch.setProjectionMatrix(stage.getViewport().getCamera().combined);

        game.batch.begin();
        game.batch.draw(Assets.backgroundFacul, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        //game.batch.draw(Assets.daviNeutro, 0, 0, 150, 150); // Adiciona o Davi só como uma textura
        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    public void logic(float delta){
        timer += delta;

        if(bobo){
            if(timer > 2f) {
                timer = 0;
                swapTextureRegion(davi, Assets.daviNeutro);
                bobo = false;
            }
        }
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
