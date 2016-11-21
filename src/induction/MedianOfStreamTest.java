package induction;

import org.junit.Assert;
import org.junit.Test;

public class MedianOfStreamTest {
	
	@Test
	public void testCase1() {
		final int[] DATA = { 3, 9, 2, 6, 7, 8, 5 };
		final int[][] RESULT = new int[][] {
			{ 3 },
			{ 3, 9 },
			{ 3 },
			{ 3, 6 },
			{ 6 },
			{ 6, 7 },
			{ 6 }
		};
		
		MedianOfStream mos = new MedianOfStream();
		
		for (int i = 0; i != DATA.length; ++i) {
			mos.insert(DATA[i]);
			
			int[] r = mos.median();
			
			Assert.assertArrayEquals(RESULT[i], r);
		}
	}

}
