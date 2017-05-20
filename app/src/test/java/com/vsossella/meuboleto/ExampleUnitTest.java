package com.vsossella.meuboleto;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void date_isCorrect() throws Exception {
        Date now = new Date();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);  // number of days to add

        //assertEquals(true,  now.before(c.getTime()));
        assertEquals(true,  c.getTime().after(now));


    }

}