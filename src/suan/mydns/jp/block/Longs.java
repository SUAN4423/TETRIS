package suan.mydns.jp.block;

import suan.mydns.jp.TETRIS;

public class Longs extends SuperBlock
{

	@Override
	public void draw(TETRIS ts)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void turn(TETRIS ts, byte turn)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void init(TETRIS ts)
	{
		// TODO 自動生成されたメソッド・スタブ
		switch(ts.st.direction)
		{
		case 0:
		case 2:
			ts.st.block[0][0]=3;
			ts.st.block[0][1]=0;
			ts.st.block[1][0]=4;
			ts.st.block[1][1]=0;
			ts.st.block[2][0]=5;
			ts.st.block[2][1]=0;
			ts.st.block[3][0]=6;
			ts.st.block[3][1]=0;
			break;
		case 1:
		case 3:
			ts.st.block[0][0]=4;
			ts.st.block[0][1]=0;
			ts.st.block[1][0]=4;
			ts.st.block[1][1]=1;
			ts.st.block[2][0]=4;
			ts.st.block[2][1]=2;
			ts.st.block[3][0]=4;
			ts.st.block[3][1]=3;
			break;
		}
	}

	@Override
	public void next(TETRIS ts)
	{
		// TODO 自動生成されたメソッド・スタブ
		switch(ts.st.ndirection)
		{
		case 0:
		case 2:
			ts.st.nblock[0] = 4;
			ts.st.nblock[1] = 5;
			ts.st.nblock[2] = 6;
			ts.st.nblock[3] = 7;
			break;
		case 1:
		case 3:
			ts.st.nblock[0] = 1;
			ts.st.nblock[1] = 5;
			ts.st.nblock[2] = 9;
			ts.st.nblock[3] = 13;
			break;
		}
	}

}