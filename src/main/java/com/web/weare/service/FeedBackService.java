package com.web.weare.service;



import com.web.response.message.ResponseFile;
import com.web.weare.dto.FeedBackDto;
import com.web.weare.entity.FeedBackImage;
import com.web.weare.entity.Feedback;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface FeedBackService {
    public String saveFeedBack(FeedBackDto feedBackDto);

    FeedBackImage saveFeedBackImage(List<MultipartFile>multipartFiles ) throws IOException;

    String  deleteFeedBack (Long id);
    String  deleteFeedBackImage(Long id);
    List<FeedBackDto> getAllfeedBack();

    Optional<Feedback> getFeedBack(Long id);


    public FeedBackImage getFile(Long id);

    public ResponseEntity<List<ResponseFile>> getAllFiles() ;


}
