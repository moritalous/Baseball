package baseball;

import java.util.ArrayList;
import java.util.List;

/** 走者全員クラス **/
public class Runners {

	/** 走者リスト **/
	List<Runner> runners;

	/** 走者の状態更新後のステータス **/
	enum RUNNER_STATUS {
		OK, HOME_IN,
	}

	/** 走者クラス **/
	class Runner {
		int baseNo;

		public Runner() {
			baseNo = 0;
		}

		/** 走者の状態更新 **/
		public RUNNER_STATUS run(int count) {
			baseNo += count;
			if (baseNo > 3) {
				return RUNNER_STATUS.HOME_IN;
			} else {
				return RUNNER_STATUS.OK;
			}
		}

		/** 塁取得 **/
		public int getBaseNo() {
			return baseNo;
		}
	}

	public Runners() {
		runners = new ArrayList<>();
	}

	/** ヒット時の状態更新 **/
	public int hit(int count) {
		runners.add(new Runner());

		List<Runner> removeRunner = new ArrayList<>();

		int point = 0;
		for (Runner runner : runners) {
			RUNNER_STATUS status = runner.run(count);
			if (status == RUNNER_STATUS.HOME_IN) {
				point++;
				removeRunner.add(runner);
			}
		}
		runners.removeAll(removeRunner);
		return point;
	}

	/** 塁状態のプリント **/
	public void printBaseStatus() {
		List<Integer> list = new ArrayList<>();
		for (Runner runner : runners) {
			int baseNo = runner.getBaseNo();
			list.add(baseNo);
		}
		System.out.println(String.format("　　　%s　　", getBaseIcon(list.contains(2))));
		System.out.println(String.format("　　／　＼　"));
		System.out.println(String.format("　%s　　　%s", getBaseIcon(list.contains(3)), getBaseIcon(list.contains(1))));
		System.out.println(String.format("　　＼　／　"));
		System.out.println(String.format("　　　%s　　", getBaseIcon(true)));

	}

	private String getBaseIcon(boolean on) {
		return on ? "◆" : "◇";
	}
}
