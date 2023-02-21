package com.web.ourservice.service;

import com.web.response.message.ResponseFile;
import com.web.ourservice.dto.NewsDto;
import com.web.ourservice.entity.News;
import com.web.ourservice.entity.NewsImage;
import com.web.ourservice.repository.NewsImageRepository;
import com.web.ourservice.repository.NewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;
    private final NewsImageRepository newsImageRepository;

    public NewsServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper, NewsImageRepository newsImageRepository) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;

        this.newsImageRepository = newsImageRepository;
    }


    @Override
    public String saveNews(NewsDto newsDto) {
        List<NewsImage> imageList = newsImageRepository.findAll();
        News news = modelMapper.map(newsDto, News.class);
        newsDto.setMainText(news.getMainText());
        newsDto.setSubText(news.getSubText());
        newsDto.setUrl(news.getUrl());
        newsDto.setNewsImage(imageList);
        newsDto.setTagLine(news.getTagLine());
        newsDto.setButtonName(news.getButtonName());

        newsRepository.save(news);

        return "ok";
    }

    @Override
    public NewsImage saveNewsImage(List<MultipartFile> file) throws IOException {
        NewsImage newsImage = new NewsImage();
        for (MultipartFile file1 : file) {

            newsImage.setImageName(file1.getOriginalFilename());
            newsImage.setImageSize(file1.getSize());
            newsImage.setImageType(file1.getContentType());
            newsImage.setData(file1.getBytes());
        }
        return newsImageRepository.save(newsImage);
    }

    @Override
    public String deleteNews(Long id) {

        try {
            newsRepository.deleteById(id);
            return "Deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteNewsImage(Long id) {
        try {
            newsImageRepository.deleteById(id);
            return "Deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<NewsDto> getAll() {
        Iterable<News> iterable = newsRepository.findAll();
        List<NewsDto> newsDtos = StreamSupport.stream(iterable.spliterator(), false)
                .map(news -> {
                    NewsDto newsDto = new NewsDto();
                    BeanUtils.copyProperties(news, newsDto);
                    return newsDto;
                }).collect(Collectors.toList());
        return newsDtos;

    }

    @Override
    public Optional<News> getNews(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public NewsImage getImage(Long id) {
        NewsImage newsImage = newsImageRepository.findById(id).get();
        return newsImage;
    }

    @Override
    public ResponseEntity<List<ResponseFile>> getAllFiles() {
        List<ResponseFile> list = newsImageRepository.findAll().stream().map(image -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(image.getId()))
                    .toUriString();

            return new ResponseFile(
                    image.getId(),
                    image.getImageName(),
                    fileDownloadUri,
                    image.getImageType(),
                    image.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}



