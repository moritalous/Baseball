package baseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import baseball.Count.COUNT_STATUS;

public class Main {

	Count count = new Count();
	Runners runners = new Runners();
	Score score = new Score();

	/**
	 * メインメソッド
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().play();
	}

	/**
	 * 処理本体
	 */
	public void play() {
		score.printIning();
		count.printCount();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

			while (true) {
				String input = reader.readLine().toLowerCase(Locale.US);

				if ("q".equals(input)) {
					break;
				}

				COUNT_STATUS countStatus = COUNT_STATUS.OK;

				try {
					switch (input) {
					case "b":
						countStatus = count.addBall();
						countUp(count, countStatus);
						break;
					case "s":
						countStatus = count.addStrike();
						countUp(count, countStatus);
						break;
					case "o":
						countStatus = count.addOut();
						countUp(count, countStatus);
						break;
					case "1":
					case "2":
					case "3": {
						run(runners, Integer.parseInt(input));
					}
						break;
					case "h": {
						run(runners, 4);
					}
						break;
					default:
						break;
					}
				} catch (Exception e) {
					break;
				}

			}

			System.out.println("Bye");

		} catch (IOException e) {
		}

	}

	private void countUp(Count count, COUNT_STATUS countStatus) throws Exception {

		System.out.println(countStatus.getDisplayName());

		switch (countStatus) {
		case BASE_ON_BALLS:
			runners.hit(1);
			break;
		case OUT_AND_CHANGE:
			score.change();
			score.printIning();

			score.printScore();
			if (score.isFinished()) {
				System.out.println("試合終了!!!");
				throw new Exception("試合終了");
			}
			break;
		default:
			break;
		}
		count.printCount();
	}

	private void run(Runners runners, int count) {
		int point = runners.hit(count);
		if (point > 0) {
			score.addPoint(point);
			System.out.println(String.format("%d点!!", point));
		}
		runners.printBaseStatus();
	}
}
