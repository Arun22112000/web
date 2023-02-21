package com.web.ourservice.controller;

import com.web.response.message.ResponseFile;
import com.web.ourservice.dto.NewsDto;
import com.web.ourservice.entity.News;
import com.web.ourservice.entity.NewsImage;
import com.web.ourservice.service.NewsServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "v1/OurService")
@CrossOrigin("http://localhost:4200")
public class NewsController {
    private final NewsServiceImpl newsService;

    public NewsController(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }


    @PostMapping(value = "/saveNews")
    public String insertNewsDetails(@RequestBody NewsDto newsDto) {
        return newsService.saveNews(newsDto);
    }


    @PostMapping(value = "/newsImage",consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    public NewsImage uploadNewsImage(@RequestParam("file") List<MultipartFile> file) throws IOException {


        return newsService.saveNewsImage(file);

    }

    @GetMapping(value = "getAllNews")
    public List<NewsDto> getAll() {
        return newsService.getAll();
    }

    @GetMapping(value = "/getNewsById/{id}")
    public Optional<News> getNews(@PathVariable("id") Long id) {
        return newsService.getNews(id);
    }

    @DeleteMapping("/deleteNewsById/{id}")
    public String deleteNews(@PathVariable("id") Long id) {
        return newsService.deleteNews(id);
    }

    @DeleteMapping("/deleteNewsImageById/{id}")
    public String deleteNewsImage(@PathVariable("id") Long id) {
        return newsService.deleteNewsImage(id);
    }

    @GetMapping(value = "/getImageById/{id}")
    public NewsImage getfeedbackImage(@PathVariable("id") Long id) {
        return newsService.getImage(id);
    }

    @GetMapping("/getAllImageFiles")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        return newsService.getAllFiles();
    }
}

