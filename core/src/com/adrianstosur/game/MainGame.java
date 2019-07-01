package com.adrianstosur.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.awt.Rectangle;


public class MainGame extends ApplicationAdapter {
    SpriteBatch batch;

    Texture background;
    Texture[] hero;
    Texture[] drake;
    Texture[] enemy;
    Texture[] bee;

    int heroFrame = 0;
    int drakeFrame = 0;
    int enemyFrame = 0;
    int fireState = 0;
    int beeFrame = 0;
    boolean gameStatus = false;
    int slow = 0;
    int playerScore = 0;

    float gravity = 0.4f;
    float velocity = 0;
    float enemyWidth;
    float enemyHeight;
    float heroWidth;
    float heroHeight;
    float beeWidth;
    float beeHeight;

    BitmapFont font_score;
    BitmapFont font_starter;

    Rectangle enemyCollision;
    Rectangle beeColission;
    Rectangle heroCollision;

    private Music music;

    @Override
    public void create () {
        batch = new SpriteBatch();
        background = new Texture("background1.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

       // heroCollision = new Rectangle(400, 200, , heroHeight);
       // enemyCollision = new Rectangle(300, 300, enemyWidth, enemyHeight);
       // beeColission = new Rectangle(350, 300, beeWidth, beeHeight);

        music.setLooping(true);
        music.setVolume(0.9f);
        music.play();

        hero = new Texture[4];
        hero[0] = new Texture("frame-1.png");
        hero[1] = new Texture("frame-2.png");
        hero[2] = new Texture("frame-3.png");
        hero[3] = new Texture("frame-4.png");

        drake = new Texture[4];
        drake[0] = new Texture("frame-1_drake.png");
        drake[1] = new Texture("frame-2_drake.png");
        drake[2] = new Texture("frame-3_drake.png");
        drake[3] = new Texture("frame-4_drake.png");

        bee = new Texture[6];
        bee[0] = new Texture("bee_1.png");
        bee[1] = new Texture("bee_2.png");
        bee[2] = new Texture("bee_3.png");
        bee[3] = new Texture("bee_4.png");
        bee[4] = new Texture("bee_5.png");
        bee[5] = new Texture("bee_6.png");

        enemy = new Texture[43];
        enemy[0] = new Texture("og_000.png");
        enemy[1] = new Texture("og_001.png");
        enemy[2] = new Texture("og_002.png");
        enemy[3] = new Texture("og_003.png");
        enemy[4] = new Texture("og_004.png");
        enemy[5] = new Texture("og_005.png");
        enemy[6] = new Texture("og_006.png");
        enemy[7] = new Texture("og_007.png");
        enemy[8] = new Texture("og_008.png");
        enemy[9] = new Texture("og_009.png");
        enemy[10] = new Texture("og_010.png");
        enemy[11] = new Texture("og_011.png");
        enemy[12] = new Texture("og_012.png");
        enemy[13] = new Texture("og_013.png");
        enemy[14] = new Texture("og_014.png");
        enemy[15] = new Texture("og_015.png");
        enemy[16] = new Texture("og_016.png");
        enemy[17] = new Texture("og_017.png");
        enemy[18] = new Texture("og_018.png");
        enemy[19] = new Texture("og_019.png");
        enemy[20] = new Texture("og_020.png");
        enemy[21] = new Texture("og_021.png");
        enemy[22] = new Texture("og_022.png");
        enemy[23] = new Texture("og_023.png");
        enemy[24] = new Texture("og_024.png");
        enemy[25] = new Texture("og_025.png");
        enemy[26] = new Texture("og_026.png");
        enemy[27] = new Texture("og_027.png");
        enemy[28] = new Texture("og_028.png");
        enemy[29] = new Texture("og_029.png");
        enemy[30] = new Texture("og_030.png");
        enemy[31] = new Texture("og_031.png");
        enemy[32] = new Texture("og_032.png");
        enemy[33] = new Texture("og_033.png");
        enemy[34] = new Texture("og_034.png");
        enemy[35] = new Texture("og_035.png");
        enemy[36] = new Texture("og_036.png");
        enemy[37] = new Texture("og_037.png");
        enemy[38] = new Texture("og_038.png");
        enemy[39] = new Texture("og_039.png");
        enemy[40] = new Texture("og_040.png");
        enemy[41] = new Texture("og_041.png");
        enemy[42] = new Texture("og_042.png");

        enemyWidth = Gdx.graphics.getWidth() / 10 + 2750;
        enemyHeight = Gdx.graphics.getHeight() / 10;

        beeWidth = Gdx.graphics.getWidth() / 10 + 3500;
        beeHeight = Gdx.graphics.getHeight() / 2;

        heroWidth = Gdx.graphics.getWidth() / 2 - hero[heroFrame].getWidth() / 2 - 450;
        heroHeight = Gdx.graphics.getHeight() / 2 - 1500 + 80;

        font_score = new BitmapFont();
        font_score.setColor(Color.WHITE);
        font_score.getData().setScale(10);

        font_starter = new BitmapFont();
        font_starter.setColor(Color.WHITE);
    }


    @Override
    public void render () {

        batch.begin();
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        if (gameStatus == true) {
            if (Gdx.input.isTouched()) {
                velocity = -11;
            }

            if (enemyWidth > -400 && playerScore < 1000) {
                enemyWidth -= 10;
            } else if (enemyWidth > -400 && playerScore >= 1000) {
                enemyWidth -= 18;
            } else {
                enemyWidth = Gdx.graphics.getWidth() / 10 + 2750;

                if (playerScore < 500) {
                    playerScore += 100;
                } else if (playerScore >= 500 && playerScore < 1500 ){
                    playerScore += 200;
                } else if (playerScore >= 1500 && playerScore < 5000){
                    playerScore += 500;
                } else {
                    playerScore += 100;
                }
            }

            if (beeWidth > -500 && playerScore < 1000) {
                beeWidth -= 8;
            } else if (beeWidth > -500 && playerScore >= 1000){
                beeWidth -= 15;
            } else  {
                beeWidth = Gdx.graphics.getWidth() / 10 + 3500;

                if (playerScore < 500) {
                    playerScore += 100;
                } else if (playerScore >= 500 && playerScore < 1500 ){
                    playerScore += 200;
                } else if (playerScore >= 1500 && playerScore < 5000){
                    playerScore += 500;
                } else {
                    playerScore += 100;
                }
            }

            if (slow < 8){
                slow++;
            } else {
                slow = 0;

                if (heroFrame < 3) {
                    heroFrame++;
                } else {
                    heroFrame = 0;
                }

                if (drakeFrame < 3) {
                    drakeFrame++;
                } else {
                    drakeFrame = 0;
                }

                if (beeFrame < 5) {
                    beeFrame++;
                } else {
                    beeFrame = 0;
                }
            }
                if (enemyFrame < 42) {
                    enemyFrame++;
                } else {
                    enemyFrame = 0;
                }

            velocity += gravity;
            heroHeight -= velocity;

            if (heroHeight >= 800) {
                heroHeight = 800;
            }
            if (heroHeight <= 0) {
                heroHeight = 0;
            }
        } else {

            if(Gdx.input.justTouched()){
                gameStatus = true;
            }
        }

       // if(heroCollision.intersect(enemyCollision) || heroCollision.intersects(beeColission)){
         //   gameStatus = false;
           // Gdx.app.log("collision", "collision detected!");
        //}



        font_score.draw(batch, String.valueOf(playerScore), 750 , 1000);
        batch.draw(drake[drakeFrame], Gdx.graphics.getWidth() / 10 + 850, Gdx.graphics.getHeight() / 10 + 300);
        batch.draw(enemy[enemyFrame], enemyWidth, enemyHeight);
        batch.draw(hero[heroFrame],Gdx.graphics.getWidth() / 2 - hero[heroFrame].getWidth() / 2 - 850, heroHeight + 80);
        batch.draw(bee[beeFrame], beeWidth, beeHeight);

        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        music.dispose();
    }
}
