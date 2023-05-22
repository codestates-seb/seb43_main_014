package com.cv.domain.cv.dto.linkDto;

import com.cv.domain.cv.entity.Link;
import lombok.Data;

@Data
public class LinkAddDto {
    private Link.LinkName linkName;

    private String linkAddress;
}
