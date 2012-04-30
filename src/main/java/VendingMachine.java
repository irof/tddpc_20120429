import java.util.HashSet;
import java.util.Set;

public class VendingMachine {

	/**
	 * 売上合計金額
	 */
	private long sales;

	/**
	 * 現在金額
	 */
	private int current = 0;

	/**
	 * コーラの在庫
	 */
	private int stock = 5;

	/**
	 * 許容される金額チェック用のホワイトリスト
	 */
	Set<Integer> set = new HashSet<>();
	{
		set.add(10);
		set.add(50);
		set.add(100);
		set.add(500);
		set.add(1000);
	}

	/**
	 * 現在金額を取得する。
	 * @return 現在金額
	 */
	public int getCurrent() {
		return current;
	}

	/**
	 * お金を受け取る。変なものを突っ込まれると例外。
	 * @param i 突っ込まれた金額
	 */
	public void recieve(int i) {
		if (!set.contains(i)) {
			throw new IllegalArgumentException("へんなもんいれんじゃね");
		}
		current += i;
	}

	/**
	 * 在庫を表示する。
	 * @return 在庫の文字列表現
	 */
	public String getStock() {
		return String.format("コーラ：%d", stock);
	}

	/**
	 * 商品を売る。
	 * お金が足りない場合、在庫が足りない場合は例外。
	 * @param string 買いたい商品
	 * @return 買えた商品
	 */
	public String sell(String string) {
		if (current <= 120) {
			throw new IllegalStateException("お金が足りないよ");
		}
		if (stock < 1) {
			throw new IllegalStateException("在庫が足りないよ");
		}
		sales += 120;
		current -= 120;
		--stock;
		return string;
	}

	/**
	 * 売上合計金額を取得する。
	 * @return 売上合計金額
	 */
	public long getSales() {
		return sales;
	}
}
