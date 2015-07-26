package induction;

public class MaximumProductConsecutiveSubsequence {
	
	public float[] find(float[] sequence) {
		if (sequence == null) {
			throw new IllegalArgumentException("Sequence can't be null!");
		}
		
		if (sequence.length == 0) {
			return new float[] { };
		}
		
		float m = 1;
		float p = 1;
		float n = 1;
		
		int mb = 0;
		int me = 0;
		int pb = 0;
		int nb = 0;
		
		float tf;
		int t;
		
		for (int i = 0; i != sequence.length; ++i) {
			if (sequence[i] == 0) {
				if (p > m) {
					m = p;
					mb = pb;
					me = i;
				}
				p = 1;
				n = 1;
				pb = i + 1;
				nb = i + 1;
			} if (sequence[i] > 0) {
				tf = p * sequence[i];
				if (sequence[i] >= 1) {
					p = tf;
				} else {
					if (p > m) {
						m = p;
						mb = pb;
						me = i;
					}
					p = tf;
					if (p < 1) {
						p = 1;
						pb = i + 1;
					}
				}
				if (n < 0) {
					n *= sequence[i];
				}
			} else {
				if (p > m) {
					m = p;
					mb = pb;
					me = i;
				}
				tf = p * sequence[i];
				t = pb;
				p = n < 0 ? n * sequence[i] : 1;
				pb = n < 0 ? nb : i + 1;
				n = tf;
				nb = t;
			}
		}
		
		if (p > m) {
			m = p;
			mb = pb;
			me = sequence.length;
		}
		
		if (mb < me) {
			float[] result = new float[me - mb];
			System.arraycopy(sequence, mb, result, 0, result.length);
			return result;
		}
		
		return new float[] { };
	}

}
