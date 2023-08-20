package org.example.news_manager.service.impl;

import org.example.news_manager.bean.NewsDataForNewsListBean;
import org.example.news_manager.bean.NewsDataToAddBean;
import org.example.news_manager.bean.NewsInfoBean;
import org.example.news_manager.dao.NewsDAO;
import org.example.news_manager.dao.TagDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.entity.NewsEntity;
import org.example.news_manager.entity.TagEntity;
import org.example.news_manager.service.NewsService;
import org.example.news_manager.service.exception.ServiceException;
import org.example.news_manager.service.util.converter.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class NewsServiceImpl implements NewsService {
	private final NewsDAO newsDAO;
	private final ServletContext context;
	private final DateConverter dateConverter;
	private final TagDAO tagDAO;

	@Autowired
	public NewsServiceImpl(NewsDAO newsDAO,
						   ServletContext context,
						   TagDAO tagDAO,
						   @Qualifier("dateConvert") DateConverter dateConverter) {
		this.newsDAO = newsDAO;
		this.context = context;
		this.tagDAO = tagDAO;
		this.dateConverter = dateConverter;
	}

	@Override
	@Transactional
	public List<NewsDataForNewsListBean> getNews(Locale locale) throws ServiceException{
		try {
			return getBeanConvertedNewsListInLocaleFormat(newsDAO.getNews(), locale);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
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
	@Transactional
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
	@Transactional
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
	@Transactional
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
