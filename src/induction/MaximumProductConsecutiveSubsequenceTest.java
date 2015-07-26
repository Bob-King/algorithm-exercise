package induction;

import org.junit.Test;

public class MaximumProductConsecutiveSubsequenceTest {
	
	@Test
	public void testCase1() {
		final float[][] TEST_DATA = new float[][] {
				{ 1, 2, 3, 0.1f, 4, 5 },
				{ 5, -2, -5, -2, -10, -8 },
				{ 5, -0.1f, -2, -5, -10, -8 }
		};
		
		MaximumProductConsecutiveSubsequence mpcs = new MaximumProductConsecutiveSubsequence();
		
		for (final float[] data : TEST_DATA) {
			printArray(mpcs.find(data));
		}
	}
	
	private static void printArray(float[] array) {
		System.out.print("(");
		if (array.length > 0) {
			System.out.print(array[0]);
			for (int i = 1; i != array.length; ++i) {
				System.out.print(", " + array[i]);
			}
		} else {
			System.out.print(" ");
		}
		System.out.println(")");
	}

}
