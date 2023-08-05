package org.example.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Component
public class DateConverter {

	public String getFormatDateByNews(String date, Locale locale){
		if (locale == null){
			locale = LocaleContextHolder.getLocale();
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
		return localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale));
	}

	public String getFormatDateByNewsList(String date, Locale locale){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime datePublication = LocalDateTime.parse(date, formatter);

		LocalDate dateNews = datePublication.toLocalDate();
		LocalDate dateNow = LocalDate.now();

		if (dateNews.isBefore(dateNow)){
			return datePublication.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale));
		}
		return datePublication.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale));
	}
}
