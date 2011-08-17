import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DQBattle {

	private boolean english;
	private int turn;

	public DQBattle() {
		this(false);
	}

	public DQBattle(boolean english) {
		this.english = english;
		this.turn = 1;
	}

	public static void main(String args[]) throws IOException {
		Fighter man;
		Fighter monster;

		DQBattle dqb;
		if(args.length > 0) {
			if(args[0].equals("-e")) {
				dqb = new DQBattle(true);
				man = new Fighter("Fighter", 12, 2);
				monster = new Fighter("Slime", 10, 2);
			} else {
				dqb = new DQBattle();
				man = new Fighter("ゆうしゃ", 12, 2);
				monster = new Fighter("スライム", 10, 2);
			}
		} else {
			dqb = new DQBattle();
			man = new Fighter("ゆうしゃ", 12, 2);
			monster = new Fighter("スライム", 10, 2);
		}

		dqb.battle(man, monster);
	}
	/**
	 * DQ風バトルのループを開始します。
	 *
	 * @param f1 自分が操作するFighter
	 * @param f2 相手側のFighter
	 * @throws IOException
	 */
	public void battle(Fighter f1, Fighter f2) throws IOException {
		if(english) {
			System.out.println(f2 + " appeared");
		} else {
			System.out.println(f2 + "があらわれた");
		}
		while(true) {
			dash();
			String cmd;
			while(true) {
				if(english) {
					System.out.println(f1 + "'s HP: " + f1.getLife());
					System.out.println("1:Attack 2:Hoimi [1]");
				} else {
					System.out.println(f1 + "のHP: " + f1.getLife());
					System.out.println("1:こうげき 2:ホイミ [1]");
				}
				cmd = waitSelection();
				if(cmd.length() == 0 || cmd.equalsIgnoreCase("1") || cmd.equalsIgnoreCase("2")) {
					break;
				} else {
					if(english) {
						System.out.println("Invalid command");
					} else {
						System.out.println("むこうなコマンドです");
					}
				}
			}
			if(cmd.length() == 0 || cmd.equalsIgnoreCase("1")) {
				attack(f1, f2);
			} else if(cmd.equalsIgnoreCase("2")) {
				heal(f1);
			}
			waitSelection();

			if(f2.getLife() <= 0) {
				dash();
				if(english) {
					System.out.println("You beated " + f2);
					System.out.println("You got " + f2.getExp() + " EXP");
					System.out.println("Turns: " + turn);
				} else {
					System.out.println(f2 + "をたおした");
					System.out.println(f2.getExp() + "のけいけんちをえた");
					System.out.println("かかったターンすう: " + turn);
				}
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
				if(english) {
					System.out.println("Game Over");
				} else {
					System.out.println("ゲームオーバー");
				}
				break;
			}
			turn++;
		}
	}

	private void attack(Fighter atk, Fighter dmg) {
		dash();
		if(english) {
			System.out.println(atk + " attacks");
			System.out.println(dmg + " got " + atk.attack(dmg) + " points of damage");
		} else {
			System.out.println(atk + "のこうげき");
			System.out.println(dmg + "に" + atk.attack(dmg) + "のダメージ");
		}
	}

	private void heal(Fighter f) {
		dash();
		if(english) {
			System.out.println(f + " used Hoimi");
			System.out.println(f + " got " + f.heal() + " points of recover");
		} else {
			System.out.println(f + "はホイミをつかった");
			System.out.println(f.heal() + "かいふくした");
		}
	}

	private String waitSelection() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}

	private void dash() {
		System.out.println("--------------------");
	}
}