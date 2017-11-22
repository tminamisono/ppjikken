package test1;

import java.util.ArrayList;
import java.util.List;

public class Sample {

	public static int tate = 13;
	public static int yoko = 6;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String[] c = { "red", "yellow", "blue", "green" };
		List<int[][]> pair = new ArrayList();
		String[][] field = new String[yoko + 2][tate + 2];
		field = makeKabe(field);
		field[1][1] = c[1];
		field[2][1] = c[1];
		field[3][1] = c[1];
		pair=makePair(field, pair);
		System.out.println(pair);
		printField(field);

	}

	public static String[][] makeKabe(String[][] field) {
		for (int i = 0; i <= tate + 1; i++) {
			field[0][i] = "kabe";
		}
		for (int i = 0; i <= tate + 1; i++) {
			field[yoko + 1][i] = "kabe";
		}
		for (int i = 0; i <= yoko + 1; i++) {
			field[i][0] = "kabe";
		}
		return field;
	}

	public static void printField(String[][] field) {
		for (int i = tate + 1; i >= 0; i--) {
			for (int j = 0; j <= yoko + 1; j++) {
				if (field[j][i] == null) {
					System.out.print("　");
				} else if (field[j][i].equals("kabe")) {
					System.out.print("■");
				} else if (field[j][i].equals("red")) {
					System.out.print("○");
				} else if (field[j][i].equals("yellow")) {
					System.out.print("×");
				} else if (field[j][i].equals("green")) {
					System.out.print("△");
				} else if (field[j][i].equals("blue")) {
					System.out.print("□");
				}
			}
			System.out.println();
		}

	}

	public static List<int[][]> makePair(String[][] field, List<int[][]> pair) {
		for (int i = tate; i >= 1; i--) {
			for (int j = 1; j <= yoko; j++) {
				if (field[j][i] == null) {
					break;
				} else if (field[j][i].equals("kabe")) {
					break;
				} else {
					if (field[j][i].equals(field[j + 1][i])) {
						int[][] pairArray = { { j, i }, { j + 1, i } };
						System.out.println(pairArray);
						pair.add(pairArray);
					}
					if (field[j][i].equals(field[j - 1][i])) {
						int[][] pairArray = { { j, i }, { j - 1, i } };
						pair.add(pairArray);
					}
					if (field[j][i].equals(field[j][i + 1])) {
						int[][] pairArray = { { j, i }, { j, i + 1 } };
						pair.add(pairArray);
					}
					if (field[j][i].equals(field[j][i - 1])) {
						int[][] pairArray = { { j, i }, { j, i - 1 } };
						pair.add(pairArray);
					}
				}
			}

		}
		return pair;
	}
}
