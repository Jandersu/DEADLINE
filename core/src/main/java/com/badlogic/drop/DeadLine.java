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

    //variaveis cutscene
    private final String[] textoCutscene1 = {"Era uma vez", "um menino chamado Davi", ":O"};
    //private final Image imagemBackground1 = new Image(Assets.backgroundCutsceneTeste);

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
        cutsceneScreen = new CutsceneScreen(this, ScreenKey.Cutscene, textoCutscene1, Assets.backgroundCutsceneTeste);
        hubScreen = new HubScreen(this, ScreenKey.Hub);
        //combatScreen = new Combate(this);
        //gameOverScreen = new GameOverScreen(this);
        configScreen = new ConfigScreen(this, ScreenKey.Config);
        mapScreen = new MapScreen(this, ScreenKey.Map);

        musicaPrincipal = Gdx.audio.newMusic(Gdx.files.local("music_cutscene.mp3"));
        musicaPrincipal.setVolume(Settings.volumeMusica);
        musicaPrincipal.setLooping(true);

        musicaMenu = Gdx.audio.newMusic(Gdx.files.local("musica_menu.mp3"));
        musicaMenu.setVolume(Settings.volumeMusica);
        musicaMenu.setLooping(true);

        musicaInseguranca = Gdx.audio.newMusic(Gdx.files.internal("escopofobia.mp3"));
        musicaInseguranca.setVolume(Settings.volumeMusica);
        musicaInseguranca.setLooping(true);

        musicaGameOver = Gdx.audio.newMusic(Gdx.files.internal("music_gameover.mp3"));
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

    public void setScreen(ScreenKey screenKey){
        switch(screenKey) {
            case MainMenu:
                setScreen(mainMenuScreen);
                break;
            case Cutscene:
                setScreen(cutsceneScreen);
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
            /*case Combat:
                setScreen(combatScreen);
                break;
            case GameOver:
                setScreen(gameOverScreen);
                break;*/
        }
    }

    public enum ScreenKey {MainMenu, Cutscene, Hub, Combat, GameOver, Config, Map}

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
