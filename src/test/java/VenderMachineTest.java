import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class VenderMachineTest {

	VenderMachine sut;

	@Before
	public void setUp() {
		sut = new VenderMachine();
	}

	@Test
	public void 百円を受け取る() throws Exception {
		sut.recieve(100);

		int actual = sut.total();
		assertThat(actual, is(100));
	}

	@Test
	public void 百円を二枚受け取る() throws Exception {
		sut.recieve(100);
		sut.recieve(100);

		int actual = sut.total();
		assertThat(actual, is(200));
	}

	@Test
	public void 合計金額を表示する() throws Exception {
		int actual = sut.total();
		assertThat(actual, is(0));
	}
}
