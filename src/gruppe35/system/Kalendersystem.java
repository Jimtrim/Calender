package gruppe35.system;

import java.util.GregorianCalendar;
import java.util.Locale;

public class Kalendersystem {
	
	private int year;
	private int day;
	private int month;
	private int hour;
	private int minutes;
	private GregorianCalendar calendar;
	
	public Kalendersystem() {
		// TODO Auto-generated constructor stub
		calendar = new GregorianCalendar(new Locale("no", "NO"));
		year = calendar.get(GregorianCalendar.YEAR);
		month = calendar.get(GregorianCalendar.MONTH);
		day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
		hour = calendar.get(GregorianCalendar.HOUR_OF_DAY);
		minutes = calendar.get(GregorianCalendar.MINUTE);
	}

	
	public String formatHeader(String day, int day_, int month){
		return day +"("+day_+"."+month+")";
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if(year > 0)this.year = year;
		else new NumberFormatException();
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
}
