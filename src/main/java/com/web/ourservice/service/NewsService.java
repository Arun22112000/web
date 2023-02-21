package com.web.ourservice.service;



import com.web.response.message.ResponseFile;
import com.web.ourservice.dto.NewsDto;
import com.web.ourservice.entity.News;
import com.web.ourservice.entity.NewsImage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface NewsService {
    String saveNews(NewsDto newsDto);

    NewsImage saveNewsImage(List<MultipartFile> file) throws IOException;

    String deleteNews(Long id);
    String deleteNewsImage(Long id);

    List<NewsDto> getAll();

    Optional<News> getNews(Long id);

    public NewsImage getImage (Long id);

    public ResponseEntity<List<ResponseFile>> getAllFiles() ;
}
