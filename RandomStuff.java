import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class RandomStuff
{
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
	{
		Random gen = new Random();
		String fileName = "newshit1";
		
		int[][] stuff = new int[50][2];
		PrintWriter writer = new PrintWriter(fileName +".txt", "UTF-8");
		
		for(int i = 0; i < 50; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				stuff[i][j] = gen.nextInt(23)+1;
				writer.print(stuff[i][j]);
				if(j == 0)
					writer.print(",");
			}
			writer.println();
		}
		
		for(int i = 1; i <= 23; i++)
		{
			for(int j = 1; j <= 23; j++)
			{
				for(int k = 0; k < 50; k++)
					if(stuff[k][0] == i && stuff[k][1] == j)
						{writer.print("X"); break;}
				writer.print("O");
			}
			writer.println();
		}
		writer.close();
	}
}
