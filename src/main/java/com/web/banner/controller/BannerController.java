package com.web.banner.controller;

import com.banner.qb.exceptions.BannerException;
import com.web.banner.dto.BannerDto;
import com.web.banner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/getAllBanners")
    public ResponseEntity<List<BannerDto>> allBannerList() {
        List<BannerDto> list = bannerService.getAllList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/createBanner", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BannerDto> createBanner(@RequestPart String banner, @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(bannerService.createBanner(banner, file), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteBannerById")
    public String deleteBannerById(@RequestParam int id) throws BannerException {
        return bannerService.deleteExistingBanner(id);
    }

    @GetMapping("/getBannerById")
    public BannerDto getBanner(@RequestParam int bannerId) throws BannerException {
        return bannerService.getBannerById(bannerId);
    }

    @PutMapping(value = "/updateBannerDetails", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateBannerDetails(@RequestParam int bannerId, @RequestPart String bannerDto, @RequestParam MultipartFile file) throws IOException {
        return bannerService.updateBannerDetails(bannerId, bannerDto, file);
    }

    @DeleteMapping("/deleteImage")
    public String deleteImageByBannerId(@RequestParam int bannerId) {
        return bannerService.deleteImage(bannerId);
    }

}
