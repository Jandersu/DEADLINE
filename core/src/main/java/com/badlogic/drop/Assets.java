package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static TextureAtlas buttonAtlas;
    public static TextureAtlas deadlineAtlas;
    public static Texture backgroundCutsceneTeste;
    public static Texture backgroundMainMenu;
    public static TextureAtlas.AtlasRegion daviRegion;
    public static TextureRegion jucaRegion;
    static Texture jucaNeutro;
    private Texture raiva;
    private Texture triste;
    private Texture feliz;
    private Texture fala;
    private Texture medo;
    private Texture tiro;

    public static void load() {
        buttonAtlas = new TextureAtlas(Gdx.files.local("buttons/buttons.pack"));
        deadlineAtlas = new TextureAtlas(Gdx.files.local("DeadlineAtlas.atlas"));

        backgroundMainMenu = new Texture(Gdx.files.local("espiral.png"));
        backgroundCutsceneTeste = new Texture(Gdx.files.local("quarto.png"));
        daviRegion = deadlineAtlas.findRegion("Davi_medo");
        jucaRegion = deadlineAtlas.findRegion("Juca_neutro");
    }

    public static void dispose(){
        buttonAtlas.dispose();
        backgroundMainMenu.dispose();
        backgroundCutsceneTeste.dispose();
        deadlineAtlas.dispose();
    }
}
