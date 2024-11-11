package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class DeadLine extends Game {
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;

    // Instanciando as telas do jogo
    private MainMenuScreen mainMenuScreen;
    private CutsceneScreen cutsceneScreen;
    private CutsceneScreen cutsceneScreenOnibus;
    private CutsceneScreen cutsceneScreenJuca;
    private CutsceneScreen cutsceneScreenThales;
    private CutsceneScreen cutsceneScreenYuri;
    private CutsceneScreen cutsceneScreenProfessor;

    private PersonagemTela telaJuca;
    private PersonagemTela telaThales;
    private PersonagemTela telaYuri;
    private PersonagemTela telaProfessor;

    private Combate combateScreen;
    private GameOverScreen gameOverScreen;

    private HubScreen hubScreen;
    //private Combate combatScreen;
    //private GameOverScreen gameOverScreen;
    private ConfigScreen configScreen;
    private MapScreen mapScreen;

    public BitmapFont font;
    public FitViewport viewport;
    public SpriteBatch batch;

    public Music musicaPrincipal; // Musica principal do jogo
    public Music musicaMenu; // Musica que toca na MainMenuScreen
    public Music musicaInseguranca; // Musica que toca quando a Inseguranca aparece, normalmente em combates
    public Music musicaGameOver; // Musica que toca quando a Inseguranca derrota o Davi num combate

    // Variável prefs para controle das preferências de usuário.
    //private Preferences prefs;

    @Override
    public void create() {
        Assets.load();
        Settings.load();
        font = new BitmapFont(Gdx.files.local("fontes/pixel.fnt"));
        viewport = new FitViewport(8, 6);
        batch = new SpriteBatch();
        // Ajustando a fonte para as dimensoes do viewport
        font.setUseIntegerPositions(false);
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

        // Inicializando as telas
        mainMenuScreen = new MainMenuScreen(this, ScreenKey.MainMenu);

        cutsceneScreen = new CutsceneScreen(this, ScreenKey.Cutscene, Dialogos.textoCutsceneInicial, Assets.backgroundCutsceneTeste,0);
        cutsceneScreenOnibus = new CutsceneScreen(this, ScreenKey.Cutscene, Dialogos.textoCutsceneOnibus, Assets.backgroundChegada, 0);
        cutsceneScreenJuca = new CutsceneScreen(this, ScreenKey.Cutscene, Dialogos.getTextoCutsceneJuca, Assets.backgroundBiblioteca, 0);
        cutsceneScreenThales = new CutsceneScreen(this, ScreenKey.Cutscene, Dialogos.getTextoCutsceneThales, Assets.backgroundTeatro, 0);
        cutsceneScreenYuri = new CutsceneScreen(this, ScreenKey.Cutscene, Dialogos.getTextoCutsceneYuri, Assets.backgroundGinasio, 0);
        cutsceneScreenProfessor = new CutsceneScreen(this, ScreenKey.Cutscene, Dialogos.getTextoCutsceneProfessor, Assets.backgroundSala, 2);

        telaJuca = new PersonagemTela(this, Assets.jucaNeutro, Assets.backgroundBiblioteca, 0);
        telaThales = new PersonagemTela(this, Assets.thalesNeutro, Assets.backgroundGinasio, 1);
        telaYuri = new PersonagemTela(this, Assets.yuriNeutro, Assets.backgroundTeatro, 2);
        telaProfessor = new PersonagemTela(this, Assets.professorTexture, Assets.backgroundSala, 3);

        combateScreen = new Combate(this);
        gameOverScreen = new GameOverScreen(this);

        hubScreen = new HubScreen(this, ScreenKey.Hub);
        //combatScreen = new Combate(this);
        //gameOverScreen = new GameOverScreen(this);
        configScreen = new ConfigScreen(this, ScreenKey.Config);
        mapScreen = new MapScreen(this, ScreenKey.Map);

        musicaPrincipal = Gdx.audio.newMusic(Gdx.files.local("sounds-musics/music_cutscene.mp3"));
        musicaPrincipal.setVolume(Settings.volumeMusica);
        musicaPrincipal.setLooping(true);

        musicaMenu = Gdx.audio.newMusic(Gdx.files.local("sounds-musics/musica_menu.mp3"));
        musicaMenu.setVolume(Settings.volumeMusica);
        musicaMenu.setLooping(true);

        musicaInseguranca = Gdx.audio.newMusic(Gdx.files.internal("sounds-musics/escopofobia.mp3"));
        musicaInseguranca.setVolume(Settings.volumeMusica);
        musicaInseguranca.setLooping(true);

        musicaGameOver = Gdx.audio.newMusic(Gdx.files.internal("sounds-musics/music_gameover.mp3"));
        musicaGameOver.setVolume(Settings.volumeMusica);

        setScreen(ScreenKey.MainMenu);

        /*
        // Esta linha cria um arquivo .prefs no caminho: C:\Users\Usuario\.prefs
        // O valor passado entre aspas é o nome do arquivo.
        prefs = Gdx.app.getPreferences("com.badlogic.DeadLine.settings");

        // Busca em "com.badlogic.DeadLine.settings", o valor armazenado na
        // preferência "Amigo" e atribui à amizade. Caso nenhum valor seja
        // encontrado, atribui "Nenhum".
        String amizade = prefs.getString("Amigo", "Nenhum");
        Integer progresso = prefs.getInteger("Dia", 1);

        // Configura a opção "Amigo" com o valor "Juca".
        prefs.putString("Amigo", "Yuri");

        // Este comando efetivamente salva o que foi feito na linha 57,
        // antes do flush, as alterações ficam salvas no cache.
        prefs.flush();

        // Imprime no log o valor da variável amizade.
        Gdx.app.log("Amigo:", amizade );
        Gdx.app.log("Fase: ", String.valueOf(progresso));
        */

    }

    public void setScreen(ScreenKey screenKey) {
        switch(screenKey) {
            case MainMenu:
                setScreen(mainMenuScreen);
                break;
            case Cutscene:
                setScreen(cutsceneScreen);
                break;
            case cutsceneScreenOnibus:
                setScreen(cutsceneScreenOnibus);
                break;
            case cutsceneScreenJuca:
                setScreen(cutsceneScreenJuca);
                break;
            case cutsceneScreenThales:
                setScreen(cutsceneScreenThales);
                break;
            case cutsceneScreenYuri:
                setScreen(cutsceneScreenYuri);
                break;
            case telaJuca:
                setScreen(telaJuca);
                break;
            case telaThales:
                setScreen(telaThales);
                break;
            case telaYuri:
                setScreen(telaYuri);
                break;
            case telaProfessor:
                setScreen(telaProfessor);
                break;
            case cutsceneScreenProfessor:
                setScreen(cutsceneScreenProfessor);
                break;
            case Hub:
                setScreen(hubScreen);
                break;
            case Config:
                setScreen(configScreen);
                break;
            case Map:
                setScreen(mapScreen);
                break;
            case combate:
                setScreen(combateScreen);
                break;
            case GameOver:
                setScreen(gameOverScreen);
                break;
        }
    }

    public enum ScreenKey {MainMenu, Cutscene, cutsceneScreenOnibus, cutsceneScreenJuca, cutsceneScreenThales, cutsceneScreenYuri, cutsceneScreenProfessor, telaJuca, telaThales, telaYuri, telaProfessor,Hub, combate, GameOver, Config, Map}

    public ConfigScreen getConfigScreen() {
        return configScreen;
    }

    public MapScreen getMapScreen() {
        return mapScreen;
    }

    @Override
    public void dispose(){
        Assets.dispose();
        mainMenuScreen.dispose();
        cutsceneScreen.dispose();
        hubScreen.dispose();
        //combatScreen.dispose();
        //gameOverScreen.dispose();
        musicaPrincipal.dispose();
        musicaMenu.dispose();
        musicaInseguranca.dispose();
        musicaGameOver.dispose();
    }
}
