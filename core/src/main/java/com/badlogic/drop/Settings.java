package com.badlogic.drop;

public class Settings {
    public static float tamanhoFonte; // Scale da fonte
    public static float volumeSom; // Valores de volume variam de 0.0 ate 1.0
    public static float volumeMusica;

    // Carrega as configuracoes do jogo
    public static void load(){
        // Se nao tiver configuracoes salvas, usa as padrao
        defaultSettings();
        // Caso contrario usa as das prefs
        //prefSettings();
    }

    public static void defaultSettings(){
        // Configuracoes padrao, MEDIA
        tamanhoFonte = 1;
        volumeSom = .5f;
        volumeMusica = .5f;
    }

    public static void prefSettings(){
        // Codigo das prefs
    }

    /*
    // Metodo que pega as configuracoes de som das preferencias e carrega nas settings.
    // Talvez deva ser no construtor ja que as prefs loadam quando o jogo começa a rodar
    // Ai o codigo de preferencia le daqui quando muda alguma coisa
    public void setSettingsPref() {

    }*/
}
