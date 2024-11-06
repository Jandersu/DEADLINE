package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static TextureAtlas buttonAtlas;
    public static Texture backgroundCutsceneTeste;
    public static Texture backgroundMainMenu;

    public static void load() {
        buttonAtlas = new TextureAtlas(Gdx.files.local("buttons/buttons.pack"));
        backgroundMainMenu = new Texture(Gdx.files.local("espiral.png"));
        backgroundCutsceneTeste = new Texture(Gdx.files.local("quarto.png"));
    }

    public static void dispose(){
        buttonAtlas.dispose();
        backgroundMainMenu.dispose();
        backgroundCutsceneTeste.dispose();
    }
}
