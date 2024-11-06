package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class DeadLine extends Game {
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    private  MainMenuScreen mainMenuScreen;
    /*private CutsceneScreen cutsceneScreen;
    private Combate combate;*/
    public BitmapFont font;
    public FitViewport viewport;
    public SpriteBatch batch;


    @Override
    public void create() {
        Assets.load();
        font = new BitmapFont();
        viewport = new FitViewport(8, 5);
        batch = new SpriteBatch();
        // Ajustando a fonte para as dimensoes do viewport
        font.setUseIntegerPositions(false);
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());
        mainMenuScreen = new MainMenuScreen(this);

        setScreen(mainMenuScreen);
    }

    @Override
    public void dispose(){
        Assets.dispose();
        mainMenuScreen.dispose();
    }
}
