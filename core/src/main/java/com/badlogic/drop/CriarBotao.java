package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class CriarBotao {
    private final Skin skin;
    private final BitmapFont font;
    private final TextButton botao;

    public CriarBotao(String textoBotao, TextureAtlas buttonAtlas) {

        font = new BitmapFont(Gdx.files.local("fontes/pixel.fnt"));
        font.getData().setScale(0.75f);

        skin = new Skin();
        skin.addRegions(buttonAtlas);

        TextButtonStyle botaoStyle = new TextButtonStyle();
        botaoStyle.font = font;
        botaoStyle.fontColor = Color.BLACK;
        botaoStyle.up = skin.getDrawable("botao-normal");
        botaoStyle.down = skin.getDrawable("down-button");
        botaoStyle.checked = skin.getDrawable("checked-button");

        botao = new TextButton(textoBotao, botaoStyle);
    }

    public TextButton getBotao() {
        return botao;
    }
}
