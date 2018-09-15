package game;

import board.Board;
import judge.Judge;
import stone.Stone;

public class Game {

	public static void main(String[] args) {

		int w_turn = 1;
		int w_progress_turn;// 前回のターンと比較させる.経過ターン
		boolean w_game_end_judge = false;
		int w_point_x = 0;
		int w_point_y = 0;// 座標指定用
		int w_player = 1;// 1が黒、-1が白
		String[][] w_board;

		Board board = new Board();
		Judge judge = new Judge();
		Stone stone = new Stone();

		// 盤上を初期化
		w_board = board.initializeBoard();

		while (w_game_end_judge == false) {

			// 盤上を表示
			board.boardDisp(w_board);

			// 打てる場所があるか判断を行う
			w_progress_turn = board.pointCheck(w_board, w_turn, w_player, w_point_x, w_point_y) + w_turn;

			// 座標を入力し指定、打てる場所が入力されればそこに石を打つ
			// 経過ターンが2ターンたっていれば両者打つとこなし。ゲーム終了。
			w_game_end_judge = judge.isGameOver(w_turn, w_progress_turn);

			// ゲーム終了フラグが立っていれば石を数え勝者を判定
			if (w_game_end_judge == true) {

				judge.stoneNumberJudge(w_board);
				break;
			}

			//経過ターン数を加算
			w_turn = w_progress_turn;

			w_board = stone.inputCheck(w_board, w_turn);

			//ターン加算

			w_turn++;


		}

	}

}
