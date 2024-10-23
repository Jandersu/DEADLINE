package com.badlogic.drop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    Texture backgroundTexture;
    Texture bucketTexture;
    Texture dropTexture;
    Texture daviTexture;
    Texture backgroundTexture2;
    Texture fichaTexture;
    Texture fichaBoaTexture;
    Sound dropSound;
    Music music;

    Sprite daviSprite;

    Vector2 touchPos;

    SpriteBatch spriteBatch;
    FitViewport viewport;

    @Override
    public void create() {
        // Prepare your application here.
        backgroundTexture = new Texture("background.png");
        bucketTexture = new Texture("bucket.png");
        dropTexture = new Texture("drop.png");
        daviTexture = new Texture("davi.png");
        backgroundTexture2 = new Texture("ru.jpg");
        fichaTexture = new Texture("ficharu.png");
        fichaBoaTexture = new Texture("fichaboa.png");
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

        daviSprite = new Sprite(daviTexture); // initialize the sprite based on the texture
        daviSprite.setSize(1, 1); // define the size of the sprite

        touchPos = new Vector2();

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8,5);
    }

    @Override
    public void resize(int width, int height) {
        // Resize your application here. The parameters represent the new window size.
        viewport.update(width, height, true); // true center the camera
    }

    @Override
    public void render() {
        // Draw your application here.
        input();
        logic();
        draw();
    }

    private void input(){
        float speed = 4f;
        float delta = Gdx.graphics.getDeltaTime(); // retrieve the current delta

        // keyboard controls
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            // Do something when the user presses the right arrow
            daviSprite.translateX(speed * delta); // Move Davi right
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            // Do something when the user presses the left arrow
            daviSprite.translateX(-speed * delta); // Move Davi left
        } else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            daviSprite.translateY(speed/2 * delta); // Move Davi up
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            daviSprite.translateY(-speed/2 * delta); // Move Davi up
        }

        // mouse and touch controls
        if(Gdx.input.isTouched()) { // If the user has clicked or tapped the screen
            // React to the player touching the screen
            touchPos.set(Gdx.input.getX(), Gdx.input.getY()); // Get where the touch happened on screen
            viewport.unproject(touchPos); // Convert the units to the world units of the viewport
            daviSprite.setCenterX(touchPos.x);
            daviSprite.setCenterY(touchPos.y);
        }

    }

    private void logic(){
    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        spriteBatch.begin();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        // draw stuff in here
        // 100 pixels = 1 meter
        // the drawing order is the code order
        spriteBatch.draw(backgroundTexture2, 0, 0, worldWidth, worldHeight); // draw the background
        spriteBatch.draw(fichaTexture, 3, 3, 1, 1);
        spriteBatch.draw(fichaBoaTexture, 5, 3, 1, 1);
        daviSprite.draw(spriteBatch);

        spriteBatch.end();
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        // Destroy application's resources here.
    }
}
