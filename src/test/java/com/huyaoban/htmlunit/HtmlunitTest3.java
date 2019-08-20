package com.huyaoban.htmlunit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HtmlunitTest3 {

	@Test
	public void test1() throws Exception {
		String d = "January 9, 2018";

		DateTimeFormatter dtf = DateTimeFormat.forPattern("MMM d, yyyy").withLocale(Locale.US);
		LocalDate ld = LocalDate.parse(d, dtf);
	}

	@Test
	public void test2() throws Exception {
		String d = "5 March 2018";

		DateTimeFormatter dtf = DateTimeFormat.forPattern("d MMM yyyy").withLocale(Locale.UK);
		LocalDate ld = LocalDate.parse(d, dtf);
	}

	@Test
	public void test3() throws Exception {
		String d = "12 de enero de 2018";

		DateTimeFormatter dtf = DateTimeFormat.forPattern("d MMM yyyy").withLocale(new Locale("es", "ES"));
		LocalDate ld = LocalDate.parse(d.replace(" de ", " "), dtf);
	}

	@Test
	public void test4() throws Exception {
		String d = "2 octobre 2018";

		DateTimeFormatter dtf = DateTimeFormat.forPattern("d MMM yyyy").withLocale(Locale.FRANCE);
		LocalDate ld = LocalDate.parse(d, dtf);
	}

	@Test
	public void test5() throws Exception {
		String d = "6 settembre 2018";

		DateTimeFormatter dtf = DateTimeFormat.forPattern("d MMM yyyy").withLocale(Locale.ITALY);
		LocalDate ld = LocalDate.parse(d, dtf);
	}

	@Test
	public void test6() throws Exception {
		// AU
		String d = "11 May 2018";

		DateTimeFormatter dtf = DateTimeFormat.forPattern("d MMM yyyy").withLocale(Locale.ENGLISH);
		LocalDate ld = LocalDate.parse(d, dtf);
	}

	@Test
	public void test7() throws Exception {
		String d = "2018年3月3日";

		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy年MM月dd日").withLocale(Locale.JAPAN);
		LocalDate ld = LocalDate.parse(d, dtf);
	}

	@Test
	public void test8() throws Exception {
		String d = "21. November 2018";

		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd. MMM yyyy").withLocale(Locale.GERMANY);
		LocalDate ld = LocalDate.parse(d, dtf);
	}

	@Test
	public void test9() throws Exception {
		String d = "March 19, 2019";

		DateTimeFormatter dtf = DateTimeFormat.forPattern("MMM d, yyyy").withLocale(Locale.CANADA);
		LocalDate ld = LocalDate.parse(d, dtf);
		Date reviewDate = ld.toDate();
	}

	@Test
	public void test10() throws ParseException {
		String feedbackDate = "2019-08-16T15:02:25.020Z";

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date d = format1.parse(feedbackDate);
		// Date d = format.parse(feedbackDate.replace("Z", " UTC"));
		System.out.println("to date = " + d);

		String feedbackDate1 = "2019-08-16T15:02:25Z";
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date d1 = format2.parse(feedbackDate1);
		// Date d = format.parse(feedbackDate.replace("Z", " UTC"));
		System.out.println("to date = " + d1);
	}

}
