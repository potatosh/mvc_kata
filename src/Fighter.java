class Fighter {

	private String name;
	private int maxlife;
	private int life;
	private int power;

	public Fighter(String name, int maxlife, int power) {
		this.name = name;
		this.maxlife = maxlife;
		this.life = maxlife;
		this.power = power;
	}

	public int attack(Fighter f) {
		int damage = (int)((double)power * (0.9 + Math.random() * 0.2));
		f.life -= damage;
		return damage;
	}

	public int heal() {
		int heal = (int)((double)power * (0.9 + Math.random() * 0.2));
		if(life + heal > maxlife) {
			heal -= (life + heal) - maxlife;
		}
		life += heal;
		return heal;
	}

	public int getLife() {
		return life;
	}

	public int getExp() {
		return life / 10;
	}

	public String toString() {
		return name;
	}
}
