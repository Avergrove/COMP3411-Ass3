package classes;

// The context class represents the knowledge the AI currently holds.
public class Context {
	char map[][]; // This is the map as memorized by the AI
	Coordinate startPosition;
	boolean hasAxe;
	boolean hasGold;
	boolean hasRock;
	boolean hasKey;

	public boolean getHasAxe() {
		return this.hasAxe;
	}

	public void isHasAxe(boolean hasAxe) {
		this.hasAxe = hasAxe;
	}

	public boolean getHasGold() {
		return this.hasGold;
	}

	public void isHasGold(boolean hasGold) {
		this.hasGold = hasGold;
	}

	public boolean getHasRock() {
		return this.hasRock;
	}

	public void isHasRock(boolean hasRock) {
		this.hasRock = hasRock;
	}

	public Context() {
		this.map = new char[50][50];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = '?';
			}
		}

		this.map[25][25] = '^';
		startPosition = new Coordinate(25,25);
		
		hasAxe = false;
		hasGold = false;
		hasRock = false;
		hasKey = false;
	}

	// Returns the position of the AI on the map
	public Coordinate getAIPosition() {
		for (int row = 0; row < this.map.length; row++) {
			for (int col = 0; col < this.map[row].length; col++) {
				if (this.map[row][col] == '^' || this.map[row][col] == '<' || this.map[row][col] == 'v'
						|| this.map[row][col] == '>') {
					return new Coordinate(row, col);
				}
			}
		}

		return null;
	}

	// Prints out the map
	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}
	}

	// Returns the direction of the AI
	public char getAIDirection() {
		Coordinate c = getAIPosition();
		return map[c.getRow()][c.getCol()];
	}

	public char getItemAtPosition(int row, int col) {
		return map[row][col];
	}

	// Changes an item at position on map, returns the overwritten item.
	public char setItemAtPosition(int row, int col, char c) {
		char prev = this.getItemAtPosition(row, col);
		map[row][col] = c;
		return prev;
	}

	// Changes an item at position on map, returns the overwritten item.
	public char setItemAtPosition(Coordinate coordinate, char c) {
		char prev = this.getItemAtPosition(coordinate.getRow(), coordinate.getCol());
		map[coordinate.getRow()][coordinate.getCol()] = c;
		return prev;
	}

	// The method adds knowledge to the AI's map, using a view.
	public void improveMap(char view[][]) {

		Coordinate pos = getAIPosition();
		View v = new View(view);

		switch (getAIDirection()) {
		case '^':
			break;

		case '>':
			v.rotateClockwise();
			break;

		case 'v':
			v.flip();
			break;

		case '<':
			v.rotateAntiClockwise();
			break;

		default:
			break;
		}

		// Get the 24 adjacent tiles around the Agent, if possible.
		// TODO: Consider tiles out of bounds of map array.
		for (int row = 0; row < v.getYLength(); row++) {
			for (int col = 0; col < v.getXLength(); col++) {
				if (row != 2 || col != 2) {
					map[pos.getRow() - 2 + row][pos.getCol() - 2 + col] = v.getItemAtPosition(row, col);
				}

			}
		}
	}

	public void moveForward() {
		Coordinate pos = this.getAIPosition();
		char direction = this.getAIDirection();
		Coordinate next;

		switch (direction) {
		case '^':
			next = new Coordinate(pos.getRow() - 1, pos.getCol());
			break;

		case '>':
			next = new Coordinate(pos.getRow(), pos.getCol() + 1);
			break;

		case 'v':
			next = new Coordinate(pos.getRow() + 1, pos.getCol());
			break;

		case '<':
			next = new Coordinate(pos.getRow(), pos.getCol() - 1);
			break;

		default:
			next = null;
			break;
		}

		char item = getItemAtPosition(next.getRow(), next.getCol());

		switch (item) {

		case 'a':
			this.hasAxe = true;
			break;

		case 'k':
			this.hasKey = true;
			break;

		case 'o':
			this.hasRock = true;
			break;

		case 'g':
			this.hasGold = true;
			break;
		
		case ' ':
			break;
		
		case 'O':
			break;
			
		default:
			next = pos;
			break;
		}

		this.setItemAtPosition(pos.getRow(), pos.getCol(), ' ');
		this.setItemAtPosition(next.getRow(), next.getCol(), direction);
	}

	public void turnRight() {
		Coordinate pos = this.getAIPosition();
		char direction = this.getAIDirection();

		switch (direction) {
		case '^':
			this.setItemAtPosition(pos.getRow(), pos.getCol(), '>');
			break;

		case '>':
			this.setItemAtPosition(pos.getRow(), pos.getCol(), 'v');
			break;

		case 'v':
			this.setItemAtPosition(pos.getRow(), pos.getCol(), '<');
			break;

		case '<':
			this.setItemAtPosition(pos.getRow(), pos.getCol(), '^');
			break;
		}
	}

	public void turnLeft() {
		Coordinate pos = this.getAIPosition();
		char direction = this.getAIDirection();

		switch (direction) {
		case '^':
			this.setItemAtPosition(pos.getRow(), pos.getCol(), '<');
			break;

		case '>':
			this.setItemAtPosition(pos.getRow(), pos.getCol(), '^');
			break;

		case 'v':
			this.setItemAtPosition(pos.getRow(), pos.getCol(), '>');
			break;

		case '<':
			this.setItemAtPosition(pos.getRow(), pos.getCol(), 'v');
			break;
		}
	}

}