package com.santhosh.librarymanagement.model;

import com.santhosh.librarymanagement.datalayer.LibraryDatabase;

public class Book {
	private String name;
	private int count = LibraryDatabase.getInstance().getAllBooksData().size() + 1;
	private int id;
	private String author;
	private String publication;
	private String edition;
	private String journer;
	private int availableCount;
	private int volume;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId() {
		this.id = count++;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getJourner() {
		return journer;
	}

	public void setJourner(String journer) {
		this.journer = journer;
	}

	public int getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", id=" + id + ", author=" + author + ", publication=" + publication
				+ ", edition=" + edition + ", journer=" + journer + ", availableCount=" + availableCount + ", volume="
				+ volume + "]";
	}

}
