package suan.mydns.jp.state;

import java.util.HashMap;

import suan.mydns.jp.TETRIS;
import suan.mydns.jp.music.MM2;

public class KMState
{
	public boolean MLeft = false;
	public boolean MCenter = false;
	public boolean MRight = false;
	public int[] Mouse = new int[2];
	public MM2 mm2 = new MM2();

	public HashMap<Integer, Boolean> Key = new HashMap<Integer, Boolean>();
	public HashMap<Character, Boolean> KeyC = new HashMap<Character, Boolean>();

	public boolean IsMouseIn(int x, int y, int Width, int Height)
	{
		if(this.Mouse[0] >= x && this.Mouse[0] <= x + Width)
		{
			if(this.Mouse[1] >= y && this.Mouse[1] <= y + Height)
			{
				return true;
			}
		}
		return false;
	}

	public KMState(TETRIS game) {
		this.Key.put(game.RIGHT, false);
		this.Key.put(game.LEFT, false);
		this.Key.put(game.UP, false);
		this.Key.put(game.DOWN, false);
		this.Key.put(game.CONTROL, false);
		this.KeyC.put(game.ENTER, false);
		this.KeyC.put(' ', false);
	}
}
