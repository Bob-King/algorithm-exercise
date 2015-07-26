package utils;

import java.io.PrintStream;

public abstract class Utils {
	
	public static void printArray(PrintStream ps, int[] array) {
		if (array == null) {
			ps.println("<null>");
			return;
		}
		
		ps.print("{ ");
		if (array.length > 0) {
			ps.print(array[0]);
			for (int i = 1; i != array.length; ++i) {
				ps.print(", " + array[i]);
			}
		}
		ps.println(" }");
	}

}
