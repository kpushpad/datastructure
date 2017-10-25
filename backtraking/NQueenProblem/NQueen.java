package com.kpushpad.problems.nqueen;
/***
 * 
 * @author kpushpad (Kamal Pushpad)
 *
 */
public class NQueen {
	private Integer count;
	private Integer[] solution;
	private Boolean[] col;

	NQueen(int count) {
		this.count = count;
		this.solution = new Integer[count];
		this.col = new Boolean[count];
	}

	private void init() {
		for (int i = 0; i < col.length; i++) {
			col[i] = true;
		}
	}

	private void printSolution() {
		for (int i = 0; i < solution.length; i++) {
			System.out.println(solution[i]);
		}
	}

	// Check if Queen @ (i,j)  is attacked by any other queens we already placed on board
	private Boolean isQueenAttacking(int i, int j) {

		// Check if Queen is on attacking column
		if (col[j] == false) {
			return true;
		}

		// Queen @(i, J) will attack diagonally to any other Queen @(p,q) if |p-i| == |q-j|
		for (int p = 0; p < i; p++) {
			int deltaRow = Math.abs(i - p);
			int deltaCol = Math.abs(j - solution[p]);
			if (deltaRow == deltaCol) {
				return true;
			}
		}
		return false;
	}

  private 	Boolean addQueen(int index) {
		// Once we are done placing all queens , print the Queen config on board
		if (index == count) {
			printSolution();
			return true;
		}

		// Try placing 'index' queen on all possible column in 'index' row
		for (int j = 0; j < count; j++) {
			if (!isQueenAttacking(index, j)) {
				// put index queen in index row  in j col
				solution[index] = j;
				col[j] = false;
				// Try next queen	
				Boolean result = addQueen(index + 1);
				if (result) {
					return true;
				} else {
					//BackTrack
					col[j] = true;
				}
			}
		}
		return false;
	}

	public boolean placeAllQueens() {
		init();
		// Start with 0th queen
		return addQueen(0);	
	}
}
