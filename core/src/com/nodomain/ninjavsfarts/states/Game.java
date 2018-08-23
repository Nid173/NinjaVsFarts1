package com.nodomain.ninjavsfarts.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Game extends state {

    ShapeRenderer shapeRenderer;
    //HOTSPOTS
    Circle ninjaCircle;
    Circle fartCircle;
    Circle infoCircle;
    Circle MusicCircle;
    Circle SettingsCircle;

    //Variables
    int i=0;
    int ninjaState = 0 ;
    int fartloop = 0 ;
    float ninjaY;
    float velocity = 0;
    int gameState = 0;
    int Score = 0;
    int min = 70;
    int max;
    int fartX;
    int randomY;
    int fartW=50;
    int fartH=50;
    //data
    Preferences prefs;

    //LOOK And FEEL
    Texture info;
    Texture Settings;
    Texture[] MusOfOn;
    Texture background;
    Texture gameOver;
    Texture[] ninja;
    Texture[] fart;
    BitmapFont font;
    GlyphLayout layout ;


    //Sounds
    Sound[] JumpS;
    int JumpSCounter = 0;
    Music backgroundMusic;




    public Game(GameStateManger gsm) {
        super(gsm);

        shapeRenderer = new ShapeRenderer();


        //HOTSPOT init
        ninjaCircle = new Circle();
        fartCircle = new Circle();
        infoCircle = new Circle();
        MusicCircle = new Circle();
        SettingsCircle = new Circle();
        //data init
        prefs = Gdx.app.getPreferences("Data");
        //LOOK and FEEL init
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(6);
        background = new Texture("Desert.png");
        gameOver = new Texture("gameover.png");
        ninja = new Texture[2];
        ninja[1] = new Texture("Glide_006.png");
        ninja[0] = new Texture("Jump__006.png");
        fart = new Texture[12];
        fart[0] = new Texture("smokeOrange0.png");
        fart[1] = new Texture("smokeOrange2.png");
        fart[2] = new Texture("smokeOrange3.png");
        fart[3] = new Texture("smokeOrange5.png");
        fart[4] = new Texture("smokeOrange1.png");
        fart[5] = new Texture("smokeOrange2.png");
        fart[6] = new Texture("smokeYellow0.png");
        fart[7] = new Texture("smokeYellow2.png");
        fart[8] = new Texture("smokeYellow3.png");
        fart[9] = new Texture("smokeYellow5.png");
        fart[10] = new Texture("smokeYellow1.png");
        fart[11] = new Texture("smokeYellow2.png");
        info = new Texture ("question.png");
        Settings = new Texture ("gear.png");
        MusOfOn = new Texture[2];
        MusOfOn[0] = new Texture ("musicOff.png");
        MusOfOn[1] = new Texture ("musicOn.png");
        layout = new GlyphLayout();
        layout.setText(font,"HighScore:" + String.valueOf(prefs.getInteger("highScore")));


        // Variables init
        max = Gdx.graphics.getHeight() - 50;
        fartX= Gdx.graphics.getWidth();
        ninjaY = Gdx.graphics.getHeight()/2 - ninja[0].getHeight() / 2;
        // Sounds init
        JumpS= new Sound[6];
        JumpS[0]  = Gdx.audio.newSound(Gdx.files.internal("Jump01.flac"));
        JumpS[3]  = Gdx.audio.newSound(Gdx.files.internal("Jump02.flac"));
        JumpS[1]  = Gdx.audio.newSound(Gdx.files.internal("Jump03.flac"));
        JumpS[2]  = Gdx.audio.newSound(Gdx.files.internal("Jump04.flac"));
        JumpS[4]  = Gdx.audio.newSound(Gdx.files.internal("doh_wav_cut.wav"));
        JumpS[5]  = Gdx.audio.newSound(Gdx.files.internal("Well Done CCBY3.ogg"));
        backgroundMusic  = Gdx.audio.newMusic(Gdx.files.internal("Alexander Ehlers - Twists.mp3"));
        backgroundMusic.setVolume(getMastervol());
        backgroundMusic.setLooping(true);

        SettingsCircle.set( 50 + Settings.getWidth() ,Gdx.graphics.getHeight() - 50,Settings.getWidth()/2);
        MusicCircle.set( 50 + MusOfOn[musicState].getWidth() + Settings.getWidth() ,Gdx.graphics.getHeight() - 50, MusOfOn[musicState].getWidth() /2 );
        infoCircle.set( 50 + info.getWidth() + Settings.getWidth() + MusOfOn[1].getWidth() ,Gdx.graphics.getHeight() - 50,info.getWidth()/2);



    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {


        if (gameState == 1) { // RUNNING
            if(!backgroundMusic.isPlaying()){
                backgroundMusic.play();

            }


            if(fartX < -fartW) {
                randomY = new Random().nextInt((max - min) + 1) + min;
                fartloop = new Random().nextInt(12);
                fartW+=5;
                fartH+=5;
                fartX = Gdx.graphics.getWidth();
                Score++;
            }

            if(ninjaY > 0 && ninjaY < Gdx.graphics.getHeight()) {
                velocity++;
                ninjaY -= velocity;
            }else{
                gameState=2;
            }
            if(Gdx.input.justTouched()) {
                Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                if (!infoCircle.contains(tmp) && !SettingsCircle.contains(tmp) && !MusicCircle.contains(tmp)) {
                    velocity = -20;
                    JumpS[JumpSCounter].play(getSfxvol());
                    if (JumpSCounter == 3) JumpSCounter = 0;
                    JumpSCounter++;

                }

            }
        }else if(gameState==0){ //First screen



            if(Gdx.input.isTouched()) {
                Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                if (!infoCircle.contains(tmp) && !SettingsCircle.contains(tmp) && !MusicCircle.contains(tmp)) {
                    gameState = 1;
                }
            }
        }else if(gameState==2){ //Game Over
            if(Score > prefs.getInteger("highScore") - 1 ){
                if(i==0) {
                    prefs.putInteger("highScore", Score);
                    prefs.flush();
                    layout.setText(font,"HQ:" + String.valueOf(prefs.getInteger("highScore")));
                    Gdx.app.log("highscore", String.valueOf(prefs.getInteger("highScore")));
                    JumpS[5].play(getSfxvol());
                    i++;
                }
            }else if(Score < prefs.getInteger("highScore")){
                if(i==0) {
                    JumpS[4].play(getSfxvol());
                    i++;
                }
            }
            backgroundMusic.pause();

            if(Gdx.input.justTouched()) {
                Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                if (!infoCircle.contains(tmp) && !SettingsCircle.contains(tmp) && !MusicCircle.contains(tmp)) {
                    Score = 0;
                    gameState = 0;
                    fartX = Gdx.graphics.getWidth();
                    fartW = 50;
                    fartH = 50;
                    i=0;
                    ninjaY = Gdx.graphics.getHeight() / 2 - ninja[0].getHeight() / 2;
                    velocity = 0;

                }
            }
        }
        //Draw deppend on GameState
        if (velocity >= 0) {
            ninjaState = 1;
        } else {
            ninjaState = 0;
        }
        sb.begin();
        sb.draw(background, 0, 0,background.getWidth() , Gdx.graphics.getHeight());
        sb.draw(ninja[ninjaState], Gdx.graphics.getWidth() / 2 - ninja[ninjaState].getWidth() / 2, ninjaY,Gdx.graphics.getWidth()/5,Gdx.graphics.getWidth()/4);
        if (gameState == 1){//RUNNING
            sb.draw(fart[fartloop], fartX -= 10, randomY,fartW ,fartH);
            ninjaCircle.set(Gdx.graphics.getWidth() / 2, ninjaY + ninja[ninjaState].getHeight() / 2 ,  Gdx.graphics.getWidth()/5 / 2);
            fartCircle.set(fartX + fart[fartloop].getWidth() / 2 ,randomY + fart[fartloop].getHeight()/2,fartW/2);

            if(Intersector.overlaps(ninjaCircle,fartCircle)){
                gameState=2;
            }
        }
        if(gameState == 2){//GameOver
            sb.draw(gameOver, Gdx.graphics.getWidth() / 2 - gameOver.getWidth() /2, Gdx.graphics.getHeight() / 2 - gameOver.getHeight() /2);

        }
        font.draw(sb,String.valueOf(Score),Gdx.graphics.getWidth() / 2,Gdx.graphics.getHeight() - 200);
        font.draw(sb, layout, Gdx.graphics.getWidth() - layout.width - 10 ,Gdx.graphics.getHeight()-layout.height);
        sb.draw(Settings,100,50);
        sb.draw(MusOfOn[musicState],100 + Settings.getWidth(),50);
        sb.draw(info,100 + Settings.getWidth() + MusOfOn[1].getWidth(),50);




        if(Gdx.input.justTouched())
        {
            Vector2 tmp = new Vector2(Gdx.input.getX(),Gdx.input.getY());
                if(infoCircle.contains(tmp))
                {
                    gsm.set(new info(gsm));
                    dispose();
                }
            if(SettingsCircle.contains(tmp))
            {
                gsm.set(new Settings(gsm));
                dispose();
            }
            if(MusicCircle.contains(tmp))
            {
                if(Mastervol==1){
                    Mastervol=0;
                    backgroundMusic.setVolume(Mastervol);
                    musicState=0;
                }else{
                    Mastervol=1;
                    musicState=1;
                    backgroundMusic.setVolume(Mastervol);
                }
            }
        }
        sb.end();


    }

    @Override
    public void dispose() {
        shapeRenderer = new ShapeRenderer();

        font.dispose();
        background.dispose();
        gameOver.dispose();
        ninja[1].dispose();
        ninja[0].dispose();
        fart[0].dispose();
        fart[1].dispose();
        fart[2].dispose();
        fart[3].dispose();
        fart[4].dispose();
        fart[5].dispose();
        fart[6].dispose();
        fart[7].dispose();
        fart[8].dispose();
        fart[9].dispose();
        fart[10].dispose();
        fart[11].dispose();
        info.dispose();
        Settings.dispose();
        MusOfOn[0].dispose();
        MusOfOn[1].dispose();


        JumpS[0].dispose();
        JumpS[3].dispose();
        JumpS[1].dispose();
        JumpS[2].dispose();
        JumpS[4].dispose();
        JumpS[5].dispose();
        backgroundMusic.dispose();

    }
}
