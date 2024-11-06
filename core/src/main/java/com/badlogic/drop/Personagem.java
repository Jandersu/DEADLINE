package com.badlogic.drop;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Personagem extends Actor {
    protected String nome;
    protected boolean amigo;
    protected Sprite sprite;

    public Personagem(){
        sprite = new Sprite();
    }

    public void falar(){

    }

    public boolean isAmigo() {
        return amigo;
    }

    public void setAmigo(boolean amigo) {
        this.amigo = amigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
