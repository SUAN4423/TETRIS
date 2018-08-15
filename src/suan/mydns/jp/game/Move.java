package suan.mydns.jp.game;

import suan.mydns.jp.TETRIS;

public class Move
{
	private boolean[] pressed = {false, false, false, false};

	public void move(TETRIS game)
	{
		if(game.kmState.Key.get(game.LEFT) && !this.pressed[0])
		{
			game.bgm.SESTART(1);
			this.pressed[0] = true;
			boolean b = false;
			for(int i = 0; i < 4; i++)
			{
				if(game.st.block[i][0] == 0) b = true;
				else if(game.st.Stage[game.st.block[i][0] - 1][game.st.block[i][1]] != 0) b = true;
				game.st.block[i][0] -= 1;
			}
			if(b)
			{
				for(int i = 3; i >= 0; i--)
				{
					game.st.block[i][0] += 1;
				}
			}
		}
		else if(!game.kmState.Key.get(game.LEFT) && this.pressed[0])
		{
			this.pressed[0] = false;
		}
		if(game.kmState.Key.get(game.RIGHT) && !this.pressed[1])
		{
			game.bgm.SESTART(1);
			this.pressed[1] = true;
			boolean b = false;
			for(int i = 3; i >= 0; i--)
			{
				if(game.st.block[i][0] == 9) b = true;
				else if(game.st.Stage[game.st.block[i][0] + 1][game.st.block[i][1]] != 0) b = true;
				game.st.block[i][0] += 1;
			}
			if(b)
			{
				for(int i = 0; i < 4; i++)
				{
					game.st.block[i][0] -= 1;
				}
			}
		}
		else if(!game.kmState.Key.get(game.RIGHT) && this.pressed[1])
		{
			this.pressed[1] = false;
		}
	}

	public void turn(TETRIS game)
	{
		if(game.kmState.Key.get(game.DOWN) && !this.pressed[2])
		{
			game.bgm.SESTART(1);
			game.st.direction++;
			if(game.st.direction > 3) game.st.direction = 0;
			this.pressed[2] = true;
			boolean b = false;
			for(int i = 3; i >= 0; i--)
			{
				byte x = (byte) (game.st.block[1][1] - game.st.block[i][1]);
				byte y = (byte) (game.st.block[1][0] - game.st.block[i][0]);
				switch(game.st.now)
				{
				case 0:
				case 3:
					game.st.block[i][0] = (byte) (game.st.block[1][0] + x);
					game.st.block[i][1] = (byte) (game.st.block[1][1] + y);
					break;
				case 5:
				case 6:
					if(game.st.direction < 2)
					{
						game.st.block[i][0] = (byte) (game.st.block[1][0] + x);
						game.st.block[i][1] = (byte) (game.st.block[1][1] - y);
					}
					else
					{
						game.st.block[i][0] = (byte) (game.st.block[1][0] - x);
						game.st.block[i][1] = (byte) (game.st.block[1][1] + y);
					}
					break;
				case 1:
				case 2:
				case 4:
					game.st.block[i][0] = (byte) (game.st.block[1][0] + x);
					game.st.block[i][1] = (byte) (game.st.block[1][1] - y);
					break;
				}
			}
			for(int i = 3; i >= 0; i--)
			{
				switch(game.st.now)
				{
				case 0:
				case 3:
				case 4:
				case 5:
				case 6:
					break;
				case 1:
				case 2:
					if(game.st.direction == 2)
					{
						game.st.block[i][1] -= 1;
					}
					if(game.st.direction == 0)
					{
						game.st.block[i][1] += 1;
					}
					break;
				}
				if(game.st.block[i][0] > 9 || game.st.block[i][0] < 0) b = true;
				else if(game.st.block[i][1] < 0 || game.st.block[i][1] > 19) b = true;
				else if(game.st.Stage[game.st.block[i][0]][game.st.block[i][1]] != 0) b = true;
			}
			if(b)
			{
				for(int i = 0; i < 4; i++)
				{
					byte x = (byte) (game.st.block[1][1] - game.st.block[i][1]);
					byte y = (byte) (game.st.block[1][0] - game.st.block[i][0]);
					switch(game.st.now)
					{
					case 0:
					case 3:
						game.st.block[i][0] = (byte) (game.st.block[1][0] + x);
						game.st.block[i][1] = (byte) (game.st.block[1][1] + y);
						break;
					case 5:
					case 6:
						if(game.st.direction < 2)
						{
							game.st.block[i][0] = (byte) (game.st.block[1][0] - x);
							game.st.block[i][1] = (byte) (game.st.block[1][1] + y);
						}
						else
						{
							game.st.block[i][0] = (byte) (game.st.block[1][0] + x);
							game.st.block[i][1] = (byte) (game.st.block[1][1] - y);
						}
						break;
					case 1:
					case 2:
					case 4:
						game.st.block[i][0] = (byte) (game.st.block[1][0] - x);
						game.st.block[i][1] = (byte) (game.st.block[1][1] + y);
						break;
					}
				}
				for(int i = 0; i < 4; i++)
				{
					switch(game.st.now)
					{
					case 0:
					case 3:
					case 4:
					case 5:
					case 6:
						break;
					case 1:
					case 2:
						if(game.st.direction == 2)
						{
							game.st.block[i][1] += 1;
						}
						if(game.st.direction == 0)
						{
							game.st.block[i][1] -= 1;
						}
						break;
					}
				}
			}
		}
		else if(!game.kmState.Key.get(game.DOWN) && this.pressed[2])
		{
			this.pressed[2] = false;
		}

		if(game.kmState.Key.get(game.UP) && !this.pressed[3])
		{
			game.bgm.SESTART(0);
			this.pressed[3] = true;
			while(true)
			{
				boolean b = false;
				for(int i = 0; i < 4; i++)
				{
					if(game.st.block[i][1]+1 >= 20) b = true;
					else if(game.st.Stage[game.st.block[i][0]][game.st.block[i][1]+1] != 0) b = true;
				}
				if(!b)
				{
					for(int i = 0; i < 4; i++)
					{
						game.st.block[i][1]+=1;
					}
				}
				else
				{
					for(int i = 0; i < 4; i++)
					{
						game.st.Stage[game.st.block[i][0]][game.st.block[i][1]] = (byte) (game.st.now+3);
					}
					/*game.st.now = game.st.r.nextInt(game.st.blo.length);
					game.st.direction = game.st.r.nextInt(4);
					game.st.blo[game.st.now].init(game);//*/
					/*game.st.now = game.st.next;
					game.st.direction = game.st.ndirection;
					game.st.next = game.st.r.nextInt(game.st.blo.length);
					game.st.ndirection = game.st.r.nextInt(4);
					game.st.blo[game.st.now].init(game);
					game.st.blo[game.st.next].next(game);//*/
					game.st.nbloena = true;
				}
				if(b) break;
			}
			game.st.RemoveCheque(game);
			//game.st.Gameover(game);
		}
		else if(!game.kmState.Key.get(game.UP) && this.pressed[3])
		{
			this.pressed[3] = false;
		}
	}
}
