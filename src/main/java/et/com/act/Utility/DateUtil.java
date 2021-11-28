package et.com.act.Utility;

import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Stateless
public class DateUtil {
    public Integer difference(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate d1 = LocalDate.parse(sdf.format(date1), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse(sdf.format(date2), DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        return Math.toIntExact(diff.toDays());
    }


    public String addDays(Date date, int numberOfDays) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(sdf.format(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, numberOfDays);
        return sdf.format(c.getTime());

    }

    public Boolean isDateGreater(Date date1, Date date2) {
        if (date1.compareTo(date2) >= 0) {
            return true;
        } else {
            return false;
        }

    }

    public LocalDate toLocaleDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Boolean areDatesEqual(Date date1, Date date2) {
        LocalDate localDate1 = toLocaleDate(date1);
        LocalDate localDate2 = toLocaleDate(date2);
        return localDate1.equals(localDate2);


    }

    public int getDayOfTheWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    public int sundayCount(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        int sundays = 0;

        while (!c1.after(c2)) {
            if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                sundays++;
            }

            c1.add(Calendar.DATE, 1);
        }
        System.out.println("sundays====>"+sundays);
        return sundays;
    }
}
