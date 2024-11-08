package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static TextureAtlas buttonAtlas;
    public static TextureAtlas deadlineAtlas;

    // Planos de fundo
    public static Texture backgroundCutsceneTeste;
    public static Texture backgroundMainMenu;
    public static Texture backgroundQuarto;
    public static Texture backgroundFacul;
    public static Texture backgroundSala;
    public static Texture backgroundBiblioteca;
    public static Texture backgroundTeatro;
    public static Texture backgroundGinasio;
    public static Texture backgroundRu;
    public static Texture backgroundChegada;
    public static Texture backgroundSaida;
    public static Texture backgroundMapa;

    public static Texture barraVida;
    // Sprites do Davi
    public static TextureRegion daviNeutro;
    public static TextureRegion daviRaiva;
    public static TextureRegion daviTriste;
    public static TextureRegion daviFeliz;
    public static TextureRegion daviMedo;
    public static TextureRegion daviFala;
    public static TextureRegion daviTiro;

    // Sprites da Inseguranca
    public static TextureRegion insegurancaNormal;
    public static TextureRegion insegurancaDano;
    public static TextureRegion insegurancaTiro;

    // Sprites da Juca
    public static TextureRegion jucaNeutro;
    public static TextureRegion jucaRaiva;
    public static TextureRegion jucaTriste;
    public static TextureRegion jucaFeliz;
    public static TextureRegion jucaFala;
    public static TextureRegion jucaTiro;

    // Sprites do Yuri
    public static TextureRegion yuriNeutro;
    public static TextureRegion yuriRaiva;
    public static TextureRegion yuriTriste;
    public static TextureRegion yuriFeliz;
    public static TextureRegion yuriFala;
    public static TextureRegion yuriTiro;

    // Sprites do Thales
    public static TextureRegion thalesNeutro;
    public static TextureRegion thalesRaiva;
    public static TextureRegion thalesTriste;
    public static TextureRegion thalesFeliz;
    public static TextureRegion thalesFala;
    public static TextureRegion thalesTiro;

    // Sprite do Professor
    public static TextureRegion professorTexture;

    // Icones da UI
    public static TextureRegion configIcon;
    public static TextureRegion studyIcon;
    public static TextureRegion mapIcon;
    public static TextureRegion exitIcon;
    public static TextureRegion exclaimIcon;


    public static void load() {
        buttonAtlas = new TextureAtlas(Gdx.files.local("buttons/buttons.pack"));
        deadlineAtlas = new TextureAtlas(Gdx.files.local("DeadLineAtlas/DeadlineAtlas.atlas"));

        // Carregando Texturas dos Planos de Fundo
        backgroundMainMenu = new Texture(Gdx.files.local("backgrounds/espiral.png"));
        backgroundCutsceneTeste = new Texture(Gdx.files.local("backgrounds/quarto.png"));
        backgroundQuarto = new Texture(Gdx.files.local("backgrounds/quarto.png"));
        backgroundFacul = new Texture(Gdx.files.local("backgrounds/facul.jpg"));
        backgroundSala = new Texture(Gdx.files.local("backgrounds/sala.jpg"));
        backgroundBiblioteca = new Texture(Gdx.files.local("backgrounds/biblioteca.jpg"));
        backgroundTeatro = new Texture(Gdx.files.local("backgrounds/teatro.jpg"));
        backgroundGinasio = new Texture(Gdx.files.local("backgrounds/ginasio.jpg"));
        backgroundRu = new Texture(Gdx.files.local("backgrounds/ru.png"));
        backgroundChegada = new Texture(Gdx.files.local("backgrounds/chegada.jpg"));
        backgroundSaida = new Texture(Gdx.files.local("backgrounds/saida.jpg"));
        backgroundMapa = new Texture(Gdx.files.local("backgrounds/mapa.png"));

        barraVida = new Texture("Barra_Vida.png");

        // Carregando Texturas dos Personagens
        daviNeutro = deadlineAtlas.findRegion("Davi_neutro");
        daviRaiva = deadlineAtlas.findRegion("Davi_raiva");
        daviTriste = deadlineAtlas.findRegion("Davi_triste");
        daviFeliz = deadlineAtlas.findRegion("Davi_feliz");
        daviMedo = deadlineAtlas.findRegion("Davi_medo");
        daviFala = deadlineAtlas.findRegion("Davi_fala");
        daviTiro = deadlineAtlas.findRegion("Davi_tiro");

        insegurancaNormal = deadlineAtlas.findRegion("Inseguranca_normal");
        insegurancaDano = deadlineAtlas.findRegion("Inseguranca_dano");
        insegurancaTiro = deadlineAtlas.findRegion("Inseguranca_tiro");

        jucaNeutro = deadlineAtlas.findRegion("Juca_neutro");
        jucaRaiva = deadlineAtlas.findRegion("Juca_raiva");
        jucaTriste = deadlineAtlas.findRegion("Juca_triste");
        jucaFeliz = deadlineAtlas.findRegion("Juca_feliz");
        jucaFala = deadlineAtlas.findRegion("Juca_fala");
        jucaTiro = deadlineAtlas.findRegion("Juca_tiro");

        yuriNeutro = deadlineAtlas.findRegion("Yuri_neutro");
        yuriRaiva = deadlineAtlas.findRegion("Yuri_raiva");
        yuriTriste = deadlineAtlas.findRegion("Yuri_triste");
        yuriFeliz = deadlineAtlas.findRegion("Yuri_feliz");
        yuriFala = deadlineAtlas.findRegion("Yuri_fala");
        yuriTiro = deadlineAtlas.findRegion("Yuri_tiro");

        thalesNeutro = deadlineAtlas.findRegion("Thales_neutro");
        thalesRaiva = deadlineAtlas.findRegion("Thales_raiva");
        thalesTriste = deadlineAtlas.findRegion("Thales_triste");
        thalesFeliz = deadlineAtlas.findRegion("Thales_feliz");
        thalesFala = deadlineAtlas.findRegion("Thales_fala");
        thalesTiro = deadlineAtlas.findRegion("Thales_tiro");

        professorTexture = deadlineAtlas.findRegion("Professor");

        // Carregando textura dos icones da UI
        configIcon = deadlineAtlas.findRegion("Config_icon");
        studyIcon = deadlineAtlas.findRegion("Study_icon");
        mapIcon = deadlineAtlas.findRegion("Map_icon");
        exitIcon = deadlineAtlas.findRegion("Exit_icon");
        exclaimIcon = deadlineAtlas.findRegion("Exclaim_icon");
    }

    public static void dispose(){
        buttonAtlas.dispose();
        backgroundMainMenu.dispose();
        backgroundCutsceneTeste.dispose();
        backgroundQuarto.dispose();
        backgroundFacul.dispose();
        backgroundSala.dispose();
        backgroundBiblioteca.dispose();
        backgroundTeatro.dispose();
        backgroundGinasio.dispose();
        backgroundRu.dispose();
        backgroundChegada.dispose();
        backgroundSaida.dispose();
        backgroundMapa.dispose();
        barraVida.dispose();
        deadlineAtlas.dispose();
    }
}
