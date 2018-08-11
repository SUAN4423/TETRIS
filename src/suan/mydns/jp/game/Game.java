package suan.mydns.jp.game;

import suan.mydns.jp.TETRIS;

public class Game
{
	public void Draw(TETRIS game)
	{
		game.fill(0xFF);
		game.strokeWeight(0);
		game.rect(0, 0, 720, 720);
		game.fill(0);
		game.rect(205, 80, 5, 600);
		game.rect(510, 80, 5, 600);
		game.rect(205, 680, 310, 5);
	}
}
