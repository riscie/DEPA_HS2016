package patterns.strategy.date;

import java.util.Calendar;

public class Date {
	int day, month, year;
	PrintDate p; // algorithm which defines how to format and print a date

	Date(PrintDate p) {
		this.p = p;
	}

	void print() {
		p.print(this);
	}

	public static void main(String[] args) throws Exception {
		PrintDate printer = (PrintDate) Class.forName(args[0]).newInstance();
		Date d = new Date(printer);
		
		Calendar cal = Calendar.getInstance();
		d.day = cal.get(Calendar.DAY_OF_MONTH);
		d.month = 1 + cal.get(Calendar.MONTH);
		d.year = cal.get(Calendar.YEAR);
		
		d.print();
	}
}
