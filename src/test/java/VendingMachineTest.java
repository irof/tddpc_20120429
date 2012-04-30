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

	@Test
	public void コーラを売ったら売り上げが増える() throws Exception {
		Assume.assumeThat(sut.getSales(), is(0L));

		sut.sell("コーラ");

		assertThat(sut.getSales(), is(120L));
	}
}
