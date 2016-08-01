package baseball;

/**
 * スコアボード
 *
 */
public class Score {

	/** 先行が攻撃中はtrue、後攻が攻撃中はfalse **/
	private boolean playFirst;
	/** イニングのカウント1回は0 **/
	private int inningCount;

	/** 先攻チームのスコア **/
	private int[] firstTeamScore;
	/** 後攻チームのスコア **/
	private int[] secondTeamScore;

	public Score() {
		playFirst = true;
		inningCount = 0;

		firstTeamScore = new int[9];
		secondTeamScore = new int[9];
	}

	/**
	 * チェンジ時の状態変更
	 */
	public void change() {
		playFirst = !playFirst;
		if (playFirst) {
			inningCount++;
		}
	}

	/**
	 * 試合終了かどうかを返却
	 * 
	 * @return
	 */
	public boolean isFinished() {
		if (inningCount >= 9) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 点数加算
	 * 
	 * @param point
	 */
	public void addPoint(int point) {
		if (playFirst) {
			firstTeamScore[inningCount] += point;
		} else {
			secondTeamScore[inningCount] += point;
		}
	}

	/**
	 * スコアボードのプリント
	 */
	public void printScore() {
		System.out.println("    | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
		printMyScore("先行", firstTeamScore);
		printMyScore("後攻", secondTeamScore);
	}

	private void printMyScore(String teamName, int[] score) {
		System.out.print(teamName + "|");
		for (int i : score) {
			System.out.print(String.format(" %d |", i));
		}
		System.out.println();
	}

	/**
	 * 何回の攻撃かをプリント
	 */
	public void printIning() {
		System.out.println(String.format("%d回%s", inningCount + 1, playFirst ? "表" : "裏"));
	}
}