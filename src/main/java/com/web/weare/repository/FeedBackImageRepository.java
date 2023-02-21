package com.web.weare.repository;


import com.web.weare.entity.FeedBackImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackImageRepository extends JpaRepository<FeedBackImage,Long> {
}
