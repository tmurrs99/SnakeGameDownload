import java.awt.Color;

public enum GameColor
{
	SNAKE(Color.GREEN), OBST(Color.WHITE); 
	private final Color col;
	GameColor(Color c)
	{
		this.col = c;
	}
}
