package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MinigameScreen implements Screen {
    final DeadLine game;

    Image mapButton;
    Sound dropSound;
    Sound fichaBoaSound;
    Music music;
    Sprite daviSprite;
    Vector2 touchPos;
    Array<Sprite> fichaSprites;
    Array<Sprite> fichaBoaSprites;

    float fichaTimer;
    float fichaTimer2;

    Rectangle daviRectangle;
    Rectangle fichaRectangle;

    int score = 0;
    BitmapFont font;

    Stage stage;
    TextureAtlas buttonAtlas;

    public MinigameScreen(final DeadLine game){

        this.game = game;

        stage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT));
        Gdx.input.setInputProcessor(stage);
        buttonAtlas = new TextureAtlas(Gdx.files.local("buttons/buttons.pack"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds-musics/drop.mp3"));
        fichaBoaSound = Gdx.audio.newSound(Gdx.files.internal("sounds-musics/rupee.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds-musics/musicru.mp3"));

        daviSprite = new Sprite(Assets.daviNeutro); // initialize the sprite based on the texture
        daviSprite.setSize(1, 1); // define the size of the sprite

        touchPos = new Vector2();
        fichaSprites = new Array<>();
        fichaBoaSprites = new Array<>();

        daviRectangle = new Rectangle();
        fichaRectangle = new Rectangle();

        music.setLooping(true);
        music.setVolume(.3f);

        font = new BitmapFont();
        font.setColor(Color.YELLOW);
        font.setUseIntegerPositions(false);
        font.getData().setScale(game.viewport.getWorldHeight()/Gdx.graphics.getHeight()+0.001f);

        mapButton = new Image(Assets.mapIcon);

        mapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(DeadLine.ScreenKey.Map);
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.top().right();
        table.add(mapButton).width(50).height(50);
        stage.addActor(table);
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
        music.play();
    }

    @Override
    public void render(float delta) {
        // Draw your application here.
        music.setVolume(Settings.volumeMusica);
        input();
        logic();
        draw(delta);
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
        }

    }

    private void logic(){
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();
        float daviWidth = daviSprite.getWidth();
        float daviHeight = daviSprite.getHeight();
        float delta = Gdx.graphics.getDeltaTime();

        daviSprite.setX(MathUtils.clamp(daviSprite.getX(),0,worldWidth - daviWidth));
        daviSprite.setY(MathUtils.clamp(daviSprite.getY(),0,worldHeight - daviHeight));

        daviRectangle.set(daviSprite.getX(), daviSprite.getY(),daviWidth, daviHeight);

        for (int i = fichaSprites.size - 1; i >= 0; i--) {
            Sprite fichaSprite = fichaSprites.get(i);
            fichaSprite.translateY(-2f * delta);
            fichaRectangle.set(fichaSprite.getX(), fichaSprite.getY(), fichaSprite.getWidth(), fichaSprite.getHeight());

            if (fichaSprite.getY() < -fichaSprite.getHeight()) {
                fichaSprites.removeIndex(i);
            } else if (daviRectangle.overlaps(fichaRectangle)) { // Checar colisão
                fichaSprites.removeIndex(i);

                // Diferenciar com base no tamanho da ficha
                if (fichaSprite.getWidth() > 1) {  // Supondo que fichas boas são maiores
                    fichaBoaSound.play(Settings.volumeSom);
                    addScore(1); // Incrementa o placar para fichas boas
                } else {
                    dropSound.play(Settings.volumeSom);
                    addScore(-1); // Reduz o placar para fichas normais
                }
            }
        }

        fichaTimer += delta;
        fichaTimer2 += delta;
        if(fichaTimer > 1f){ // Check if it has been more than a second
            fichaTimer = 0; // Reset the timer
            createFicha(); // Create a ficha
        }
        if(fichaTimer2 > 4f){ // Check if it has been more than a second
            fichaTimer2 = 0; // Reset the timer
            createFichaBoa(); // Create a ficha
        }
    }

    private void draw(float delta){
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        // draw stuff in here
        // 100 pixels = 1 meter
        // the drawing order is the code order
        game.batch.draw(Assets.backgroundRu, 0, 0, worldWidth, worldHeight); // draw the background
        daviSprite.draw(game.batch);

        // desenhando fichas
        for(Sprite fichaSprite : fichaSprites){
            fichaSprite.draw(game.batch);
        }

        font.draw(game.batch, "SCORE:  " + score, 7, 4);

        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    private void createFicha(){
        float fichaWidth = 1;
        float fichaHeight = 1;
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        // criando sprite da ficha
        Sprite fichaSprite = new Sprite(Assets.ficha15);
        fichaSprite.setSize(fichaWidth, fichaHeight);
        fichaSprite.setX(MathUtils.random(0f, worldWidth - fichaWidth)); // randomizando posicao da ficha
        fichaSprite.setY(worldHeight);
        fichaSprites.add(fichaSprite); // colocar sprite na lista
    }

    private void createFichaBoa(){
        float fichaWidth = 1.01f;
        float fichaHeight = 1;
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        Sprite fichaBoaSprite = new Sprite(Assets.ficha3);
        fichaBoaSprite.setSize(fichaWidth, fichaHeight);
        fichaBoaSprite.setX(MathUtils.random(0f, worldWidth - fichaWidth));
        fichaBoaSprite.setY(worldHeight);
        fichaSprites.add(fichaBoaSprite);
    }

    public void addScore(int valor){
        score += valor;
    }

    @Override
    public void resize(int width, int height){
        game.viewport.update(width, height, true);
    }

    @Override
    public void hide(){
        music.stop();
        Gdx.input.setInputProcessor(null);
        dispose();
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
        dropSound.dispose();
        music.dispose();
        fichaBoaSound.dispose();
        stage.dispose();
    }
}
