package baseball;

public class Count {

	/** ボールカウント **/
	private int ball;
	/** ストライクカウント **/
	private int strike;
	/** アウトカウント **/
	private int out;

	/** ボールの最大値 **/
	private static final int BALL_MAX = 3;
	/** ストライクの最大値 **/
	private static final int STRIKE_MAX = 2;
	/** アウトの最大値 **/
	private static final int OUT_MAX = 2;

	/** カウント更新後のステータス **/
	enum COUNT_STATUS {
		OK(""), STRIKE("ストライク"), BALL("ボール"), BASE_ON_BALLS("フォアボール"), OUT("アウト"), OUT_AND_CHANGE("アウト、チェンジ");

		/** 日本語表示名 **/
		private String displayname;

		private COUNT_STATUS(String displayname) {
			this.displayname = displayname;
		}

		/** 日本語っ表示名の取得 **/
		public String getDisplayName() {
			return this.displayname;
		}
	}

	public Count() {
		countReset();
	}

	/** ボールカウントの追加 **/
	public COUNT_STATUS addBall() {
		if (ball == BALL_MAX) {
			strike = 0;
			ball = 0;
			return COUNT_STATUS.BASE_ON_BALLS;
		}

		ball++;
		return COUNT_STATUS.BALL;
	}

	/** ストライクカウントの追加 **/
	public COUNT_STATUS addStrike() {
		if (strike == STRIKE_MAX) {
			strike = 0;
			ball = 0;

			COUNT_STATUS outStatus = addOut();
			if (outStatus == COUNT_STATUS.OUT_AND_CHANGE) {
				return COUNT_STATUS.OUT_AND_CHANGE;
			} else {
				return COUNT_STATUS.OUT;
			}
		}

		strike++;
		return COUNT_STATUS.STRIKE;
	}

	/** アウトカウントの追加 **/
	public COUNT_STATUS addOut() {
		if (out == OUT_MAX) {
			ball = 0;
			strike = 0;
			out = 0;
			return COUNT_STATUS.OUT_AND_CHANGE;
		}

		ball = 0;
		strike = 0;
		out++;
		return COUNT_STATUS.OUT;
	}

	/** ヒット時のカウントリセット **/
	public void countResetByHit() {
		ball = 0;
		strike = 0;
	}

	/** アウト時のカウントリセット **/
	public void countReset() {
		ball = 0;
		strike = 0;
		out = 0;
	}

	/** ボールカウントの取得 **/
	public int getBall() {
		return ball;
	}

	/** ストライクカウントの取得 **/
	public int getStrike() {
		return strike;
	}

	/** アウトカウントの取得 **/
	public int getOut() {
		return out;
	}

	/** カウントのプリント **/
	public void printCount() {
		System.out.println("----------");
		printBall();
		printStrike();
		printOut();
		System.out.println("----------");
	}

	private void printBall() {
		System.out.print("B : ");
		for (int i = 0; i < BALL_MAX; i++) {
			if (i < ball) {
				System.out.print("●");
			} else {
				System.out.print("○");
			}
		}
		System.out.println();
	}

	private void printStrike() {
		System.out.print("S : ");
		for (int i = 0; i < STRIKE_MAX; i++) {
			if (i < strike) {
				System.out.print("●");
			} else {
				System.out.print("○");
			}
		}
		System.out.println();
	}

	private void printOut() {
		System.out.print("O : ");
		for (int i = 0; i < OUT_MAX; i++) {
			if (i < out) {
				System.out.print("●");
			} else {
				System.out.print("○");
			}
		}
		System.out.println();
	}
}
