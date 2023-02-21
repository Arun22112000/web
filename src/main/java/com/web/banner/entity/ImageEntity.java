package com.web.banner.entity;

import com.banner.qb.commonentity.CommonEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at is null")
@Table(name = "images")
@EqualsAndHashCode
public class ImageEntity extends CommonEntity {
    private String fileName;
    @Lob
    private byte[] data;

}
