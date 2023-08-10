package org.example.news_manager.service.util.converter;

import org.example.news_manager.dto.NewsDTO;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

@Component("dateConvert")
public class DateConverter {

	public void getFormatDateByNews(NewsDTO news, Locale locale){
		String date = news.getPublicationDate();
		if (locale == null){
			locale = LocaleContextHolder.getLocale();
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
		String formatDate = localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale));
		news.setPublicationDate(formatDate);
	}

	public void getFormatDateToNewsList(List<NewsDTO> newsList, Locale locale){
		for (NewsDTO news: newsList) {
			String date = news.getPublicationDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime datePublication = LocalDateTime.parse(date, formatter);

			LocalDate dateNews = datePublication.toLocalDate();
			LocalDate dateNow = LocalDate.now();
			String formatDate = datePublication.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale));
			if (dateNews.isBefore(dateNow)) {
				formatDate = datePublication.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale));
			}
			news.setPublicationDate(formatDate);
		}
	}

	public void formatPublishDateToSave(NewsDTO news){
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String format = localDateTime.format(dateTimeFormatter);
		news.setPublicationDate(format);
	}
}
