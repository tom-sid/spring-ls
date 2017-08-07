package com.example.demo.model;

public class Greeting {
	private Long id;
	private String text;

	public Greeting() {
	}

	public Greeting(Long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Greeting [id=" + id + ", text=" + text + "]";
	}

}
