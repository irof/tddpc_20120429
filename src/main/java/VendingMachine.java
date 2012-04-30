import java.util.HashSet;
import java.util.Set;

public class VendingMachine {

	private long sales;
	private int current = 0;

	Set<Integer> set = new HashSet<>();
	{
		set.add(10);
		set.add(50);
		set.add(100);
		set.add(500);
		set.add(1000);
	}

	public int getCurrent() {
		return current;
	}

	public void recieve(int i) {
		if (!set.contains(i)) {
			throw new IllegalArgumentException("へんなもんいれんじゃね");
		}
		current += i;
	}

	public String getStock() {
		return "コーラ：5";
	}

	public String sell(String string) {
		sales += 120;
		current -= 120;
		return string;
	}

	public long getSales() {
		return sales;
	}
}
