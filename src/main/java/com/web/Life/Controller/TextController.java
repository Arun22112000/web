package com.web.life.Controller;

import com.web.life.dto.TextDto;
import com.web.life.entity.Image;
import com.web.life.entity.Text;
import com.web.life.service.TextServiceImpl;
import com.web.response.message.ResponseFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "v1/lifeAtQb")
@CrossOrigin("http://localhost:4200")
public class TextController {


    private final TextServiceImpl textServiceImpl;

    public TextController(TextServiceImpl textServiceImpl) {
        this.textServiceImpl = textServiceImpl;
    }


    @PostMapping(value = "/saveText")
    public String insertTextDetails(@RequestBody TextDto textDto) {
         textServiceImpl.saveText(textDto);
        return "uploaded Successfully";
    }



    @PostMapping(value = "/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Image uploadFile(@RequestParam("file") List<MultipartFile> file) throws IOException {
     return textServiceImpl.saveImage(file);


    }

    @GetMapping(value = "/getTextById/{id}")
    public Optional<Text> getText(@PathVariable ("id")Long id) {
        return textServiceImpl.getText(id);
    }

    @GetMapping(value = "/getAllText")
    public List<TextDto> getAll() {
        return textServiceImpl.getAll();
    }

    @DeleteMapping("/deleteFeedbackById/{id}")
    public String delete(@PathVariable("id") Long id) {
        return textServiceImpl.deleteText(id);
    }

    @DeleteMapping("/deleteFeedbackImageById/{id}")
    public String deleteImage(@PathVariable("id") Long id) {
        return textServiceImpl.deleteImage(id);
    }

    @GetMapping(value = "/getImageById/{id}")
    public Image getfeedbackImage(@PathVariable("id") Long id) {
        return textServiceImpl.getFile(id);
    }

    @GetMapping("/getAllImageFiles")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        return textServiceImpl.getAllFiles();
    }

}