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

	public int next = 0;
	public byte[] nblock = new byte[4];
	public int ndirection = 0;

	public long DELTIME = 0;
	public byte DELNUM = 0;
	public byte[] DELROW = {0, 0, 0, 0};
	public boolean delete = false;
	public boolean nbloena = false;

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

		next = r.nextInt(this.blo.length);
		this.ndirection = r.nextInt(4);

		this.blo[now].init(game);
		this.blo[next].next(game);
	}

	public void Draw(TETRIS game)
	{
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				if(System.currentTimeMillis() - this.DELTIME < 1000)
				{
					if((System.currentTimeMillis() - this.DELTIME) / 250 == 1 || (System.currentTimeMillis() - this.DELTIME) / 250 == 3)
					{
						boolean b = false;
						for(int k = 0; k < this.DELNUM; k++)
						{
							if(j == this.DELROW[k]) b = true;
						}
						if(b) continue;
					}
				}
				else if(delete)
				{
					this.Remove(game);
					this.delete = false;
				}
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

		if(!delete)
		{
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
				if(!nbloena)
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
						/*now = r.nextInt(this.blo.length);
						this.direction = r.nextInt(4);//*/
						this.RemoveCheque(game);
						//this.Gameover(game);
						this.nbloena = true;
					}
				}
			}

			if(nbloena)
			{
				this.time=System.currentTimeMillis();
				now = next;
				this.direction = this.ndirection;
				next = r.nextInt(this.blo.length);
				this.ndirection = r.nextInt(4);
				this.blo[now].init(game);
				this.blo[next].next(game);
				this.nbloena = false;
				this.Gameover(game);
			}
		}

		game.fill(0);
		game.textSize(30);
		game.text("NEXT", 585, 490);
		for(int i = 0; i < 4; i++)
		{
			game.stroke(255);
			game.strokeWeight(1);
			game.fill(0x00);
			game.rect(30*(nblock[i]%4)+575, 30*(nblock[i]/4)+500, 30, 30);

			game.stroke(255);
			game.strokeWeight(2);
			switch(next + 3)
			{
			case 3:
				game.rect(30*(nblock[i]%4)+585, 30*(nblock[i]/4)+510, 10, 10);
				break;
			case 4:
				game.rect(30*(nblock[i]%4)+580, 30*(nblock[i]/4)+505, 15, 15);
				break;
			case 5:
				game.rect(30*(nblock[i]%4)+585, 30*(nblock[i]/4)+510, 15, 15);
				break;
			case 6:
				game.rect(30*(nblock[i]%4)+580, 30*(nblock[i]/4)+505, 20, 20);
				break;
			case 8:
				game.rect(30*(nblock[i]%4)+585, 30*(nblock[i]/4)+505, 15, 15);
				//game.rect(30*block[i][0]+220, 30*block[i][1]+90, 10, 10);
				break;
			case 9:
				game.rect(30*(nblock[i]%4)+580, 30*(nblock[i]/4)+510, 15, 15);
				break;
			}
		}

		game.fill(0);
		game.textSize(30);
		game.text("POINTS:"+points, 360, 50);
	}

	public void RemoveCheque(TETRIS game)
	{
		this.DELNUM = 0;
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
				this.DELTIME = System.currentTimeMillis();
				this.DELROW[this.DELNUM++] = (byte) j;
				this.delete = true;
			}
		}
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
