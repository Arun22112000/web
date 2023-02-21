package com.web.banner.entity;

import com.banner.qb.commonentity.CommonEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "banner")
@Where(clause = "deleted_At is null")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Banner extends CommonEntity {
    private String description;
    private String url;
    private String buttonName;

    //    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "images_id", referencedColumnName = "id")
    private ImageEntity image;
}
