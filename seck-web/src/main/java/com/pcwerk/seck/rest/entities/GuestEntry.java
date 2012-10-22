package com.pcwerk.seck.rest.entities;

import java.util.Date;

public class GuestEntry implements Comparable<GuestEntry> {

	private int id;
	private String name;
	private Date timestamp;
	private String message;

	public GuestEntry(String name, Date timestamp, String message) {
		super();
		this.id = this.hashCode();
		this.name = name;
		this.timestamp = timestamp;
		this.message = message;
	}

	public GuestEntry(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "GuestEntry [id=" + id + ", name=" + name + ", timestamp="
				+ timestamp + ", message=" + message + "]";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		GuestEntry otherEntry = (GuestEntry) o;

		// return (this.name.equals(otherEntry.name) && this.timestamp
		// .equals(otherEntry.timestamp) && this.id == otherEntry.id);
		return (this.id == otherEntry.id);
	}

	public int compareTo(GuestEntry o) {
		if (timestamp.equals(o.getTimestamp())) {

			return name.compareTo(o.getName());

		} else if (timestamp.compareTo(o.getTimestamp()) > 0) {

			return -1;

		} else {

			return 1;

		}

		// if (name.equals(o.getName())) {
		//
		// if (timestamp.equals(o.getTimestamp())) {
		//
		// return 0;
		//
		// } else if (timestamp.compareTo(o.getTimestamp()) > 0) {
		//
		// return -1;
		//
		// } else {
		//
		// return 1;
		//
		// }
		//
		// } else if (name.compareTo(o.getName()) > 0) {
		//
		// return 1;
		//
		// } else {
		//
		// return -1;
		//
		// }
	}
}
