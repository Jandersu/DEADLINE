package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;

/* Acho que fazer essa tela chamar diferentes cutscenes (métodos) baseado num parâmetro pode ser interessante
   Aí a gente tem a cutscene 1, a cutscene 2, etc etc e chama tudo por essa tela, que é chamada por várias outras */

public class CutsceneScreen implements Screen {
    final DeadLine game;
    private float cutsceneTimer;
    private int textoAtual = 0;
    Stage stage;
    Image image;
    private BitmapFont font;
    private final Container<Label> container;
    private final String[] textos;
    private final Label text;

    private final Image caixaDialogo;
    private String textoCompleto;
    private int tamanhoTexto = 0;
    private float tempoDigitar = 0f;

    Sound teclando;
    Sound musica;

    public CutsceneScreen(final DeadLine game, DeadLine.ScreenKey cutscene, String[] texto, Texture imagemBackground) { // Aqui teria o parâmetro "cinematica", um inteiro sei lá
        this.game = game;
        stage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT));
        image = new Image(imagemBackground);
        stage.addActor(image);

        teclando = Gdx.audio.newSound(Gdx.files.internal("teclando.mp3"));
        musica = Gdx.audio.newSound(Gdx.files.internal("music_cutscene.mp3"));

        textos = texto;

        font = new BitmapFont(Gdx.files.internal("fontes/cut.fnt"));

        text = new Label("", new Label.LabelStyle(font, Color.WHITE));

        caixaDialogo = new Image(Assets.barraTexto);
        caixaDialogo.setColor(Color.WHITE);

        container = new Container<Label>(text);
        container.setTransform(true);
        container.setScale(1);

        updateCaixaDialogo();

        stage.addActor(caixaDialogo);
        stage.addActor(container);
        //container.addAction(Actions.parallel(Actions.moveTo(100 , 300, 1.0f), Actions.scaleTo(3f, 3f, 2.0f)));
    }

    @Override
    public void show() {
        game.musicaMenu.stop();
        game.musicaPrincipal.play();
        Gdx.input.setInputProcessor(stage);
        cutsceneTimer = 0;
        textoAtual = 0;

        textoCompleto = textos[textoAtual];
        tamanhoTexto = 0;
        text.setText("");

        //coloca o cantainer no centro
        container.setSize(text.getWidth(), text.getHeight());
        container.setOrigin(container.getWidth() / 2, container.getHeight() / 2);

        container.setPosition( //centraliza o container de acordo com a tela
            (stage.getViewport().getWorldWidth() - container.getWidth()) / 2,
            (50)
        );
        updateCaixaDialogo();
    }

    @Override
    public void render(float delta) {
        logic();

        stage.act(delta);
        stage.draw();
    }

    public void logic(){
        float delta = Gdx.graphics.getDeltaTime();
        cutsceneTimer += delta;
        tempoDigitar += delta;
        game.musicaPrincipal.setVolume(Settings.volumeMusica);

        float velocidadeDigitar = 0.05f;

        if(tempoDigitar >= velocidadeDigitar && tamanhoTexto < textoCompleto.length()){
            tamanhoTexto++;
            text.setText(textoCompleto.substring(0, tamanhoTexto));
            tempoDigitar = 0;
            teclando.play(Settings.volumeSom);
        }


        if (cutsceneTimer >= 2f) {
            cutsceneTimer = 0;

            textoAtual++;
            if(textoAtual < textos.length){
                textoCompleto = textos[textoAtual];
                tamanhoTexto = 0;
                text.setText("");

                //a cada novo texto, o container vai se "adaptar"
                container.setSize(text.getWidth(), text.getHeight());
                container.setOrigin(container.getWidth() / 2, container.getHeight() / 2);
                container.setPosition(
                    (stage.getViewport().getWorldWidth() - container.getWidth()) / 2,
                    (50)
                );

                updateCaixaDialogo();
            }
            else {
                musica.stop();
                teclando.stop();
                game.setScreen(DeadLine.ScreenKey.Hub);
                // Em diferentes situacoes a tela de cutscene vai para outras telas
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        image.setSize(stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());

        container.setPosition(
            (stage.getViewport().getWorldWidth() - container.getWidth()) / 2,
            (50)
        );
        updateCaixaDialogo();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
        teclando.dispose();
        musica.dispose();
        caixaDialogo.remove();
        image.remove();
    }

    private void updateCaixaDialogo() {
        caixaDialogo.setSize(container.getWidth() + 600, container.getHeight() + 100);

        caixaDialogo.setPosition(
            container.getX() - 600 / 2,
            container.getY() - 100 / 2
        );
    }
}
