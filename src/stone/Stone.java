package stone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stone {

	public static String[][] inputCheck(String[][] pBoard, int pTurn) {

		boolean w_input = false;// 正しい座標が入力されたかどうか
		int w_player = 1;// 1が黒-1が白
		String w_first_container = "";// 入力された1文字目
		String w_second_container = "";// 2文字目
		int w_point_x = 0;
		int w_point_y = 0;// x,yともにint型に変換する用

		// 現在のプレイヤーを判断

		w_player = playerJudge(pTurn, w_player);

		if (w_player == 1) {

			System.out.println("現在のプレイヤーは黒です");
		} else {

			System.out.println("現在のプレイヤーは白です");
		}

		System.out.println("現在のターンは" + pTurn);

		// 石を打つ座標を入力。おける場所が選択されるまで繰り返す

		while (w_input == false) {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			try {

				System.out.println("打つ場所を指定してください");

				String str = br.readLine();

				w_first_container = str.substring(0, 1);
				w_second_container = str.substring(1, 2);

			} catch (IOException e) {

				e.printStackTrace();

			} catch (StringIndexOutOfBoundsException e) {

				System.out.println("2文字は入力してください");

			}

			if (w_first_container.matches("[a-h]+")) {

				if (w_second_container.matches("[1-8]")) {

					// 正しい座標が入力されていれば1文字目をx軸、2文字目をy軸とする

					w_point_x = Integer.parseInt(chanegeNumber(w_first_container));
					w_point_y = Integer.parseInt(w_second_container);

					// 指定された座標に石がおけるか判定

					w_input = isPointHitJudge(pBoard, w_point_x, w_point_y, w_player);
				}

			}

		}

		// 入力された座標に石を打つ

		pBoard = stoneHit(pBoard, w_point_x, w_point_y, w_player);
		return pBoard;

	}

	// 配列の第一引数を指定する数字に変換用

	public static String chanegeNumber(String pInputFirstValue) {

		if (pInputFirstValue.equals("a")) {

			pInputFirstValue = "1";
			return pInputFirstValue;

		} else if (pInputFirstValue.equals("b")) {

			pInputFirstValue = "2";
			return pInputFirstValue;

		} else if (pInputFirstValue.equals("c")) {

			pInputFirstValue = "3";
			return pInputFirstValue;

		} else if (pInputFirstValue.equals("d")) {

			pInputFirstValue = "4";
			return pInputFirstValue;

		} else if (pInputFirstValue.equals("e")) {

			pInputFirstValue = "5";
			return pInputFirstValue;

		} else if (pInputFirstValue.equals("f")) {

			pInputFirstValue = "6";
			return pInputFirstValue;

		} else if (pInputFirstValue.equals("g")) {

			pInputFirstValue = "7";
			return pInputFirstValue;

		} else if (pInputFirstValue.equals("h")) {

			pInputFirstValue = "8";
			return pInputFirstValue;

		}

		return pInputFirstValue;

	}

	// 指定された座標の隣に相手の石があり、進んだ先に自分の石があれば打てる

	public static boolean isPointHitJudge(String pBoard[][], int pPointX, int pPointY, int pPlayer) {

		// 入力された座標を記憶

		int w_disination_point_x = pPointX;
		int w_disination_point_y = pPointY;

		try {

			// 空きますか調べる

			if (!pBoard[pPointY][pPointX].equals("0")) {

				System.out.println("既に石があります");
				return false;

			}

			// 上

			if (pBoard[pPointY - 1][pPointX].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_y = pPointY - 1;

				// 相手の石がある限りその方向に進む

				while (pBoard[w_disination_point_y][pPointX].equals(String.valueOf(pPlayer * -1))) {

					w_disination_point_y--;
				}

				// 進んだ先に自分の石があれば打てる

				if (pBoard[w_disination_point_y][pPointX].equals(String.valueOf(pPlayer))) {

					return true;

				}

			}

			// 右斜め上

			if (pBoard[pPointY - 1][pPointX + 1].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_y = pPointY - 1;
				w_disination_point_x = pPointX + 1;

				while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					w_disination_point_y--;
					w_disination_point_x++;
				}

				if (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer))) {

					return true;

				}

			}

			// 右

			if (pBoard[pPointY][pPointX + 1].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_x = pPointX + 1;

				while (pBoard[pPointY][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					w_disination_point_x++;
				}

				if (pBoard[pPointY][w_disination_point_x].equals(String.valueOf(pPlayer))) {

					return true;

				}

			}

			// 右斜め下

			if (pBoard[pPointY + 1][pPointX + 1].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_x = pPointX + 1;
				w_disination_point_y = pPointY + 1;

				while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					w_disination_point_x++;
					w_disination_point_y++;
				}

				if (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer))) {

					return true;

				}

			}

			// 下

			if (pBoard[pPointY + 1][pPointX].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_y = pPointY + 1;

				while (pBoard[w_disination_point_y][pPointX].equals(String.valueOf(pPlayer * -1))) {

					w_disination_point_y++;
				}

				if (pBoard[w_disination_point_y][pPointX].equals(String.valueOf(pPlayer))) {

					return true;

				}

			}

			// 左斜め下

			if (pBoard[pPointY + 1][pPointX - 1].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_y = pPointY + 1;
				w_disination_point_x = pPointX - 1;

				while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					w_disination_point_y++;
					w_disination_point_x--;
				}

				if (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer))) {

					return true;

				}

			}

			// 左

			if (pBoard[pPointY][pPointX - 1].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_x = pPointX - 1;

				while (pBoard[pPointY][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					w_disination_point_x--;
				}

				if (pBoard[pPointY][w_disination_point_x].equals(String.valueOf(pPlayer))) {

					return true;

				}

			}

			// 左斜め上

			if (pBoard[pPointY - 1][pPointX - 1].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_x = pPointX - 1;
				w_disination_point_y = pPointY - 1;

				while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					w_disination_point_x--;
					w_disination_point_y--;
				}

				if (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer))) {

					return true;

				}

			}

			// ここに来た時点で全方向に打てる場所見つかってない

			System.out.println("そこには打てません。再度打つ場所を指定してください");
			return false;

		} catch (ArrayIndexOutOfBoundsException e) {

			System.out.println("そこには打てません。盤上をはみ出ています");

			return false;

		}

	}

	// 石を打つ。挟んだ石を自分と同じ色に変える

	public static String[][] stoneHit(String pBoard[][], int pPointX, int pPointY, int pPlayer) {

		int w_disination_point_x = pPointX;
		int w_disination_point_y = pPointY;

		// 上

		if (pBoard[pPointY - 1][pPointX].equals(String.valueOf(pPlayer * -1))) {

			w_disination_point_y = pPointY - 1;

			while (pBoard[w_disination_point_y][pPointX].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_y--;
			}

			// 進んだ先に自分の石があり、石をはさめるのを確認してから石を裏返していく

			if (pBoard[w_disination_point_y][pPointX].equals(String.valueOf(pPlayer))) {

				pBoard[pPointY][pPointX] = String.valueOf(pPlayer);

				w_disination_point_y = pPointY - 1;

				while (pBoard[w_disination_point_y][pPointX].equals(String.valueOf(pPlayer * -1))) {

					pBoard[w_disination_point_y][pPointX] = String.valueOf(pPlayer);
					w_disination_point_y--;

				}

			}

		}

		// 右斜め上

		if (pBoard[pPointY - 1][pPointX + 1].equals(String.valueOf(pPlayer * -1))) {

			w_disination_point_y = pPointY - 1;
			w_disination_point_x = pPointX + 1;

			while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_y--;
				w_disination_point_x++;
			}

			if (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer))) {

				pBoard[pPointY][pPointX] = String.valueOf(pPlayer);

				w_disination_point_y = pPointY - 1;
				w_disination_point_x = pPointX + 1;

				while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					pBoard[w_disination_point_y][w_disination_point_x] = String.valueOf(pPlayer);
					w_disination_point_y--;
					w_disination_point_x++;

				}

			}

		}

		// 右

		if (pBoard[pPointY][pPointX + 1].equals(String.valueOf(pPlayer * -1))) {

			w_disination_point_x = pPointX + 1;

			while (pBoard[pPointY][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_x++;

			}

			if (pBoard[pPointY][w_disination_point_x].equals(String.valueOf(pPlayer))) {

				pBoard[pPointY][pPointX] = String.valueOf(pPlayer);
				w_disination_point_x = pPointX + 1;

				while (pBoard[pPointY][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					pBoard[pPointY][w_disination_point_x] = String.valueOf(pPlayer);
					w_disination_point_x++;

				}

			}

		}

		// 右斜め下

		if (pBoard[pPointY + 1][pPointX + 1].equals(String.valueOf(pPlayer * -1))) {

			w_disination_point_x = pPointX + 1;
			w_disination_point_y = pPointY + 1;

			while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_x++;
				w_disination_point_y++;
			}

			if (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer))) {

				pBoard[pPointY][pPointX] = String.valueOf(pPlayer);
				w_disination_point_x = pPointX + 1;
				w_disination_point_y = pPointY + 1;

				while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					pBoard[w_disination_point_y][w_disination_point_x] = String.valueOf(pPlayer);
					w_disination_point_x++;
					w_disination_point_y++;

				}

			}

		}

		// 下

		if (pBoard[pPointY + 1][pPointX].equals(String.valueOf(pPlayer * -1))) {

			w_disination_point_y = pPointY + 1;

			while (pBoard[w_disination_point_y][pPointX].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_y++;

			}

			if (pBoard[w_disination_point_y][pPointX].equals(String.valueOf(pPlayer))) {

				pBoard[pPointY][pPointX] = String.valueOf(pPlayer);
				w_disination_point_y = pPointY + 1;

				while (pBoard[w_disination_point_y][pPointX].equals(String.valueOf(pPlayer * -1))) {

					pBoard[w_disination_point_y][pPointX] = String.valueOf(pPlayer);
					w_disination_point_y++;

				}

			}

		}

		// 左斜め下

		if (pBoard[pPointY + 1][pPointX - 1].equals(String.valueOf(pPlayer * -1))) {

			w_disination_point_y = pPointY + 1;
			w_disination_point_x = pPointX - 1;

			while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_y++;
				w_disination_point_x--;

			}

			if (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer))) {

				pBoard[pPointY][pPointX] = String.valueOf(pPlayer);
				w_disination_point_y = pPointY + 1;
				w_disination_point_x = pPointX - 1;

				while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					pBoard[w_disination_point_y][w_disination_point_x] = String.valueOf(pPlayer);
					w_disination_point_y++;
					w_disination_point_x--;

				}

			}

		}

		// 左斜め上

		if (pBoard[pPointY - 1][pPointX - 1].equals(String.valueOf(pPlayer * -1))) {

			w_disination_point_x = pPointX - 1;
			w_disination_point_y = pPointY - 1;

			while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_x--;
				w_disination_point_y--;

			}

			if (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer))) {

				pBoard[pPointY][pPointX] = String.valueOf(pPlayer);
				w_disination_point_y = pPointY - 1;
				w_disination_point_x = pPointX - 1;

				while (pBoard[w_disination_point_y][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					pBoard[w_disination_point_y][w_disination_point_x] = String.valueOf(pPlayer);
					w_disination_point_y--;
					w_disination_point_x--;

				}

			}

		}

		// 左

		if (pBoard[pPointY][pPointX - 1].equals(String.valueOf(pPlayer * -1))) {

			w_disination_point_x = pPointX - 1;

			while (pBoard[pPointY][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

				w_disination_point_x--;

			}

			if (pBoard[pPointY][w_disination_point_x].equals(String.valueOf(pPlayer))) {

				pBoard[pPointY][pPointX] = String.valueOf(pPlayer);
				w_disination_point_x = pPointX - 1;

				while (pBoard[pPointY][w_disination_point_x].equals(String.valueOf(pPlayer * -1))) {

					pBoard[pPointY][w_disination_point_x] = String.valueOf(pPlayer);
					w_disination_point_x--;

				}

			}

		}

		return pBoard;
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
