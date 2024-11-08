package com.badlogic.drop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    Texture backgroundTexture;
    Texture bucketTexture;
    Texture dropTexture;
    Texture daviTexture;
    Texture backgroundTexture2;
    Texture fichaTexture;
    Texture fichaBoaTexture;
    Sound dropSound;
    Sound fichaBoaSound;
    Music music;

    SpriteBatch spriteBatch;
    FitViewport viewport;

    Sprite daviSprite;
    Vector2 touchPos;
    Array<Sprite> fichaSprites;

    float fichaTimer;
    float fichaTimer2;

    Rectangle daviRectangle;
    Rectangle fichaRectangle;

    @Override
    public void create() {
        // Prepare your application here.
        backgroundTexture = new Texture("background.png");
        bucketTexture = new Texture("bucket.png");
        dropTexture = new Texture("drop.png");
        daviTexture = new Texture("davi.png");
        backgroundTexture2 = new Texture("rupixel.png");
        fichaTexture = new Texture("ficharu.png");
        fichaBoaTexture = new Texture("fichaboa.png");
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        fichaBoaSound = Gdx.audio.newSound(Gdx.files.internal("rupee.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("musicru.mp3"));

        daviSprite = new Sprite(daviTexture); // initialize the sprite based on the texture
        daviSprite.setSize(1, 1); // define the size of the sprite

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8,5);

        touchPos = new Vector2();
        fichaSprites = new Array<>();

        daviRectangle = new Rectangle();
        fichaRectangle = new Rectangle();

        music.setLooping(true);
        music.setVolume(.3f);
        music.play();
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
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            // Do something when the user presses the right arrow
            daviSprite.translateX(speed * delta); // Move Davi right
        } else if(Gdx.input.isKeyPressed(Keys.LEFT)){
            // Do something when the user presses the left arrow
            daviSprite.translateX(-speed * delta); // Move Davi left
        } else if(Gdx.input.isKeyPressed(Keys.UP)){
            daviSprite.translateY(speed/2 * delta); // Move Davi up
        } else if(Gdx.input.isKeyPressed(Keys.DOWN)){
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
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        float daviWidth = daviSprite.getWidth();
        float daviHeight = daviSprite.getHeight();
        float delta = Gdx.graphics.getDeltaTime();

        daviSprite.setX(MathUtils.clamp(daviSprite.getX(),0,worldWidth - daviWidth));
        daviSprite.setY(MathUtils.clamp(daviSprite.getY(),0,worldHeight - daviHeight));

        daviRectangle.set(daviSprite.getX(), daviSprite.getY(),daviWidth, daviHeight);

        for(int i = fichaSprites.size - 1; i>=0; i--){
            Sprite fichaSprite = fichaSprites.get(i);
            float fichaWidth = fichaSprite.getWidth();
            float fichaHeight = fichaSprite.getHeight();

            fichaSprite.translateY(-2f * delta);
            fichaRectangle.set(fichaSprite.getX(), fichaSprite.getY(),fichaWidth, fichaHeight);

            if(fichaSprite.getY() < -fichaHeight){
                fichaSprites.removeIndex(i);
            }else if(daviRectangle.overlaps(fichaRectangle)) { // check if davi overlaps the ficha
                fichaSprites.removeIndex(i);
                fichaBoaSound.play(.3f);
            }
        }

        fichaTimer += delta;
        fichaTimer2 += delta;
        if(fichaTimer > 1.5f){ // Check if it has been more than a second
            fichaTimer = 0; // Reset the timer
            createFicha(); // Create a ficha
        }
        if(fichaTimer2 > 3f){ // Check if it has been more than a second
            fichaTimer2 = 0; // Reset the timer
            createFichaBoa(); // Create a ficha
        }
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
        daviSprite.draw(spriteBatch);

        // desenhando fichas
        for(Sprite fichaSprite : fichaSprites){
            fichaSprite.draw(spriteBatch);
        }

        spriteBatch.end();
    }

    private void createFicha(){
        float fichaWidth = 1;
        float fichaHeight = 1;
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        // criando sprite da ficha
        Sprite fichaSprite = new Sprite(fichaTexture);
        fichaSprite.setSize(fichaWidth, fichaHeight);
        fichaSprite.setX(MathUtils.random(0f, worldWidth - fichaWidth)); // randomizando posicao da ficha
        fichaSprite.setY(worldHeight);
        fichaSprites.add(fichaSprite); // colocar sprite na lista
    }

    private void createFichaBoa(){
        float fichaWidth = 1;
        float fichaHeight = 1;
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        Sprite fichaBoaSprite = new Sprite(fichaBoaTexture);
        fichaBoaSprite.setSize(fichaWidth, fichaHeight);
        fichaBoaSprite.setX(MathUtils.random(0f, worldWidth - fichaWidth));
        fichaBoaSprite.setY(worldHeight);
        fichaSprites.add(fichaBoaSprite);
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
