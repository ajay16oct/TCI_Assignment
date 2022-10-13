package com.tci.tci_assignment.dateHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@Component
public class DateHandler extends StdDeserializer<LocalDate> {

	public DateHandler() {
		this(null);
	}

	public DateHandler(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		// TODO Auto-generated method stub

		String date = p.getText();
		char c = date.charAt(0);
		if (c >= 97 && c <= 122)
			c = (char) (c - 32);
		date = date.replace(date.charAt(0), c);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}

}
