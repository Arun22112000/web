package com.web.banner.service;

import com.banner.qb.exceptions.BannerException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.banner.dto.BannerDto;
import com.web.banner.entity.Banner;
import com.web.banner.entity.ImageEntity;
import com.web.banner.repository.BannerRepository;
import com.web.banner.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class    BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BannerDto> getAllList() {
        log.info("Getting all banner list....");
        List<Banner> cv = bannerRepository.findAll();
        return cv.stream().map(banner -> modelMapper.map(banner,BannerDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public BannerDto createBanner(String banner,MultipartFile file) throws IOException {
        if(file.isEmpty())return null;
        ImageEntity imageInfo=new ImageEntity();
        Date date1=new Date();
        imageInfo.setCreatedAt(date1);
        imageInfo.setFileName(file.getOriginalFilename());
        imageInfo.setData(file.getBytes());
        ImageEntity savedImage = imageRepository.save(imageInfo);

        log.info("Create a banner processing...");
        BannerDto bannerDto1=new BannerDto();
        ObjectMapper objectMapper=new ObjectMapper();
        bannerDto1=objectMapper.readValue(banner,BannerDto.class);

        Banner banners = modelMapper.map(bannerDto1, Banner.class);
        banners.setCreatedAt(new Date());
        banners.setDescription(bannerDto1.getDescription());
        banners.setUrl(bannerDto1.getUrl());
        banners.setButtonName(bannerDto1.getButtonName());
        banners.setImage(savedImage);
        Banner savedBanner = bannerRepository.save(banners);
        return modelMapper.map(savedBanner,BannerDto.class);
    }

    @Override
    public String deleteExistingBanner(int id) throws BannerException {
        log.info("Deleting existing banner...");
        Banner banner = bannerRepository.findById(id).orElseThrow(() -> new BannerException("BANNER NOT AVAILABLE"));
        banner.setDeletedAt(LocalDate.now());
        bannerRepository.saveAndFlush(banner);
        return "Successfully deleted";
    }


    @Override
    public String updateBannerDetails(int bannerId,String bannerDto,MultipartFile file) throws IOException {
        Optional<Banner> banner1 = bannerRepository.findById(bannerId);
        BannerDto bannerDto1=new BannerDto();
        ObjectMapper objectMapper=new ObjectMapper();
        bannerDto1=objectMapper.readValue(bannerDto,BannerDto.class);

        if(banner1.isPresent()){
            banner1.get().setButtonName(bannerDto1.getButtonName());
            banner1.get().setDescription(bannerDto1.getDescription());
            banner1.get().setUrl(bannerDto1.getUrl());
            ImageEntity imageInfo = banner1.get().getImage();
            imageInfo.setFileName(file.getOriginalFilename());
            imageInfo.setData(file.getBytes());
            ImageEntity savedImage = imageRepository.saveAndFlush(imageInfo);
            banner1.get().setImage(savedImage);
            bannerRepository.save(banner1.get());
            return "Updated banner details";
        }
        return "Not Updated banner details";
    }

    @Override
    public String updateImageByBannerId(int bannerId, MultipartFile file) throws IOException {
        Optional<Banner> bannerInfo = bannerRepository.findById(bannerId);
        if (bannerInfo.isPresent()) {
            ImageEntity imageEntity = bannerInfo.get().getImage();
            imageEntity.setData(file.getBytes());
            imageEntity.setFileName(file.getOriginalFilename());
            ImageEntity image = imageRepository.save(imageEntity);
            bannerInfo.get().setImage(image);
            bannerRepository.save(bannerInfo.get());
            return "Image uploaded successfully";
        }
        return "Image Not Updated...";
    }

    @Override
    public String deleteImage(int bannerId) {
        Optional<Banner> bannerInfo = bannerRepository.findById(bannerId);
        if(bannerInfo.isPresent()){
            ImageEntity image = bannerInfo.get().getImage();
            image.setDeletedAt(LocalDate.now());
            imageRepository.save(image);
            bannerInfo.get().setImage(null);
            bannerRepository.save(bannerInfo.get());
            return "Image deleted successfully";
        }
        return "Image is not deleted..";
    }

    @Override
    public BannerDto getBannerById(int bannerId) throws BannerException {
        Banner bannerInfo = bannerRepository.findById(bannerId).orElseThrow(() -> new BannerException("BANNER NOT AVAILABLE"));
        return modelMapper.map(bannerInfo,BannerDto.class);
    }

}
