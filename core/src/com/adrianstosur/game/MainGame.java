package com.adrianstosur.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


public class MainGame extends ApplicationAdapter {
    SpriteBatch batch;

    Texture background;
    Texture[] hero;
    Texture[] drake;
    Texture[] enemy;

    int heroState = 0;
    int drakeState = 0;
    int enemyState = 0;
    int fireState = 0;
    int gameStatus = 0;

    int playerScore = 0;

    float gravity = 0.3f;
    float velocity = 0;
    float enemyWidth;
    float enemyHeight;
    float heroWidth;
    int heroHeight;

    BitmapFont font;

    ShapeRenderer shapeRenderer;
    Rectangle enemyCollision;
    Rectangle heroColission;

    private Music music;
    int slow = 0;


    @Override
    public void create () {
        batch = new SpriteBatch();
        background = new Texture("background1.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

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

        heroWidth = Gdx.graphics.getWidth() / 2 - hero[heroState].getWidth() / 2 - 450;
        heroHeight = Gdx.graphics.getHeight() / 2 - 1500;

        shapeRenderer = new ShapeRenderer();

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(15);

    }


    @Override
    public void render () {
        batch.begin();
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        if (gameStatus == 1) {

            if (Gdx.input.isTouched()) {
                velocity = -11;
                playerScore++;
            }

            if (enemyWidth > -400) {
                enemyWidth -= 10;
            } else {
                enemyWidth = Gdx.graphics.getWidth() / 10 + 2750;
                playerScore += 100;
            }

            if (slow < 8){
                slow++;
            } else {
                slow = 0;

                if (heroState < 3) {
                    heroState++;
                } else {
                    heroState = 0;
                }

                if (drakeState < 3) {
                    drakeState++;
                } else {
                    drakeState = 0;
                }

            }
                if (enemyState < 42) {
                    enemyState++;
                } else {
                    enemyState = 0;
                }
                
                if (fireState < 2){
                    fireState++;
                } else {
                    fireState = 0;
                }

            velocity += gravity;
            heroHeight -= velocity;

            if (heroHeight <= 0) {
                heroHeight = 0;
            }
        } else {

            if(Gdx.input.isTouched()){
                playerScore++;
                gameStatus = 1;

            }
            //TODO
            if (enemyHeight == heroHeight && heroWidth == enemyWidth) {
                    gameStatus = 2;

                }
        }

        font.draw(batch, String.valueOf(playerScore), 125, 1000);
        batch.draw(drake[drakeState], Gdx.graphics.getWidth() / 10 + 700, Gdx.graphics.getHeight() / 10 + 300);
        batch.draw(enemy[enemyState], enemyWidth, enemyHeight);
        batch.draw(hero[heroState],Gdx.graphics.getWidth() / 2 - hero[heroState].getWidth() / 2 - 450, heroHeight + 80);

        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        music.dispose();
    }
}
