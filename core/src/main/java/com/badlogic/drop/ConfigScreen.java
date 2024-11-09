package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class ConfigScreen implements Screen {
    final DeadLine game;
    DeadLine.ScreenKey myKey;
    Stage stage;
    Image exitButton;

    TextButton somBaixo;
    TextButton somMedio;
    TextButton somAlto;
    TextButton musicaBaixa;
    TextButton musicaMedia;
    TextButton musicaAlta;

    Sound teste;

    private DeadLine.ScreenKey previousScreen; // Atributo usado para voltar para a tela certa ao sair das configs

    public ConfigScreen(final DeadLine game, DeadLine.ScreenKey myKey){
        this.game = game;
        this.myKey = myKey;
        stage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT));

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        Table exitTable = new Table();
        exitTable.setFillParent(true);
        exitTable.top().right();

        // Botoes para ajustar o volume dos SFX do jogo
        CriarBotao botao4 = new CriarBotao("BAIXO", Assets.buttonAtlas);
        somBaixo = botao4.getBotao();
        CriarBotao botao5 = new CriarBotao("MEDIO", Assets.buttonAtlas);
        somMedio = botao5.getBotao();
        CriarBotao botao6 = new CriarBotao("ALTO", Assets.buttonAtlas);
        somAlto = botao6.getBotao();

        // Botoes para ajustar o volume da Musica do jogo
        CriarBotao botao7 = new CriarBotao("BAIXA", Assets.buttonAtlas);
        musicaBaixa = botao7.getBotao();
        CriarBotao botao8 = new CriarBotao("MEDIA", Assets.buttonAtlas);
        musicaMedia = botao8.getBotao();
        CriarBotao botao9 = new CriarBotao("ALTA", Assets.buttonAtlas);
        musicaAlta = botao9.getBotao();

        // "Botao" para voltar para a tela que abriu as configuracoes
        exitButton = new Image(Assets.exitIcon);

        configuraBotoes();

        Label fonteLabel = new Label("Tamanho da Fonte", new Label.LabelStyle(game.font, Color.WHITE));
        Label somLabel = new Label("Volume dos Efeitos Sonoros", new Label.LabelStyle(game.font, Color.WHITE));
        Label musicaLabel = new Label("Volume da Musica", new Label.LabelStyle(game.font, Color.WHITE));


        exitTable.add(exitButton).width(50).height(50);

        //table.add(somLabel).row();
        table.add(somBaixo).padBottom(25);
        table.add(somMedio).padBottom(25);
        table.add(somAlto).padBottom(25).row();
        //table.add(musicaLabel).row();
        table.add(musicaBaixa);
        table.add(musicaMedia);
        table.add(musicaAlta);

        //table.setDebug(true);
        stage.addActor(exitTable);
        stage.addActor(table);

        teste = Gdx.audio.newSound(Gdx.files.internal("tiro.mp3"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        stage.getViewport().apply();

        stage.act(delta);
        stage.draw();
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

    // Trecho muito grande de codigo. Deixaria o construtor lotado de coisa, entao vou fazer separado
    public void configuraBotoes(){
        somBaixo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.volumeSom = 0.1f;
                teste.play(Settings.volumeSom);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                somBaixo.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("hover-button"));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                somBaixo.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("botao-normal"));
            }
        });

        somMedio.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.volumeSom = 0.3f;
                teste.play(Settings.volumeSom);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                somMedio.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("hover-button"));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                somMedio.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("botao-normal"));
            }
        });

        somAlto.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.volumeSom = 1f;
                teste.play(Settings.volumeSom);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                somAlto.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("hover-button"));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                somAlto.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("botao-normal"));
            }
        });


        musicaBaixa.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.volumeMusica = 0.1f;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                musicaBaixa.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("hover-button"));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                musicaBaixa.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("botao-normal"));
            }
        });

        musicaMedia.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.volumeMusica = 0.3f;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                musicaMedia.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("hover-button"));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                musicaMedia.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("botao-normal"));
            }
        });

        musicaAlta.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.volumeMusica = 1f;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                musicaAlta.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("hover-button"));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                musicaAlta.getStyle().up = new TextureRegionDrawable(Assets.buttonAtlas.findRegion("botao-normal"));
            }
        });



        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(previousScreen);
            }
        });
    }

    public void setPreviousScreen(DeadLine.ScreenKey previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
