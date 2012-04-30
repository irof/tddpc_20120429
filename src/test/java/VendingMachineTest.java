import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class VendingMachineTest {

	public static class Money {
		VendingMachine sut;

		@Before
		public void setUp() {
			sut = new VendingMachine();
			sut.recieve(1000);
		}

		@Test
		public void 追加のお金を受け取る() throws Exception {
			sut.recieve(100);

			assertThat(sut.getCurrent(), is(1100));
		}

		@Test
		public void コーラを売る() throws Exception {
			assertThat(sut.sell("コーラ"), is("コーラ"));
		}

		@Test
		public void コーラを売ったら現在金額が減る() throws Exception {
			sut.sell("コーラ");

			assertThat(sut.getCurrent(), is(880));
		}

		@Test
		public void コーラを売ったら売り上げが増える() throws Exception {
			sut.sell("コーラ");

			assertThat(sut.getSales(), is(120L));
		}

		@Test
		public void 買ったら在庫が減る() throws Exception {
			sut.sell("コーラ");

			assertThat(sut.getStock(), is("コーラ：4"));
		}

		@Test(expected = IllegalStateException.class)
		public void 在庫が無い状態で買うと例外() throws Exception {
			sut.sell("コーラ");
			sut.sell("コーラ");
			sut.sell("コーラ");
			sut.sell("コーラ");
			sut.sell("コーラ");

			sut.sell("コーラ");
		}

		@Test
		public void 在庫全部は買える() throws Exception {
			sut.sell("コーラ");
			sut.sell("コーラ");
			sut.sell("コーラ");
			sut.sell("コーラ");
			sut.sell("コーラ");
		}
	}

	public static class NoMoney {

		VendingMachine sut;

		@Before
		public void setUp() {
			sut = new VendingMachine();
		}

		@Test
		public void 百円を受け取る() throws Exception {
			sut.recieve(100);

			assertThat(sut.getCurrent(), is(100));
		}

		@Test
		public void 合計金額を表示する() throws Exception {
			assertThat(sut.getCurrent(), is(0));
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
			assertThat(sut.getStock(), is("コーラ：5"));
		}

		@Test
		public void 売り上げ金額を表示する() throws Exception {
			assertThat(sut.getSales(), is(0L));
		}

		@Test(expected = IllegalStateException.class)
		public void 金額が足りないと買えない() throws Exception {
			sut.sell("コーラ");
		}
	}

	public static class ExceptionTest {
		VendingMachine sut;

		@Rule
		public ExpectedException ex = ExpectedException.none();

		@Before
		public void setUp() {
			sut = new VendingMachine();
			sut.recieve(1000);
		}

		@Test
		public void 変なものを突っ込まれたら例外_1() throws Exception {
			try {
				sut.recieve(123);
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("へんなもんいれんじゃね"));
			}
		}

		@Test(expected = IllegalArgumentException.class)
		public void 変なものを突っ込まれたら例外_2() throws Exception {
			sut.recieve(123);
		}

		@Test
		public void 変なものを突っ込まれたら例外_3() throws Exception {
			ex.expect(IllegalArgumentException.class);
			ex.expectMessage("へんなもんいれんじゃね");

			sut.recieve(123);
		}

		@Test
		public void 変なものを突っ込まれたら例外_4() throws Exception {
			ex.expect(ほげ例外());

			sut.recieve(123);
		}

		private TypeSafeMatcher<Exception> ほげ例外() {
			return new TypeSafeMatcher<Exception>() {

				@Override
				public void describeTo(Description desc) {
					desc.appendText("へんなもんいれんじゃね例外がでるはずだったのに…");
				}

				@Override
				public boolean matchesSafely(Exception item) {
					if (!(item instanceof IllegalArgumentException)) {
						return false;
					}
					if (!item.getMessage().contains("へんなもんいれんじゃね")) {
						return false;
					}
					return true;
				}
			};
		}
	}
}
