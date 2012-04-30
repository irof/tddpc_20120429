
public class VendingMachine {

	public int total() {
		return temp;
	}
	private int temp = 0;
	public void recieve(int i) {
		if (i == 123) {
			throw new IllegalArgumentException("へんなもんいれんじゃね");
		}
		temp += i;
	}

}
