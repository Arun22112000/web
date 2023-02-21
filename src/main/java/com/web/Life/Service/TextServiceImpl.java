package com.web.life.service;

import com.web.life.dto.TextDto;
import com.web.life.entity.Image;
import com.web.life.entity.Text;
import com.web.life.repository.ImageRepo;
import com.web.life.repository.TextRepo;
import com.web.response.message.ResponseFile;
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
public class TextServiceImpl implements com.web.life.service.TextService {

    private final ImageRepo imageRepo;
    private final TextRepo textRepo;
    private final ModelMapper modelMapper;


    public TextServiceImpl(ImageRepo imageRepo, TextRepo textRepo, ModelMapper modelMapper) {
        this.imageRepo = imageRepo;
        this.textRepo = textRepo;

        this.modelMapper = modelMapper;
    }


    @Override
    public String saveText(TextDto textDto) {
        Text text = modelMapper.map(textDto, Text.class);
        Image image = new Image();
        textDto.setId(text.getId());
        textDto.setTextMessage(text.getTextMessage());
        textDto.setImage(image);
        textDto.setTagLine(text.getTagLine());
        text.setButtonName(text.getButtonName());
        textRepo.save(text);
        return "ok";


    }

    @Override
    public Image saveImage(List<MultipartFile> file) throws IOException {
        Image image = new Image();
        for (MultipartFile file1 : file) {
            image.setId(image.getId());
            image.setImageName(file1.getOriginalFilename());
            image.setImageType(file1.getContentType());
            image.setImageSize(file1.getSize());
            image.setData(file1.getBytes());
        }
        return imageRepo.save(image);
    }

    @Override
    public String deleteText(Long id) {
        try {
            textRepo.deleteById(id);
            return "Deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteImage(Long id) {
        try {
            imageRepo.deleteById(id);
            return "Deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<TextDto> getAll() {
        Iterable<Text> iterable = textRepo.findAll();
        List<TextDto> textDtos = StreamSupport.stream(iterable.spliterator(), false)
                .map(text -> {
                    TextDto textDto = new TextDto();
                    BeanUtils.copyProperties(text, textDto);
                    return textDto;
                }).collect(Collectors.toList());
        return textDtos;
    }


    @Override
    public Optional<Text> getText(Long id) {
        return textRepo.findById(id);
    }

    @Override
    public Image getFile(Long id) {
        return imageRepo.findById(id).get();

    }

    @Override
    public ResponseEntity<List<ResponseFile>> getAllFiles() {
        List<ResponseFile> list = imageRepo.findAll().stream().map(image -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(String.valueOf(image.getId())).toUriString();

            return new ResponseFile(image.getId(), image.getImageName(), fileDownloadUri, image.getImageType(), image.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}



