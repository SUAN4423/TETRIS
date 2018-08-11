package suan.mydns.jp.block;

import suan.mydns.jp.TETRIS;

public abstract class SuperBlock
{
	public abstract void draw(TETRIS ts);
	public abstract void turn(TETRIS ts, byte turn);
	public abstract void init(TETRIS ts);
}
