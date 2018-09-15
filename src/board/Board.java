package board;
import judge.Judge;

public class Board {

	static Judge judge = new Judge();

	//盤上を初期化する。ゲームが始まるときに呼ばれる
	public static String[][] initializeBoard() {

		int w_board_size = 5;// 盤の石をおけるマス数
		int w_wall = 2;// 壁

		String[][] w_board;
		w_board = new String[w_board_size + w_wall][w_board_size + w_wall];// 盤上を表現

		// 盤上を初期化

		for (int i = 0; i < 7; i++) {

			for (int j = 0; j < 7; j++) {

				w_board[i][j] = "0";

			}

		}

		for (int k = 0; k < 7; k++) {// 盤上の上下左右に壁を作る

			for (int s = 0; s < 7; s++) {

				if (k == 0 || k == 6 || s == 0 || s == 6) {

					w_board[k][s] = "2";

				}

			}

		}

		// 石を初期位置にセット

		/*
		 * w_board[4][4] = "1"; w_board[4][5] = "-1"; w_board[5][4] = "-1";
		 * w_board[5][5] = "1";
		 */

		w_board[2][3] = "1";
		w_board[2][4] = "-1";
		w_board[3][3] = "-1";
		w_board[3][4] = "1";

		return w_board;

	}

	// 盤上を表示する

	public static void boardDisp(String[][] pBoard) {

		int w_counter = 1;// X軸の数字表示させるときに使う

		// Y軸の表示用

		System.out.println(" a b c d e f g h  ");

		for (int a = 0; a < 7; a++) {

			for (int b = 0; b < 7; b++) {

				if (b == 0 && w_counter < 6 && a > 0) {

					System.out.print(w_counter++);// X軸の数字を表示

				}

				// 盤上の空きマス、石を表示

				if (pBoard[a][b].equals("1")) {

					// 黒石

					System.out.print("◎");

				} else if (pBoard[a][b].equals("-1")) {

					// 白石

					System.out.print("○");

				} else if (pBoard[a][b].equals("0")) {

					// 空きマス

					System.out.print("　");

				}

				if (b == 6) {

					// 改行

					System.out.println("");

				}

			}

		}

	}








	// 盤上に石がおける場所があるか判断する。経過ターン数を返す。

		public static int pointCheck(String[][] pBoard, int pTurn, int pPlayer, int pPointX, int pPointY) {

			boolean w_hit_judge = false;
			// int w_now_turn = pTurn;// 現在のターンを記憶
			int w_turn_count = 0;

			// 打てるとこを見つけるまで繰り返す(最大2回まで)
			while (w_hit_judge == false) {

				// どちらのターンか判定
				pPlayer = judge.playerJudge(pTurn, pPlayer);

				for (int x = 1; x < 6; x++) {

					for (int y = 1; y < 6; y++) {

						// その場所が空きマスかを調べる

						if (pBoard[x][y].equals("0")) {

							// 上

							// 相手の石か判断

							if (pBoard[x - 1][y].equals(String.valueOf(pPlayer * -1))) {

								pPointX = x - 1;

								// 相手の石が続く限り繰り返す

								while (pBoard[pPointX][y].equals(String.valueOf(pPlayer * -1))) {

									pPointX--;

								}

								// たどった先に自分の石があれば打てる

								if (pBoard[pPointX][y].equals(String.valueOf(pPlayer))) {

									// 打てるため初期化

									w_hit_judge = true;
									return w_turn_count;

								}
							}

							// 右斜め上

							if (pBoard[x - 1][y + 1].equals(String.valueOf(pPlayer * -1))) {

								pPointX = x - 1;
								pPointY = y + 1;

								// 相手の石が続く限り繰り返す

								while (pBoard[pPointX][pPointY].equals(String.valueOf(pPlayer * -1))) {

									pPointX--;
									pPointY++;

								}

								// たどった先に自分の石があれば打てる

								if (pBoard[pPointX][pPointY].equals(String.valueOf(pPlayer))) {

									w_hit_judge = true;
									return w_turn_count;

								}
							}

							// 右

							if (pBoard[x][y + 1].equals(String.valueOf(pPlayer * -1))) {

								pPointY = y + 1;

								while (pBoard[x][pPointY].equals(String.valueOf(pPlayer * -1))) {

									pPointY++;

								}

								if (pBoard[x][pPointY].equals(String.valueOf(pPlayer))) {

									w_hit_judge = true;
									return w_turn_count;

								}
							}

							// 右斜め下

							if (pBoard[x + 1][y + 1].equals(String.valueOf(pPlayer * -1))) {

								pPointY = y + 1;
								pPointX = x + 1;

								while (pBoard[pPointX][pPointY].equals(String.valueOf(pPlayer * -1))) {

									pPointY++;
									pPointX++;

								}

								if (pBoard[pPointX][pPointY].equals(String.valueOf(pPlayer))) {

									w_hit_judge = true;
									return w_turn_count;

								}
							}

							// 下

							if (pBoard[x + 1][y].equals(String.valueOf(pPlayer * -1))) {

								pPointX = x + 1;

								while (pBoard[pPointX][y].equals(String.valueOf(pPlayer * -1))) {

									pPointX++;

								}

								if (pBoard[pPointX][y].equals(String.valueOf(pPlayer))) {

									w_hit_judge = true;
									return w_turn_count;

								}
							}

							// 左斜め下

							if (pBoard[x + 1][y - 1].equals(String.valueOf(pPlayer * -1))) {

								pPointX = x + 1;
								pPointY = y - 1;

								while (pBoard[pPointX][pPointY].equals(String.valueOf(pPlayer * -1))) {

									pPointX++;
									pPointY--;

								}

								// たどった先に自分の石があれば打てる

								if (pBoard[pPointX][pPointY].equals(String.valueOf(pPlayer))) {

									w_hit_judge = true;
									return w_turn_count;

								}
							}

							// 左

							if (pBoard[x][y - 1].equals(String.valueOf(pPlayer * -1))) {

								pPointY = y - 1;

								while (pBoard[x][pPointY].equals(String.valueOf(pPlayer * -1))) {

									pPointY--;

								}

								if (pBoard[x][pPointY].equals(String.valueOf(pPlayer))) {

									w_hit_judge = true;
									return w_turn_count;

								}
							}

							// 左斜め上

							if (pBoard[x - 1][y - 1].equals(String.valueOf(pPlayer * -1))) {

								pPointY = y - 1;
								pPointX = x - 1;

								while (pBoard[pPointX][pPointY].equals(String.valueOf(pPlayer * -1))) {

									pPointY--;
									pPointX--;

								}

								if (pBoard[pPointX][pPointY].equals(String.valueOf(pPlayer))) {

									w_hit_judge = true;
									return w_turn_count;

								}
							}

						}

					}
				}

				// 打つ場所がないためパス.次のターンへ

				System.out.println("打つ場所がないためパス。1ターン進める");

				w_turn_count++;
				pTurn++;

				// 2回連続パスが続けば両者打つとこなし。勝敗判定に移る

				if (w_turn_count == 2) {

					w_hit_judge = true;
					System.out.println("両者打つ場所なし。勝敗判定に移ります");
				}

			}

			return w_turn_count;

		}



}
