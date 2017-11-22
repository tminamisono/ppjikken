package test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sample {
	// フィールド大きさ指定
	public static int tate = 13;
	public static int yoko = 6;

	public static void main(String[] args) {
		String[] c = { "red", "yellow", "blue", "green" };
		String[][] field = new String[yoko + 2][tate + 2];
		field = makeKabe(field);
		field[1][1] = c[0];
		field[2][1] = c[0];
		field[3][6] = c[0];
		field[4][1] = c[0];
		field[5][1] = c[2];
		field[6][1] = c[2];
		field[5][2] = c[2];
		field[1][2] = c[3];
		field[4][2] = c[3];
		field[4][6] = c[2];
		printRensa(field);
	}

	public static void printRensa(String[][] field) {
		//初期状態出力
		printField(field);
		//空中にあるぷよを落下させる
		field = dropPy(field);
		printField(field);
		System.out.println("連鎖開始");
		for (int index = 1; index <= 100; index++) {
			// TODO pairを引数に渡さない形にする（いちいち受け取らなくてもよい？）
			List<int[][]> pair = new ArrayList<int[][]>();
			pair = makePair(field, pair);
			List<List<int[]>> groupList = new ArrayList<List<int[]>>();
			groupList =makeGroup(pair);
			Boolean rem = remvGroup(groupList, field);
			//Boolean rem = remvGroup(groupList, field);
			if (!rem) {
				System.out.println("終了");
				break;
			}
			printField(field);
			field = dropPy(field);
			printField(field);
		}
		return;
	}

	// フィールドのまわりの壁を作る
	public static String[][] makeKabe(String[][] field) {
		// 左の壁
		for (int i = 0; i <= tate + 1; i++) {
			field[0][i] = "kabe";
		}
		// 右の壁
		for (int i = 0; i <= tate + 1; i++) {
			field[yoko + 1][i] = "kabe";
		}
		// 底
		for (int i = 0; i <= yoko + 1; i++) {
			field[i][0] = "kabe";
		}
		return field;
	}

	// コンソールにフィールドを出力
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
					System.out.print("☆");
				} else if (field[j][i].equals("green")) {
					System.out.print("△");
				} else if (field[j][i].equals("blue")) {
					System.out.print("□");
				}
			}
			System.out.println();
		}

	}

	// 連結したぷよのグループをつくる
	public static List<List<int[]>> makeGroup(List<int[][]> pairList) {
		List<List<int[]>> groupList = new ArrayList();
		for (int[][] pair : pairList) {
			int group1 = 0;
			int group2 = 0;
			for (List<int[]> group : groupList) {
				for (int[] point : group) {
					if (Arrays.equals(pair[0], point)) {
						group1 = groupList.indexOf(group);
					}
					if (Arrays.equals(pair[1], point)) {
						group2 = groupList.indexOf(group);
					}
				}
			}
			if (group1 == 0 && group2 == 0) {

				List<int[]> newGroup = new ArrayList();
				newGroup.add(pair[0]);
				newGroup.add(pair[1]);
				groupList.add(newGroup);
			} else if (group2 == 0) {
				groupList.get(group1).add(pair[1]);
			} else if (group1 == 0) {
				groupList.get(group2).add(pair[0]);
			} else {
			}
		}
		return groupList;
	}

	// 4つ以上連結しているグループのぷよを消す
	public static Boolean remvGroup(List<List<int[]>> groupList, String[][] field) {
		Boolean remFlag = false;
		for (List<int[]> group : groupList) {
			if (group.size() >= 4) {
				for (int[] point : group) {
					field[point[0]][point[1]] = null;
					remFlag = true;
				}
			}
		}
		return remFlag;
	}

	public static String[][] dropPy(String[][] field) {
		for (int i = 1; i <= yoko; i++) {
			for (int j = 1; j <= tate; j++) {
				for (int k = j; k > 0; k--) {
					if (field[i][k] == null) {
					} else if (field[i][k - 1] == null) {
						//dummy必要かわからん
						String dummy = field[i][k].toString();
						field[i][k - 1] = dummy;
						field[i][k] = null;
					}
				}
			}
		}
		return field;
	}

	// 隣接している同色のぷよでペアを作る
	public static List<int[][]> makePair(String[][] field, List<int[][]> pair) {
		for (int i = tate; i >= 1; i--) {
			for (int j = 1; j <= yoko; j++) {
				if (field[j][i] == null) {
				} else if (field[j][i].equals("kabe")) {
				} else {
					if (field[j][i].equals(field[j + 1][i])) {
						int[][] pairArray = { { j, i }, { j + 1, i } };
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
