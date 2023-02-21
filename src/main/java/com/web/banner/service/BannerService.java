package com.web.banner.service;

import com.banner.qb.exceptions.BannerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.web.banner.dto.BannerDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface BannerService {
    List<BannerDto> getAllList();

    BannerDto createBanner(String banner,MultipartFile file) throws IOException;

    String deleteExistingBanner(int id) throws BannerException;


    String updateBannerDetails(int bannerId,String bannerDto,MultipartFile file) throws IOException;

    String updateImageByBannerId(int bannerId, MultipartFile file) throws IOException;

    String deleteImage(int bannerId);

    BannerDto getBannerById(int bannerId) throws BannerException;
}
