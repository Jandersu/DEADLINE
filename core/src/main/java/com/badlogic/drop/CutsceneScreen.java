package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
    private Container<Label> container;
    private String[] textos = {"Era uma vez", "um menino chamado Davi", ":O"};
    private Label text;

    public CutsceneScreen(final DeadLine game, DeadLine.ScreenKey cutscene) { // Aqui teria o parâmetro "cinematica", um inteiro sei lá
        this.game = game;
        stage = new Stage(new FitViewport(game.WIDTH, game.HEIGHT));
        image = new Image(Assets.backgroundCutsceneTeste);
        stage.addActor(image);

        font = new BitmapFont(Gdx.files.internal("fontes/cut.fnt"));

        text = new Label(textos[textoAtual], new Label.LabelStyle(font, Color.YELLOW));

        container = new Container<Label>(text);
        container.setTransform(true);
        container.setScale(1);

        stage.addActor(container);
        //container.addAction(Actions.parallel(Actions.moveTo(100 , 300, 1.0f), Actions.scaleTo(3f, 3f, 2.0f)));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        cutsceneTimer = 0;
        textoAtual = 0;
        text.setText(textos[textoAtual]);

        //coloca o cantainer no centro
        container.setSize(text.getWidth(), text.getHeight());
        container.setOrigin(container.getWidth() / 2, container.getHeight() / 2);

        container.setPosition( //centraliza o container de acordo com a tela
            (stage.getViewport().getWorldWidth() - container.getWidth()) / 2,
            (stage.getViewport().getWorldWidth()  - container.getHeight()) / 2
        );
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
        if (cutsceneTimer > 3f) {
            cutsceneTimer = 0;

            textoAtual++;
            if(textoAtual < textos.length){
                text.setText(textos[textoAtual]);

                //a cada novo texto, o container vai se "adaptar"
                container.setSize(text.getWidth(), text.getHeight());
                container.setOrigin(container.getWidth() / 2, container.getHeight() / 2);
                container.setPosition(
                    (stage.getViewport().getWorldWidth() - container.getWidth()) / 2,
                    (stage.getViewport().getWorldHeight() - container.getHeight()) / 2
                );
            }
            else {
                game.setScreen(DeadLine.ScreenKey.Hub); // Em diferentes situacoes a tela de cutscene vai para outras telas
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        image.setSize(stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());

        container.setPosition(
            (stage.getViewport().getWorldWidth() - container.getWidth()) / 2,
            (stage.getViewport().getWorldHeight() - container.getHeight()) / 2
        );
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

    }
}
