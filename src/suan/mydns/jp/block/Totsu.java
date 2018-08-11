package suan.mydns.jp.block;

import suan.mydns.jp.TETRIS;

public class Totsu extends SuperBlock
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
			ts.st.block[0][0]=3;
			ts.st.block[0][1]=1;
			ts.st.block[1][0]=4;
			ts.st.block[1][1]=1;
			ts.st.block[2][0]=5;
			ts.st.block[2][1]=1;
			ts.st.block[3][0]=4;
			ts.st.block[3][1]=0;
			break;
		case 1:
			ts.st.block[0][0]=5;
			ts.st.block[0][1]=0;
			ts.st.block[1][0]=5;
			ts.st.block[1][1]=1;
			ts.st.block[2][0]=5;
			ts.st.block[2][1]=2;
			ts.st.block[3][0]=4;
			ts.st.block[3][1]=1;
			break;
		case 2:
			ts.st.block[0][0]=3;
			ts.st.block[0][1]=0;
			ts.st.block[1][0]=4;
			ts.st.block[1][1]=0;
			ts.st.block[2][0]=5;
			ts.st.block[2][1]=0;
			ts.st.block[3][0]=4;
			ts.st.block[3][1]=1;
			break;
		case 3:
			ts.st.block[0][0]=4;
			ts.st.block[0][1]=0;
			ts.st.block[1][0]=4;
			ts.st.block[1][1]=1;
			ts.st.block[2][0]=4;
			ts.st.block[2][1]=2;
			ts.st.block[3][0]=5;
			ts.st.block[3][1]=1;
			break;
		}
	}

}