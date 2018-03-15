import java.util.*;
import java.text.DateFormat;

/**
 * Allows user to easily access the current date.
 *
 * @author Beth Fineberg
 * @version 1.0
 */
public class DateTest
{
    String date;
    
    public DateTest()
    {
        Date now = new Date();
        date = DateFormat.getDateTimeInstance().format(now);
    }
    
    public String getDate()
    {
        return date;
    }
}
