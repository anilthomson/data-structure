package tree;


public class TOH {
	static int x = 1;
	static char[] splitters = { '0', '@', '#', '$', '%', '&', '*' };

	// Java recursive function to solve tower of hanoi puzzle
	static void towerOfHanoi(int n, String from_rod, String to_rod, String aux_rod) {
		printStart(n, n);
		if (n == 1) {
			print("Move disk 1 from rod " + from_rod + " to rod " + to_rod + "   " + n, n);
			printEnd(n, n);
			return;
		}
		towerOfHanoi(n - 1, from_rod, aux_rod, to_rod);
		print("Move disk " + n + " from rod " + from_rod + " to rod " + to_rod + "   " + n, n);
		towerOfHanoi(n - 1, aux_rod, to_rod, from_rod);
		printEnd(n, n);
	}

	// Driver method
	public static void main(String args[]) {
		long l = System.currentTimeMillis();
		int n = 4; // Number of disks
		towerOfHanoi(n, "A", "C", "B"); // A, B and C are names of rods
		System.out.println(System.currentTimeMillis() - l);
	}

	private static void print(String s, int space) {
		for (int i = 0; i < 10/space; i++)
			System.out.print(" ");
		System.out.println(s);
		x++;
	}

	private static void printEnd(int n, int space) {
		for (int i = 0; i < 10/space; i++)
			System.out.print(" ");
		System.out.print("Ending " + n + "  ");
	//	for (int i = 0; i < 10; i++)
	//		System.out.print(splitters[n]);
		System.out.println();
	}

	private static void printStart(int n, int space) {
		for (int i = 0; i < 10/space; i++)
			System.out.print(" ");
		System.out.print("Starting " + n + "  ");
	//	for (int i = 0; i < 10; i++)
	//		System.out.print(splitters[n]);
		System.out.println();
	}
}