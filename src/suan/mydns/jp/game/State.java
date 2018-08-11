package suan.mydns.jp.game;

import java.util.Random;

import suan.mydns.jp.TETRIS;
import suan.mydns.jp.block.L;
import suan.mydns.jp.block.Left;
import suan.mydns.jp.block.Longs;
import suan.mydns.jp.block.RevL;
import suan.mydns.jp.block.Right;
import suan.mydns.jp.block.Square;
import suan.mydns.jp.block.SuperBlock;
import suan.mydns.jp.block.Totsu;

public class State
{
	public byte[][] Stage = new byte[10][20];
	public byte[][] block = new byte[4][2];
	private long time = 0;
	public long limit = 1000;
	public SuperBlock[] blo = {new Square(), new L(), new RevL(), new Longs(), new Totsu(), new Left(), new Right()};
	public int now = 0;
	public Random r = new Random();
	public int direction = 0;
	public boolean Gamev = false;
	private int[] point = {40, 60, 200, 900};
	private int points = 0;

	public void init(TETRIS game)
	{
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				Stage[i][j] = 0;
			}
		}
		this.time = System.currentTimeMillis();
		now = r.nextInt(this.blo.length);
		this.direction = r.nextInt(4);
		this.blo[now].init(game);
	}

	public void Draw(TETRIS game)
	{
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				game.stroke(255);
				game.strokeWeight(1);
				game.fill(0x00);
				if(Stage[i][j] != 0) game.rect(30*i+210, 30*j+80, 30, 30);

				game.stroke(255);
				game.strokeWeight(2);
				switch(Stage[i][j])
				{
				case 3:
					game.rect(30*i+220, 30*j+90, 10, 10);
					break;
				case 4:
					game.rect(30*i+215, 30*j+85, 15, 15);
					break;
				case 5:
					game.rect(30*i+220, 30*j+90, 15, 15);
					break;
				case 6:
					game.rect(30*i+215, 30*j+85, 20, 20);
					break;
				case 8:
					game.rect(30*i+220, 30*j+85, 15, 15);
					//game.rect(30*i+220, 30*j+90, 10, 10);
					break;
				case 9:
					game.rect(30*i+215, 30*j+90, 15, 15);
					break;
				}//*/
			}
		}
		for(int i = 0; i < 4; i++)
		{
			game.stroke(255);
			game.strokeWeight(1);
			game.fill(0x00);
			game.rect(30*block[i][0]+210, 30*block[i][1]+80, 30, 30);

			game.stroke(255);
			game.strokeWeight(2);
			switch(now + 3)
			{
			case 3:
				game.rect(30*block[i][0]+220, 30*block[i][1]+90, 10, 10);
				break;
			case 4:
				game.rect(30*block[i][0]+215, 30*block[i][1]+85, 15, 15);
				break;
			case 5:
				game.rect(30*block[i][0]+220, 30*block[i][1]+90, 15, 15);
				break;
			case 6:
				game.rect(30*block[i][0]+215, 30*block[i][1]+85, 20, 20);
				break;
			case 8:
				game.rect(30*block[i][0]+220, 30*block[i][1]+85, 15, 15);
				//game.rect(30*block[i][0]+220, 30*block[i][1]+90, 10, 10);
				break;
			case 9:
				game.rect(30*block[i][0]+215, 30*block[i][1]+90, 15, 15);
				break;
			}//*/
		}
		game.stroke(0);
		if(System.currentTimeMillis()-this.time>this.limit)
		{
			this.time=System.currentTimeMillis();
			boolean b = false;
			for(int i = 0; i < 4; i++)
			{
				if(this.block[i][1]+1 >= 20) b = true;
				else if(this.Stage[this.block[i][0]][this.block[i][1]+1] != 0) b = true;
			}
			if(!b)
			{
				for(int i = 0; i < 4; i++)
				{
					this.block[i][1]+=1;
				}
			}
			else
			{
				game.bgm.SESTART(0);
				for(int i = 0; i < 4; i++)
				{
					this.Stage[this.block[i][0]][this.block[i][1]] = (byte) (now+3);
				}
				now = r.nextInt(this.blo.length);
				this.direction = r.nextInt(4);
				this.blo[now].init(game);
				this.Remove(game);
				this.Gameover(game);
			}
		}

		game.fill(0);
		game.textSize(30);
		game.text("POINTS:"+points, 360, 50);
	}

	public void Remove(TETRIS game)
	{
		int line = 0;
		for(int j = 0; j < 20; j++)
		{
			int n = 0;
			for(int i = 0; i < 10; i++)
			{
				if(this.Stage[i][j] != 0) n++;
			}
			if(n == 10)
			{
				game.bgm.SESTART(2);
				System.out.println(j);
				for(int b = j; b >= 0; b--)
				{
					if(b == 0)
					{
						for(int a = 0; a < 10; a++)
						{
							this.Stage[a][b] = 0;
						}
						break;
					}
					for(int a = 0; a < 10; a++)
					{
						this.Stage[a][b] = this.Stage[a][b-1];
					}
				}
				points += point[line];
				line++;
			}
		}
	}

	public void Gameover(TETRIS game)
	{
		boolean b = false;
		for(int i = 0; i < 4; i++)
		{
			if(this.Stage[this.block[i][0]][this.block[i][1]] != 0) b = true;
		}
		if(b) this.Gamev = true;
	}
}
