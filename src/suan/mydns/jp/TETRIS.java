package suan.mydns.jp;

import processing.core.PApplet;
import suan.mydns.jp.game.Game;
import suan.mydns.jp.game.Move;
import suan.mydns.jp.game.State;
import suan.mydns.jp.music.MM2;
import suan.mydns.jp.state.KMState;

public class TETRIS extends PApplet
{
	public KMState kmState = new KMState(this);
	public MM2 mm2 = new MM2();
	public Game gm = new Game();
	public State st = new State();
	public Move mv = new Move();

	public BGM bgm = new BGM();

	public static void main(String[] args)
	{
		// TODO 自動生成されたメソッド・スタブ
		PApplet.main("suan.mydns.jp.TETRIS");
	}

	@Override
	public void settings()
	{
		size(720, 720);
	}

	@Override
	public void setup()
	{
		st.init(this);
		String[] strs = {".\\TETRIS_TECHNOTRIS.thh"};
		bgm.BGMLOAD(strs);
		String[] SE = {".\\SE3.thh",".\\SE2.thh",".\\SE1.thh"};
		bgm.SELOAD(SE);
	}

	@Override
	public void draw()
	{
		if(!st.Gamev)
		{
			if(!bgm.isStartBGM(0)) bgm.BGMSTART(0);
			gm.Draw(this);
			st.Draw(this);
			mv.move(this);
			mv.turn(this);
		}
		else
		{
			if(bgm.isStartBGM(0)) bgm.BGMSTOP(0);
			this.fill(0);
			this.textSize(30);
			this.text("GameOver", 50, 50);
		}
	}

	@Override
	public void keyPressed()
	{
		kmState.Key.put(keyCode, true);
		kmState.KeyC.put(key, true);
	}

	@Override
	public void keyReleased()
	{
		kmState.Key.put(keyCode, false);
		kmState.KeyC.put(key, false);
	}
}
