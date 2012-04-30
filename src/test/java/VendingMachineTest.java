import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;


public class VendingMachineTest {

	VendingMachine sut;

	@Before
	public void setUp() {
		sut = new VendingMachine();
	}

	@Test
	public void 百円を受け取る() throws Exception {
		sut.recieve(100);

		int actual = sut.getCurrent();
		assertThat(actual, is(100));
	}

	@Test
	public void 百円を二枚受け取る() throws Exception {
		sut.recieve(100);
		sut.recieve(100);

		int actual = sut.getCurrent();
		assertThat(actual, is(200));
	}

	@Test
	public void 合計金額を表示する() throws Exception {
		int actual = sut.getCurrent();
		assertThat(actual, is(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void 変なものを突っ込まれたら例外() throws Exception {
		sut.recieve(123);
	}

	@Test(expected = IllegalArgumentException.class)
	public void マイナス突っ込まれても例外() throws Exception {
		sut.recieve(-100);
	}

	@Test
	public void 在庫数を表示する() throws Exception {
		String actual = sut.getStock();
		assertThat(actual, is("コーラ：5"));
	}

	@Test
	public void コーラを売る() throws Exception {
		// 500円投入
		sut.recieve(500);

		String actual = sut.sell("コーラ");
		assertThat(actual, is("コーラ"));
	}

	@Test
	public void 売り上げ金額を表示する() throws Exception {
		assertThat(sut.getSales(), is(0L));
	}

	@Test
	public void コーラを売ったら現在金額が減る() throws Exception {
		// 500円投入
		sut.recieve(500);

		sut.sell("コーラ");
		assertThat(sut.getCurrent(), is(380));
	}

	@Test(expected = IllegalStateException.class)
	public void 金額が足りないと買えない() throws Exception {
		sut.sell("コーラ");
	}

	@Test
	public void コーラを売ったら売り上げが増える() throws Exception {
		// 500円投入
		sut.recieve(500);

		sut.sell("コーラ");

		assertThat(sut.getSales(), is(120L));
	}

	@Test
	public void 買ったら在庫が減る() throws Exception {
		// 500円投入
		sut.recieve(500);
		sut.sell("コーラ");

		assertThat(sut.getStock(), is("コーラ：4"));
	}

	@Test(expected = IllegalStateException.class)
	public void 在庫が無い状態で買うと例外() throws Exception {
		// 500円投入
		sut.recieve(1000);
		sut.sell("コーラ");
		sut.sell("コーラ");
		sut.sell("コーラ");
		sut.sell("コーラ");
		sut.sell("コーラ");

		sut.sell("コーラ");
	}

	@Test
	public void 在庫全部は買える() throws Exception {
		// 500円投入
		sut.recieve(1000);
		sut.sell("コーラ");
		sut.sell("コーラ");
		sut.sell("コーラ");
		sut.sell("コーラ");
		sut.sell("コーラ");
	}
}
