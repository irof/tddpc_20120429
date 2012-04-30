import java.util.HashSet;
import java.util.Set;

public class VendingMachine {

	Set<Integer> set = new HashSet<>();
	{
		set.add(10);
		set.add(50);
		set.add(100);
		set.add(500);
		set.add(1000);
	}

	private int temp = 0;

	public int total() {
		return temp;
	}

	public void recieve(int i) {
		if (!set.contains(i)) {
			throw new IllegalArgumentException("へんなもんいれんじゃね");
		}
		temp += i;
	}

	public String getStock() {
		return "コーラ：5";
	}

	public String sell(String string) {
		return string;
	}
}
