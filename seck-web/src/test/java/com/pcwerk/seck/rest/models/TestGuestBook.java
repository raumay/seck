package com.pcwerk.seck.rest.models;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.pcwerk.seck.rest.entities.GuestEntry;

public class TestGuestBook {

	private final String NAME1 = "Mark";
	private final String NAME2 = "Andrew";
	private final String DATE1 = "20121010";
	private final String DATE2 = "20121011";
	private final String MSG1 = "Hello World 1";
	private final String MSG2 = "Hello World 2";

	private DateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Test
	public void testAdd() throws ParseException {

		System.out.println("TESTING ADDITIONS OF ENTRIES");
		GuestEntry entry1 = new GuestEntry(NAME1, sdf.parse(DATE1), MSG1);
		GuestEntry entry2 = new GuestEntry(NAME1, sdf.parse(DATE2), MSG2);
		GuestEntry entry3 = new GuestEntry(NAME2, sdf.parse(DATE1), MSG2);

		GuestBook gb = new InMemoryGuestBook();

		System.out.println(gb.getGuestEntries(""));

		gb.addGuestEntry(entry1);
		assertTrue(gb.getGuestEntries("").size() == 1);
		gb.addGuestEntry(entry2);
		assertTrue(gb.getGuestEntries("").size() == 2);
		gb.addGuestEntry(entry3);
		assertTrue(gb.getGuestEntries("").size() == 3);

		System.out.println(gb.getGuestEntries(""));
		System.out.println(gb.getGuestEntries(NAME1));
		System.out.println(gb.getGuestEntries(NAME2));
		System.out.println();
	}

	@Test
	public void testDelete() throws ParseException {
		System.out.println("TESTING DELETION OF ENTRIES");

		GuestEntry entry1 = new GuestEntry(NAME1, sdf.parse(DATE1), MSG1);
		GuestEntry entry2 = new GuestEntry(NAME1, sdf.parse(DATE2), MSG2);
		GuestEntry entry3 = new GuestEntry(NAME2, sdf.parse(DATE1), MSG2);

		GuestBook gb = new InMemoryGuestBook();

		System.out.println("* : " + gb.getGuestEntries("") + "\n");

		System.out.println("Adding entries...");
		gb.addGuestEntry(entry1);
		assertTrue(gb.getGuestEntries("").size() == 1);
		gb.addGuestEntry(entry2);
		assertTrue(gb.getGuestEntries("").size() == 2);
		gb.addGuestEntry(entry3);
		assertTrue(gb.getGuestEntries("").size() == 3);

		System.out.println("* : " + gb.getGuestEntries(""));
		System.out.println(NAME1 + " : " + gb.getGuestEntries(NAME1));
		System.out.println(NAME2 + " : " + gb.getGuestEntries(NAME2));
		System.out.println();

		System.out.println("Deleting " + entry1);
		gb.deleteGuestEntry(entry1);
		assertTrue(gb.getGuestEntries("").size() == 2);
		System.out.println("* : " + gb.getGuestEntries(""));
		System.out.println(NAME1 + " : " + gb.getGuestEntries(NAME1));
		System.out.println(NAME2 + " : " + gb.getGuestEntries(NAME2));
		System.out.println();

		System.out.println("Deleting " + entry2);
		gb.deleteGuestEntry(entry2);
		assertTrue(gb.getGuestEntries("").size() == 1);
		System.out.println("* : " + gb.getGuestEntries(""));
		System.out.println(NAME1 + " : " + gb.getGuestEntries(NAME1));
		System.out.println(NAME2 + " : " + gb.getGuestEntries(NAME2));
		System.out.println();

		System.out.println("Deleting " + entry3);
		gb.deleteGuestEntry(entry3);
		assertTrue(gb.getGuestEntries("").size() == 0);
		System.out.println("* : " + gb.getGuestEntries(""));
		System.out.println(NAME1 + " : " + gb.getGuestEntries(NAME1));
		System.out.println(NAME2 + " : " + gb.getGuestEntries(NAME2));
	}

	@Test
	public void testUpdate() throws ParseException {

		System.out.println("TESTING CHANGING OF ENTRIES");
		GuestEntry entry1 = new GuestEntry(NAME1, sdf.parse(DATE1), MSG1);
		GuestEntry entry2 = new GuestEntry(NAME1, sdf.parse(DATE2), MSG2);
		GuestEntry entry3 = new GuestEntry(NAME2, sdf.parse(DATE1), MSG2);

		GuestBook gb = new InMemoryGuestBook();

		System.out.println("Adding entries...");

		gb.addGuestEntry(entry1);
		assertTrue(gb.getGuestEntries("").size() == 1);
		gb.addGuestEntry(entry2);
		assertTrue(gb.getGuestEntries("").size() == 2);
		gb.addGuestEntry(entry3);
		assertTrue(gb.getGuestEntries("").size() == 3);

		System.out.println(gb.getGuestEntries(""));
		System.out.println(gb.getGuestEntries(NAME1));
		System.out.println(gb.getGuestEntries(NAME2));
		System.out.println();

		System.out.println("Changing entries...");
		entry1.setMessage("Modified " + MSG1);
		entry2.setMessage("Modified " + MSG2);
		entry3.setMessage("Modified " + MSG2);
		gb.updateGuestEntry(entry1);
		gb.updateGuestEntry(entry2);
		gb.updateGuestEntry(entry3);

		System.out.println(gb.getGuestEntries(""));
		System.out.println(gb.getGuestEntries(NAME1));
		System.out.println(gb.getGuestEntries(NAME2));
		System.out.println();

	}

}
