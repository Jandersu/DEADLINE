package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MapScreen implements Screen {
    final DeadLine game;
    DeadLine.ScreenKey myKey;
    Stage stage;

    Image daviFacul;
    Image daviBiblioteca;
    Image daviTeatro;
    Image daviGinasio;
    Image daviRU;
    Image mapButton;
    Image configButton;
    Image faculButton;
    Image bibliotecaButton;
    Image teatroButton;
    Image ginasioButton;
    Image ruButton;

    private DeadLine.ScreenKey previousScreen;

    public MapScreen(DeadLine game, DeadLine.ScreenKey myKey) {
        this.game = game;
        this.myKey = myKey;
        stage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT));

        Table table = new Table();
        table.setFillParent(true);
        table.top().right();

        daviFacul = new Image(Assets.daviNeutro);
        daviBiblioteca = new Image(Assets.daviNeutro);;
        daviTeatro = new Image(Assets.daviNeutro);;
        daviGinasio = new Image(Assets.daviNeutro);;
        daviRU = new Image(Assets.daviNeutro);;
        mapButton = new Image(Assets.mapIcon);
        configButton = new Image(Assets.configIcon);
        faculButton = new Image(Assets.pointIcon);
        bibliotecaButton = new Image(Assets.pointIcon);
        teatroButton = new Image(Assets.pointIcon);
        ginasioButton = new Image(Assets.pointIcon);
        ruButton = new Image(Assets.pointIcon);

        table.add(configButton).width(50).height(50);
        table.add(mapButton).width(50).height(50);

        /*daviBiblioteca.setVisible(false);
        daviTeatro.setVisible(false);
        daviGinasio.setVisible(false);
        daviRU.setVisible(false);*/

        configuraBotoes();

        stage.addActor(daviFacul);
        stage.addActor(faculButton);
        daviFacul.setPosition(100,400);
        faculButton.setPosition(132, 400);

        stage.addActor(daviBiblioteca);
        daviBiblioteca.setVisible(false);
        stage.addActor(bibliotecaButton);
        daviBiblioteca.setPosition(200,100);
        bibliotecaButton.setPosition(232, 100);

        stage.addActor(daviTeatro);
        daviTeatro.setVisible(false);
        stage.addActor(teatroButton);
        daviTeatro.setPosition(595, 45);
        teatroButton.setPosition(627, 45);

        stage.addActor(daviGinasio);
        daviGinasio.setVisible(false);
        stage.addActor(ginasioButton);
        daviGinasio.setPosition(440, 420);
        ginasioButton.setPosition(472, 420);

        stage.addActor(daviRU);
        daviRU.setVisible(false);
        stage.addActor(ruButton);
        daviRU.setPosition(690, 300);
        ruButton.setPosition(722, 300);

        stage.addActor(table);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        game.musicaPrincipal.play();
    }

    @Override
    public void render(float delta) {
        game.musicaPrincipal.setVolume(Settings.volumeMusica);
        ScreenUtils.clear(Color.BLACK);
        stage.getViewport().apply();
        game.batch.setProjectionMatrix(stage.getViewport().getCamera().combined);

        game.batch.begin();
        game.batch.draw(Assets.backgroundMapa, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        game.batch.end();

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

    public void configuraBotoes(){
        mapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(previousScreen);
            }
        });
        configButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getConfigScreen().setPreviousScreen(myKey);
                game.setScreen(DeadLine.ScreenKey.Config);
            }
        });
        faculButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                daviFacul.setVisible(true);
                daviBiblioteca.setVisible(false);
                daviTeatro.setVisible(false);
                daviGinasio.setVisible(false);
                daviRU.setVisible(false);
                game.setScreen(DeadLine.ScreenKey.Hub);
            }
        });
        bibliotecaButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                daviFacul.setVisible(false);
                daviBiblioteca.setVisible(true);
                daviTeatro.setVisible(false);
                daviGinasio.setVisible(false);
                daviRU.setVisible(false);
                game.setScreen(DeadLine.ScreenKey.telaJuca);
            }
        });
        teatroButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                daviFacul.setVisible(false);
                daviBiblioteca.setVisible(false);
                daviTeatro.setVisible(true);
                daviGinasio.setVisible(false);
                daviRU.setVisible(false);
                game.setScreen(DeadLine.ScreenKey.telaYuri);
            }
        });
        ginasioButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                daviFacul.setVisible(false);
                daviBiblioteca.setVisible(false);
                daviTeatro.setVisible(false);
                daviGinasio.setVisible(true);
                daviRU.setVisible(false);
                game.setScreen(DeadLine.ScreenKey.telaThales);
            }
        });
        ruButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                daviFacul.setVisible(false);
                daviBiblioteca.setVisible(false);
                daviTeatro.setVisible(false);
                daviGinasio.setVisible(false);
                daviRU.setVisible(true);

                game.musicaPrincipal.pause();
                game.setScreen(new MinigameScreen(game));
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
