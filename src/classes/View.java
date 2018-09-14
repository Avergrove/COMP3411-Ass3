package classes;

public class View {
	char[][] view;

	@Override
	public String toString() {

		return this.getView();
	}

	public View(char[][] view) {

		// TODO: Adjust it so that it takes in views of different length.
		this.view = new char[view.length][view.length];
		for (int i = 0; i < view.length; i++) {
			for (int j = 0; j < view[i].length; j++) {
				this.view[i][j] = view[i][j];
			}
		}
	}

	// Returns the item at position x and y of the view.
	public char getItemAtPosition(int x, int y) {
		return view[x][y];
	}

	// Sets the item at position to char, returns the previous value.
	public char setItemAtPosition(int x, int y, char c) {
		char prev = getItemAtPosition(x, y);
		view[x][y] = c;
		return prev;
	}

	public int getXLength() {
		return this.view.length;
	}

	public int getYLength() {
		return this.view[0].length;
	}

	public String getView() {

		String s = "";

		int i, j;

		s = s + "\n+-----+\n";
		for (i = 0; i < 5; i++) {
			s = s + "|";
			for (j = 0; j < 5; j++) {
				if ((i == 2) && (j == 2)) {
					s = s + '^';
				} else {
					s = s + this.getItemAtPosition(i, j);
				}
			}
			s = s + "|\n";
		}
		s = s + "+-----+\n";

		return s;
	}

	// An Inplace function to rotate a N x N matrix
	// by 90 degrees in anti-clockwise direction
	public void rotateAntiClockwise() {
		int N = this.view.length;

		// Consider all squares one by one
		for (int x = 0; x < N / 2; x++) {
			// Consider elements in group of 4 in
			// current square
			for (int y = x; y < N - x - 1; y++) {
				// store current cell in temp variable
				char temp = view[x][y];

				// move values from right to top
				view[x][y] = view[y][N - 1 - x];

				// move values from bottom to right
				view[y][N - 1 - x] = view[N - 1 - x][N - 1 - y];

				// move values from left to bottom
				view[N - 1 - x][N - 1 - y] = view[N - 1 - y][x];

				// assign temp to left
				view[N - 1 - y][x] = temp;
			}
		}
		System.out.println(this.toString());
	}
	
	public void flip() {
		this.rotateAntiClockwise();
		this.rotateAntiClockwise();
	}
	
	public void rotateClockwise() {
		this.rotateAntiClockwise();
		this.rotateAntiClockwise();
		this.rotateAntiClockwise();
	}

}