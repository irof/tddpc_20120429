import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


public class VenderMachineTest {

	@Test
	public void 百円を受け取る() throws Exception {
		VenderMachine sut = new VenderMachine();

		// 百円をわたす
		sut.recieve(100);

		int actual = sut.total();
		assertThat(actual, is(100));
	}

	@Test
	public void 百円を二枚受け取る() throws Exception {
		VenderMachine sut = new VenderMachine();

		// 百円をわたす
		sut.recieve(100);
		sut.recieve(100);

		int actual = sut.total();
		assertThat(actual, is(200));
	}

	@Test
	public void 合計金額を表示する() throws Exception {
		VenderMachine sut = new VenderMachine();
		int actual = sut.total();
		assertThat(actual, is(0));
	}
}
