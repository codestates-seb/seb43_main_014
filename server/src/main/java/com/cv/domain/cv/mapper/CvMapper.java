package com.cv.domain.cv.mapper;

import com.cv.domain.cv.dto.CvDto;
import com.cv.domain.cv.entity.Cv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CvMapper {

    Cv cvPostToCv(CvDto.Post post);
    Cv cvPatchToCv(CvDto.Patch patch);
    CvDto.Response cvToCvResponse(Cv cv);
}
