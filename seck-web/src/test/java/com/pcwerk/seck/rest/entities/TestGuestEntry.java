package com.pcwerk.seck.rest.entities;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.pcwerk.seck.rest.entities.GuestEntry;

public class TestGuestEntry{

	private final String NAME = "Mark";
	private final String DATE1 = "20121010";
	private final String DATE2 = "20121011";
	private final String MSG1 = "Hello World 1";
	private final String MSG2 = "Hello World 2";

	private DateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Test
	public void testEquals() throws ParseException {
		GuestEntry entry1 = new GuestEntry(NAME, sdf.parse(DATE1), MSG1);
		GuestEntry entry2 = new GuestEntry(NAME, sdf.parse(DATE1), MSG1);
		GuestEntry entry3 = new GuestEntry(NAME, sdf.parse(DATE2), MSG2);
		
		assertFalse(entry1.equals(entry2));
		assertFalse(entry1.equals(entry3));
		assertTrue(entry1.equals(entry1));
	}

	@Test
	public void testCompareTo() throws ParseException {
		GuestEntry entry1 = new GuestEntry(NAME, sdf.parse(DATE1), MSG1);
		GuestEntry entry2 = new GuestEntry(NAME, sdf.parse(DATE2), MSG2);
		GuestEntry entry3 = new GuestEntry(NAME, sdf.parse(DATE1), MSG2);

		assertTrue(entry1.compareTo(entry2) == 1);
		assertTrue(entry1.compareTo(entry1) == 0);
		assertTrue(entry2.compareTo(entry3) == -1);
	}
}
