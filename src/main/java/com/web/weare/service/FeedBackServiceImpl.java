package com.web.weare.service;

import com.web.response.message.ResponseFile;
import com.web.weare.dto.FeedBackDto;
import com.web.weare.entity.FeedBackImage;
import com.web.weare.entity.Feedback;
import com.web.weare.repository.FeedBackImageRepository;
import com.web.weare.repository.FeedBackRepo;
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
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackImageRepository feedBackImageRepository;
    private final FeedBackRepo feedBackRepo;
    private final ModelMapper  modelMapper;

    public FeedBackServiceImpl(FeedBackImageRepository feedBackImageRepository, FeedBackRepo feedBackRepo, ModelMapper modelMapper) {
        this.feedBackImageRepository = feedBackImageRepository;

        this.feedBackRepo = feedBackRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public String saveFeedBack(FeedBackDto feedBackDto) {
        Feedback feedback= modelMapper.map(feedBackDto ,Feedback.class);
        List<FeedBackImage> feedBackImages=feedBackImageRepository.findAll();
        feedBackDto.setId(feedback.getId());
        feedBackDto.setMainText(feedback .getMainText());
        feedBackDto.setSubText(feedback .getSubText());
        feedBackDto.setUrl(feedback .getUrl());
        feedBackDto.setFeedBackImage(feedBackImages);
        feedBackDto.setTagLine(feedback.getTagLine());
        feedBackDto.setButtonName(feedback.getButtonName());

                feedBackRepo.save(feedback);
                return  "ok";
    }

    @Override
    public FeedBackImage saveFeedBackImage(List<MultipartFile> multipartFiles) throws IOException {
        FeedBackImage feedBackImage = new FeedBackImage();
        for (MultipartFile file1 : multipartFiles) {

            feedBackImage.setImageName(file1.getOriginalFilename());
            feedBackImage.setImageType(file1.getContentType());
            feedBackImage.setData(file1.getBytes());
        }
        return feedBackImageRepository.save(feedBackImage);
    }

    @Override
    public String deleteFeedBack(Long id) {
        try {
            feedBackRepo.deleteById(id);
            return "Deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteFeedBackImage(Long id) {
        try {
            feedBackImageRepository.deleteById(id);
            return "Deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<FeedBackDto> getAllfeedBack()
    {  Iterable<Feedback> iterable= feedBackRepo.findAll();
    List<FeedBackDto>feedBackDtos=StreamSupport.stream(iterable.spliterator(), false)
            .map( feedback-> {
               FeedBackDto feedBackDto= new FeedBackDto();
                BeanUtils.copyProperties(feedback,feedBackDto);
                return feedBackDto;
            }).collect(Collectors.toList());
        return feedBackDtos;
}
    @Override
    public Optional<Feedback> getFeedBack(Long id) {
        return feedBackRepo.findById(id);
    }

    @Override
    public FeedBackImage getFile(Long id) {

        return feedBackImageRepository.findById(id).get();

    }

    @Override
    public  ResponseEntity<List<ResponseFile>> getAllFiles() {
        List<ResponseFile> list = feedBackImageRepository.findAll().stream().map(image -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(image.getId()))
                    .toUriString();

            return new ResponseFile(
                    image.getId(), image.getImageName(),
                    fileDownloadUri,
                    image.getImageType(),
                    image.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    }
