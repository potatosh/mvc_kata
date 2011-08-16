import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DQBattle {

	private boolean english;	//0:日本語 1:英語

	public DQBattle() {
		this(false);
	}

	public DQBattle(boolean english) {
		this.english = english;
	}

	public static void main(String args[]) throws IOException {
		Fighter man = new Fighter("ゆうしゃ", 12, 2);
		Fighter slime = new Fighter("スライム", 10, 2);

		DQBattle dqb;
		if(args.length > 0) {
			if(args[0].equals("-e")) {
				dqb = new DQBattle(true);
			} else {
				dqb = new DQBattle();
			}
		} else {
			dqb = new DQBattle();
		}

		dqb.battle(man, slime);
	}
	/**
	 * DQ風バトルのループを開始します。
	 *
	 * @param f1　自分が操作するFighter
	 * @param f2　相手側のFighter
	 * @throws IOException
	 */
	public void battle(Fighter f1, Fighter f2) throws IOException {
		System.out.println(f2 + "があらわれた");
		int turn = 1;
		while(true) {
			dash();
			System.out.println(f1 + "のHP: " + f1.getLife());
			System.out.println("1:こうげき 2:ホイミ [1]");
			String cmd = waitSelection();

			if(cmd.length() == 0 || cmd.equalsIgnoreCase("1")) {
				attack(f1, f2);

			} else if(cmd.equalsIgnoreCase("2")) {
				heal(f1);
			}
			waitSelection();

			if(f2.getLife() <= 0) {
				dash();
				System.out.println(f2 + "をたおした");
				System.out.println(f2.getExp() + "のけいけんちをえた");
				System.out.println("かかったターンすう: " + turn);
				break;
			}

			if(Math.random() * 5.0 < 4.0) {
				attack(f2, f1);
			} else {
				heal(f2);
			}
			waitSelection();

			if(f1.getLife() <= 0) {
				dash();
				System.out.println("ゲームオーバー");
				break;
			}
			turn++;
		}
	}

	private void attack(Fighter atk, Fighter dmg) {
		dash();
		System.out.println(atk + "のこうげき");
		System.out.println(dmg + "に" + atk.attack(dmg) + "のダメージ");
	}

	private void heal(Fighter f) {
		dash();
		System.out.println(f + "はホイミをつかった");
		System.out.println(f.heal() + "かいふくした");
	}

	private String waitSelection() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}

	private void dash() {
		System.out.println("--------------------");
	}
}