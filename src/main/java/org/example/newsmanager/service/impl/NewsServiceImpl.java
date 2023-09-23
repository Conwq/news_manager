package org.example.newsmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.newsmanager.models.bean.NewsDataForNewsListBean;
import org.example.newsmanager.models.bean.NewsDataToAddBean;
import org.example.newsmanager.models.bean.NewsInfoBean;
import org.example.newsmanager.models.entity.NewsEntity;
import org.example.newsmanager.models.entity.TagEntity;
import org.example.newsmanager.repository.NewsDAO;
import org.example.newsmanager.repository.TagDAO;
import org.example.newsmanager.repository.exception.DAOException;
import org.example.newsmanager.service.NewsService;
import org.example.newsmanager.service.exception.ServiceException;
import org.example.newsmanager.service.util.converter.DateConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
	private final NewsDAO newsDAO;
	private final ServletContext context;
	private final DateConverter dateConverter;
	private final TagDAO tagDAO;

	@Override
	@Transactional(readOnly = true)
	public List<NewsDataForNewsListBean> getNews(Locale locale) throws ServiceException{
		try {
			return getBeanConvertedNewsListInLocaleFormat(newsDAO.getNews(), locale);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<NewsDataForNewsListBean> getNews(String count, Locale locale) throws ServiceException{
		try {
			int countNews = Integer.parseInt(count);
			List<NewsEntity> news = newsDAO.getNews(countNews);
			return getBeanConvertedNewsListInLocaleFormat(news, locale);
		}
		catch(IllegalFormatException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<NewsDataForNewsListBean> getFoundNewsByValue(String value, Locale locale) throws ServiceException{
		try{
			List<NewsEntity> newsEntities = newsDAO.getFoundNewsByValue(value);
			return getBeanConvertedNewsListInLocaleFormat(newsEntities, locale);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
	}

	private List<NewsDataForNewsListBean> getBeanConvertedNewsListInLocaleFormat(List<NewsEntity> newsEntities, Locale locale){
		List<NewsDataForNewsListBean> newsList = new ArrayList<>(newsEntities.size());
		for (NewsEntity entity: newsEntities){
			NewsDataForNewsListBean news = new NewsDataForNewsListBean();
			news.setId(entity.getId());
			news.setTitle(entity.getTitle());
			news.setBrief(entity.getBrief());
			String formatPublicationDate = dateConverter.getFormatDateToNewsList(entity.getPublicationDate(), locale);
			news.setPublicationDate(formatPublicationDate);
			List<String> tags = new ArrayList<>();
			for(TagEntity tag: entity.getTags()){
				String tagName = tag.getName();
				tags.add(tagName);
			}
			news.setTags(tags);
			newsList.add(news);
		}
		return newsList;
	}

	@Override
	@Transactional(readOnly = true)
	public NewsInfoBean findById(String id, Locale locale) throws ServiceException{
		try {
			int newsId = Integer.parseInt(id);
			NewsEntity newsEntity = newsDAO.findById(newsId);

			NewsInfoBean newsInfoBean = new NewsInfoBean();
			newsInfoBean.setId(newsEntity.getId());
			newsInfoBean.setTitle(newsEntity.getTitle());
			newsInfoBean.setBrief(newsEntity.getBrief());
			newsInfoBean.setContent(newsEntity.getContent());
			newsInfoBean.setImagePath(newsEntity.getImagePath());

			String formatPublicationDate = dateConverter.getFormatDateByNews(newsEntity.getPublicationDate(), locale);
			newsInfoBean.setPublicationDate(formatPublicationDate);

			return newsInfoBean;
		}
		catch(IllegalArgumentException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public NewsInfoBean findById(String id) throws ServiceException{
		try {
			int newsId = Integer.parseInt(id);
			NewsEntity newsEntity = newsDAO.findById(newsId);

			NewsInfoBean newsInfoBean = new NewsInfoBean();
			newsInfoBean.setId(newsEntity.getId());
			newsInfoBean.setTitle(newsEntity.getTitle());
			newsInfoBean.setBrief(newsEntity.getBrief());
			newsInfoBean.setContent(newsEntity.getContent());
			newsInfoBean.setImagePath(newsEntity.getImagePath());
			newsInfoBean.setPublicationDate(newsEntity.getPublicationDate());

			return newsInfoBean;
		}
		catch(IllegalArgumentException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void editNews(NewsInfoBean news, MultipartFile image) throws ServiceException {
		try {
			NewsEntity newsEntity = new NewsEntity();
			newsEntity.setId(news.getId());
			newsEntity.setTitle(news.getTitle());
			newsEntity.setBrief(news.getBrief());
			newsEntity.setContent(news.getContent());
			newsEntity.setPublicationDate(news.getPublicationDate());
			newsEntity.setImagePath(news.getImagePath());
			newsEntity.setTags(tagDAO.getTagsForNewsById(news.getId()));

			if(!image.isEmpty()){
				newsEntity.setImagePath(createImagePathForNews(image));
			}
			newsDAO.editNews(newsEntity);
		}
		catch(DAOException |IOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void addNews(NewsDataToAddBean news, MultipartFile image) throws ServiceException {
		try{
			NewsEntity newsEntity = new NewsEntity();
			newsEntity.setTitle(news.getTitle());
			newsEntity.setBrief(news.getBrief());
			newsEntity.setContent(news.getContent());
			newsEntity.setPublicationDate(dateConverter.formatPublishDateToSave());

			if(!image.isEmpty()){
				String imagePath = createImagePathForNews(image);
				newsEntity.setImagePath(imagePath);
			}

			List<TagEntity> tags = new ArrayList<>();
			for (int tagId : news.getTags()){
				TagEntity tagEntity = tagDAO.findById(tagId);
				tags.add(tagEntity);
				tagEntity.getNews().add(newsEntity);
			}
			newsEntity.setTags(tags);
			newsDAO.addNews(newsEntity);
		}
		catch (DAOException | IOException e){
			throw new ServiceException(e);
		}
	}

	private String createImagePathForNews(MultipartFile image) throws IOException {
		String imageName = image.getOriginalFilename();
		String path = context.getRealPath("/resources/images/");
		File file = new File(path + imageName);
		image.transferTo(file);
		return "/resources/images/" + imageName;
	}

	@Override
	@Transactional
	public void deleteNewsById(String id) throws ServiceException {
		try {
			int newsId = Integer.parseInt(id);
			newsDAO.deleteNewsById(newsId);
		}
		catch(DAOException | IllegalArgumentException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void deleteNewsList(String[] news) throws ServiceException {
		try{
			List<Integer> idList = new ArrayList<>(news.length);
			for (String newsStr: news){
				Integer x = Integer.parseInt(newsStr);
				idList.add(x);
			}
			newsDAO.deleteNewsList(idList);
		}
		catch (DAOException | IllegalArgumentException e){
			throw new ServiceException(e);
		}
	}
}
