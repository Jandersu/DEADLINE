package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Combate implements Screen{
    final DeadLine game;
    Texture backgroundTexture;
    Texture daviTexture;
    Texture insegurancaTexture;
    Texture backgroundTexture2;
    Texture barraVida;
    Texture insegurancaLagrima;
    Texture daviTiroTexture;
    Sound danoDaviSound;
    Sound tiroSound;
    Sound danoSound;
    Music music;
    Sprite daviSprite;
    Sprite insegurancaSprite;
    Texture insegurancaTextureDano;
    Sprite daviTiroSprite;
    Vector2 touchPos;
    Array<Sprite> lagrimaSprites;
    Array<Sprite> tiroSprites;

    float fichaTimer;
    float danoTimer;
    float danoTimerDavi;
    boolean tomouDano;
    boolean tomouDanoDavi;

    Rectangle daviRectangle;
    Rectangle fichaRectangle;
    Rectangle insegurancaRectangle;
    Rectangle tiroRectangle;

    int vida = 1;
    int vidaInseguranca = 100;
    BitmapFont font;

    Stage stage;
    TextureAtlas buttonAtlas;

    public Combate(final DeadLine game){

        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        buttonAtlas = new TextureAtlas(Gdx.files.local("buttons/buttons.pack"));

        //backgroundTexture = new Texture("background.png");
        barraVida = new Texture("barravida.png");

        daviTexture = new Texture("DAVI/davi_medo.png");
        insegurancaTexture = new Texture("Inseguranca_normal.png");
        daviTiroTexture = new Texture("Davi_tiro.png");
        insegurancaTextureDano = new Texture("Inseguranca_dano.png");

        backgroundTexture2 = new Texture("rupixel.png");
        insegurancaLagrima = new Texture("inseguranca_tiro.png");

        danoDaviSound = Gdx.audio.newSound(Gdx.files.internal("danoDavi.mp3"));
        tiroSound = Gdx.audio.newSound(Gdx.files.internal("tiro.mp3"));
        danoSound = Gdx.audio.newSound(Gdx.files.internal("dano.mp3"));

        music = Gdx.audio.newMusic(Gdx.files.internal("scopofobia.mp3"));

        daviSprite = new Sprite(daviTexture); // initialize the sprite based on the texture
        daviSprite.setSize(1, 1); // define the size of the sprite

        insegurancaSprite = new Sprite(insegurancaTexture);
        insegurancaSprite.setSize(3,3);

        touchPos = new Vector2();
        lagrimaSprites = new Array<>();
        tiroSprites = new Array<>();

        daviRectangle = new Rectangle();
        insegurancaRectangle = new Rectangle();
        fichaRectangle = new Rectangle();
        tiroRectangle  = new Rectangle();

        music.setLooping(true);
        music.setVolume(.3f);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.setUseIntegerPositions(false);
        font.getData().setScale(game.viewport.getWorldHeight()/Gdx.graphics.getHeight()+0.002f);
    }

    @Override
    public void show(){
        music.play();
    }

    @Override
    public void render(float delta) {
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
        }else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            // Do something when the user presses the right arrow
            daviSprite.translateX(speed * delta); // Move Davi right
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            // Do something when the user presses the left arrow
            daviSprite.translateX(-speed * delta); // Move Davi left
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            createTiro();
            tiroSound.play(.3f);// Create a ficha
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    private void logic(){
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();
        float daviWidth = daviSprite.getWidth();
        float daviHeight = daviSprite.getHeight();
        float delta = Gdx.graphics.getDeltaTime();

        float insegurancaWidth = insegurancaSprite.getWidth();
        float insegurancaHeight = insegurancaSprite.getHeight();

        daviSprite.setX(MathUtils.clamp(daviSprite.getX(),0,worldWidth - daviWidth));
        daviSprite.setY(MathUtils.clamp(daviSprite.getY(),0,worldHeight - daviHeight));

        daviRectangle.set(daviSprite.getX(), daviSprite.getY(),daviWidth, daviHeight);
        insegurancaRectangle.set(insegurancaSprite.getX(), insegurancaSprite.getY() + 2.5f ,insegurancaWidth, insegurancaHeight);

        for (int i = lagrimaSprites.size - 1; i >= 0; i--) {
            Sprite fichaSprite = lagrimaSprites.get(i);
            fichaSprite.translateY(-2f * delta);
            fichaRectangle.set(fichaSprite.getX(), fichaSprite.getY(), fichaSprite.getWidth(), fichaSprite.getHeight());

            if (fichaSprite.getY() < -fichaSprite.getHeight()) {
                lagrimaSprites.removeIndex(i);
            } else if (daviRectangle.overlaps(fichaRectangle)) { // Checar colisão
                lagrimaSprites.removeIndex(i);
                tomouDanoDavi = true;
                danoTimerDavi = 0f;
                daviSprite.setColor(Color.RED);
                danoDaviSound.play(.3f);
                vida -= 1;
                if(vida <= 0){
                    game.setScreen(new GameOverScreen(game));
                    dispose();
                }
            }
        }

        for (int i = tiroSprites.size - 1; i >= 0; i--) {
            Sprite tiroSprite = tiroSprites.get(i);
            tiroSprite.translateY(2f * delta);
            tiroRectangle.set(tiroSprite.getX(), tiroSprite.getY(), tiroSprite.getWidth(), tiroSprite.getHeight());

            if (tiroSprite.getY() > worldHeight) {
                tiroSprites.removeIndex(i);
                //dropSound.play(.3f);
            } else if (insegurancaRectangle.overlaps(tiroRectangle)) { // Checar colisão
                tiroSprites.removeIndex(i);
                tomouDano = true;
                danoTimer = 0f;
                insegurancaSprite.setTexture(insegurancaTextureDano);
                vidaInseguranca -= 1;
                if(vidaInseguranca<100)
                    vidaInseguranca = 0;
                danoSound.play(.3f);
            }
        }

        if(tomouDano){
            danoTimer += delta;
            if (danoTimer > 0.5f) {
                insegurancaSprite.setTexture(insegurancaTexture);
                tomouDano = false;
            }
        }

        if(tomouDanoDavi){
            danoTimerDavi += delta;
            if (danoTimerDavi > 0.5f) {
                daviSprite.setColor(Color.WHITE);
                tomouDanoDavi = false;
            }
        }

        fichaTimer += delta;
        if(fichaTimer > 1f){ // Check if it has been more than a second
            fichaTimer = 0; // Reset the timer
            createLagrima(); // Create a ficha
        }
    }

    private void draw(){
        ScreenUtils.clear(Color.GRAY);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        // draw stuff in here
        // 100 pixels = 1 meter
        // the drawing order is the code order

        //game.batch.draw(backgroundTexture2, 0, 0, worldWidth, worldHeight); // draw the background
        daviSprite.draw(game.batch);
        //daviSprite.setPosition(4,0);
        insegurancaSprite.draw(game.batch);
        insegurancaSprite.setPosition(worldWidth/2 - 1.4f,worldHeight-3);

        // desenhando fichas
        for(Sprite lagrimaSprite : lagrimaSprites){
            lagrimaSprite.draw(game.batch);
        }

        for(Sprite tiroSprite : tiroSprites){
            tiroSprite.draw(game.batch);
        }

        float barWidth = 2f;
        float barHeight = 0.2f;
        float percentualVida = vida / 10f;
        game.batch.draw(barraVida, 0.5f, worldHeight-2, barWidth * percentualVida, barHeight);
        font.draw(game.batch, "VIDA:  ", 0.05f, worldHeight - 1.80f);

        game.batch.end();
    }

    private void createLagrima(){
        float lagrimaWidth = 1;
        float lagrimaHeight = 1;
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        // criando sprite da ficha
        Sprite lagrimaSprite = new Sprite(insegurancaLagrima);
        lagrimaSprite.setSize(lagrimaWidth, lagrimaHeight);
        lagrimaSprite.setX(MathUtils.random(0f, worldWidth - lagrimaWidth)); // randomizando posicao da ficha
        lagrimaSprite.setY(worldHeight);
        lagrimaSprites.add(lagrimaSprite); // colocar sprite na lista
    }

    private void createTiro() {
        float tiroWidth = 1;
        float tiroHeight = 1;
        //float worldWidth = game.viewport.getWorldWidth();
        //float worldHeight = game.viewport.getWorldHeight();

        // criando sprite da ficha
        daviTiroSprite = new Sprite(daviTiroTexture);
        daviTiroSprite.setSize(tiroWidth, tiroHeight);
        daviTiroSprite.setX(daviSprite.getX());
        daviTiroSprite.setY(daviSprite.getY()+1);
        tiroSprites.add(daviTiroSprite); // colocar sprite na lista
    }


    @Override
    public void resize(int width, int height){
        game.viewport.update(width, height, true);
    }

    @Override
    public void hide(){
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
        danoDaviSound.dispose();
        music.dispose();
        tiroSound.dispose();
        danoSound.dispose();
        tiroSound.dispose();
        insegurancaTextureDano.dispose();
        daviTexture.dispose();
        insegurancaTexture.dispose();
        backgroundTexture2.dispose();
        insegurancaLagrima.dispose();
        insegurancaTextureDano.dispose();
        barraVida.dispose();
        insegurancaLagrima.dispose();
        daviTiroTexture.dispose();
        stage.dispose();
    }
}
