package judge;

public class Judge {

	// ゲーム終了の判断をする
	public static boolean isGameOver(int pTurn, int pNextTurn) {

		// 2ターン経過していれば両者打つところなし

		if (pTurn > 24 || pTurn + 2 == pNextTurn) {

			System.out.println("ゲームは終了デス");
			return true;

		} else {

			return false;

		}

	}

	// 石を数え勝敗の判定を行なう
	public static void stoneNumberJudge(String pBoard[][]) {

		int w_black_stone_count = 0;
		int w_white_stone_count = 0;// それぞれの石の数をここに代入

		for (int x = 0; x < 7; x++) {

			for (int y = 0; y < 7; y++) {

				if (pBoard[x][y].equals("1")) {

					w_black_stone_count++;

				} else if (pBoard[x][y].equals("-1")) {

					w_white_stone_count++;

				}

			}

		}

		System.out.println("石数は黒:" + w_black_stone_count + "個、" + "白:" + w_white_stone_count + "個");

		if (w_black_stone_count > w_white_stone_count) {

			System.out.println("黒の勝ちです");

		} else if (w_black_stone_count < w_white_stone_count) {

			System.out.println("白の勝ちです");

		} else {

			System.out.println("引き分けです");

		}

	}

	// 現在どちらのターンかを判断する
		public static int playerJudge(int pTurn, int pPlayer) {

			if (pTurn % 2 == 0) {

				pPlayer = -1;
			} else {

				pPlayer = 1;
			}

			return pPlayer;
		}


}
