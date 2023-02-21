package com.web.weare.controller;


import com.web.response.message.ResponseFile;
import com.web.weare.dto.FeedBackDto;
import com.web.weare.entity.FeedBackImage;
import com.web.weare.entity.Feedback;
import com.web.weare.service.FeedBackServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "v1/WhoWeAre")
@CrossOrigin("http://localhost:4200")
public class FeedBackController {
    private final FeedBackServiceImpl feedBackService;

    public FeedBackController(FeedBackServiceImpl feedBackService) {
        this.feedBackService = feedBackService;
    }


    @PostMapping(value = "/saveFeedback")
    public String insertfeedBackDetails(@RequestBody FeedBackDto feedBackDto) {
        return feedBackService.saveFeedBack(feedBackDto);
    }


    @PostMapping(value = "/uploadFeedback", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FeedBackImage uploadFeedbackImage(@RequestParam("file") List<MultipartFile> file) throws IOException {


        return feedBackService.saveFeedBackImage(file);

    }

    @GetMapping(value = "/getAllFeedback")
    public List<FeedBackDto> getAll() {
        return feedBackService.getAllfeedBack();
    }

    @GetMapping(value = "/getFeedbackById/{id}")
    public Optional<Feedback> getFeedBack(@PathVariable("id") Long id) {
        return feedBackService.getFeedBack(id);
    }

    @DeleteMapping("/deleteFeedbackById/{id}")
    public String delete(@PathVariable("id") Long id) {
        return feedBackService.deleteFeedBack(id);
    }

    @GetMapping(value = "/getImageById/{id}")
    public FeedBackImage getfeedbackImage(@PathVariable("id") Long id) {
        return feedBackService.getFile(id);
    }

    @GetMapping("/getAllImageFiles")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        return feedBackService.getAllFiles();
    }

    @DeleteMapping("/deleteFeedbackImageById/{id}")
    public String deleteImage(@PathVariable("id") Long id) {
        return feedBackService.deleteFeedBackImage(id);
    }

}

