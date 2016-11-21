package induction;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianOfStream {
	
	public void insert(int i) {
		if (mLeftQueue.isEmpty()) {
			mLeftQueue.add(i);
			return;
		}
		
		final int l = mLeftQueue.peek();
		
		if (mRightQueue.isEmpty()) {
			if (i < l) {
				mRightQueue.add(mLeftQueue.poll());
				mLeftQueue.add(i);
			} else {
				mRightQueue.add(i);
			}
			return;
		}
		
		final int r = mRightQueue.peek();		
		final int lSize = mLeftQueue.size();
		final int rSize = mRightQueue.size(); 
		
		if (i < l) {
			if (lSize > rSize) {
				mRightQueue.add(mLeftQueue.poll());
			}
			
			mLeftQueue.add(i);
		} else if (i <= r) {
			if (lSize > rSize) {
				mRightQueue.add(i);
			} else {
				mLeftQueue.add(i);
			}
		} else {
			if (lSize == rSize) {
				mLeftQueue.add(mRightQueue.poll());
			}
			
			mRightQueue.add(i);
		}
	}
	
	public int[] median() {
		final int lSize = mLeftQueue.size();
		final int rSize = mRightQueue.size();
		
		if (lSize == 0) {
			return new int[] { };
		} else if (lSize > rSize) {
			return new int[] { mLeftQueue.peek() };
		} else {
			return new int[] { mLeftQueue.peek(), mRightQueue.peek() };
		}
	}
	
	// Store the elements which are less than or equal to the median
	Queue<Integer> mLeftQueue = new PriorityQueue<>(
			new Comparator<Integer>() {

				@Override
				public int compare(Integer arg0, Integer arg1) {
					return arg1 - arg0;
				}
		
	});
	// Store the elements which are greater than or equal to the median
	Queue<Integer> mRightQueue = new PriorityQueue<>();

}
