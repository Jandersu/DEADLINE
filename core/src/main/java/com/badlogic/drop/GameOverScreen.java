package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
    final DeadLine game;
    BitmapFont font;
    BitmapFont font2;;
    Sprite insegurancaSprite;

    public GameOverScreen(DeadLine game){
        this.game = game;

        font = new BitmapFont();
        font.setColor(Color.RED);
        font.setUseIntegerPositions(false);
        font.getData().setScale(game.viewport.getWorldHeight()/ Gdx.graphics.getHeight()+0.005f);

        font2 = new BitmapFont();
        font2.setColor(Color.WHITE);
        font2.setUseIntegerPositions(false);
        font2.getData().setScale(game.viewport.getWorldHeight()/ Gdx.graphics.getHeight()+0.003f);

        insegurancaSprite = new Sprite(Assets.insegurancaNormal);
        insegurancaSprite.setSize(3,3);
    }

    @Override
    public void show() {
        game.musicaGameOver.play();
    }

    @Override
    public void render(float delta) {
        game.musicaGameOver.setVolume(Settings.volumeMusica);
        ScreenUtils.clear(Color.BLACK);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.musicaInseguranca.stop();
            game.setScreen(DeadLine.ScreenKey.MainMenu);
        }
        game.batch.begin();
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        insegurancaSprite.draw(game.batch);
        insegurancaSprite.setPosition(worldWidth/2 - 1.4f,worldHeight-3);
        font.draw(game.batch, "GAME OVER", 3.4f, worldHeight - 2f);
        font2.draw(game.batch, "Pressione ESPAÇO para \n       voltar ao Menu", 3.1f, worldHeight - 2.5f);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        game.musicaGameOver.stop();
    }

    @Override
    public void dispose() {
        font.dispose();
        font2.dispose();
    }
}
