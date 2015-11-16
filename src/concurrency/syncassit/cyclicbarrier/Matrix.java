package concurrency.syncassit.cyclicbarrier;

import java.util.Random;

public class Matrix {
	private int[][] matrix;

	public Matrix(int rowSize, int columnSize) {
		this.matrix = new int[rowSize][columnSize];
		
		init();
	}

	private void init() {
		Random random = new Random();
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[i].length;j++)
			{
				matrix[i][j] = random.nextInt(10);
			}
		}
	}
	
	public int[] getRow(int row)
	{
		return matrix[row];
	}
}
