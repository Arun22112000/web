package com.web.life.service;


import com.web.life.dto.TextDto;
import com.web.life.entity.Image;
import com.web.life.entity.Text;
import com.web.response.message.ResponseFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface TextService {
    String saveText(TextDto textDto);

    Image saveImage(List<MultipartFile> file) throws IOException;


    String deleteText(Long id);

    String deleteImage(Long id);

     List<TextDto> getAll();


    Optional<Text> getText(Long id);

    public Image getFile(Long id);

    public ResponseEntity<List<ResponseFile>> getAllFiles() ;


}
