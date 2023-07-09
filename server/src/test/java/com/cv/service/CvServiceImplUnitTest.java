package com.cv.service;

import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.repository.CvRepository;
import com.cv.domain.cv.service.CvServiceImpl;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.service.UserServiceUtilsInterface;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CvServiceImplUnitTest {
    // @Spy : Mock 되지 않는 메서드로, 실제 메서드로 동작하는 애너테이션
    @Mock
    private CvRepository cvRepository;
    @Mock
    private UserServiceUtilsInterface userServiceUtilsInterface;
    @InjectMocks
    private CvServiceImpl cvService;

    private Cv cv;

    @BeforeEach
    void setup() {
        cv = new Cv();
        cv.setCvId(1L);
    }

    @DisplayName("이력서 작성 테스트")
    @Test
    void createCvTest() {
        // given
        User user = new User();
        user.setId(1L);
        cv.setUser(user);

        when(userServiceUtilsInterface.findUserByUUID(anyString())).thenReturn(user);
        when(cvRepository.save(cv)).thenReturn(cv);

        // when
        Cv createCv = cvService.createCv(cv);

        // then
        assertEquals(cv, createCv);
        verify(userServiceUtilsInterface, times(1)).findUserByUUID(anyString());
        verify(cvRepository, times(1)).save(cv);
    }

    @DisplayName("이력서 수정 테스트")
    @Test
    void updateCvTest() {

        // given
        Cv findCv = new Cv();
        findCv.setCvId(1L);

        when(cvRepository.findById(anyLong())).thenReturn(Optional.of(findCv));
        when(cvRepository.save(findCv)).thenReturn(findCv);

        // when
        Cv updatedCv = cvService.updateCv(cv);

        // then
        assertEquals(findCv, updatedCv);
        verify(cvRepository, times(1)).findById(anyLong());
        verify(cvRepository, times(1)).save(findCv);
    }

    @DisplayName("이력서 조회 테스트")
    @Test
    void getCvTest() {

        // given
        when(cvRepository.findById(anyLong())).thenReturn(Optional.of(cv));

        // when
        Cv getCv = cvService.getCv(1L);

        // then
        assertEquals(cv, getCv);
        verify(cvRepository, times(1)).findById(anyLong());
    }

    @DisplayName("이력서 삭제 테스트(soft delete)")
    @Test
    void deleteCvTest() {

        // given
        when(cvRepository.findById(anyLong())).thenReturn(Optional.of(cv));
        when(cvRepository.save(cv)).thenReturn(cv);

        // when
        cvService.deleteCv(1L);

        // then
        assertEquals(true, cv.getIsDelete());
        verify(cvRepository, times(1)).save(cv);
    }

    @DisplayName("RESUME not found exception 테스트")
    @Test
    void findVerifiedCvTest() {

        long cvId = 2L;

        BusinessLogicException exception = assertThrows(
                BusinessLogicException.class,
                () -> cvService.findVerifiedCv(cvId)
        );

        assertEquals(ExceptionCode.RESUME_NOT_FOUND, exception.getExceptionCode());
    }

    @DisplayName("유효한 이력서인지 확인하는 테스트")
    @Test
    void isCvValid() {

        cv.setIsDelete(true);

        BusinessLogicException exception = assertThrows(
                BusinessLogicException.class,
                () -> cvService.isCvValid(cv)
        );

        assertEquals(ExceptionCode.RESUME_WAS_DELETED, exception.getExceptionCode());
    }
}
