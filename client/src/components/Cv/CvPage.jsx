import React from 'react';
import Box from '@mui/material/Box';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepButton from '@mui/material/StepButton';
import styled from 'styled-components';
import { useState } from 'react';
import CvBasicInfo from './CvBasicInfo';
import CvCareer from './CvCareer';
import CvSkillInput from './CvCustomInput';

export default function CvPage() {
  const [activeStep, setActiveStep] = useState(0);
  const steps = ['기본 정보', '경력 및 프로젝트', '작성 완료'];
  const getStepContent = (stepNumber) => {
    switch (stepNumber) {
      case 0:
        return <CvBasicInfo />;
      case 1:
        return (
          <>
            <CvCareer />
          </>
        );
      case 2:
        return '이력서 작성이 완료되었습니다. 수정할 사항이 있다면 이전버튼, 이력서를 저장하시려면 완료 버튼을 눌러주세요';
    }
  };
  const handleNext = () => {
    setActiveStep((preActiveStep) => preActiveStep + 1);
  };
  const handleBack = () => {
    setActiveStep((preActiveStep) => preActiveStep - 1);
  };
  const handleReset = () => {
    setActiveStep(0);
  };

  return (
    <Container>
      <Box sx={{ width: '100%' }}>
        <div>
          <Stepper nonLinear activeStep={activeStep}>
            {steps.map((label) => (
              <Step key={label}>
                <StepButton color="inherit" disabled="disabled">
                  {label}
                </StepButton>
              </Step>
            ))}
          </Stepper>

          {activeStep !== steps.length ? (
            <>
              <div className="test">
                <StyledButton disabled={activeStep === 0} onClick={handleBack}>
                  이전
                </StyledButton>
                <StyledButton onClick={handleNext}>
                  {activeStep === steps.length - 1 ? '완료' : '다음'}
                </StyledButton>
              </div>
              <div>
                <div>{getStepContent(activeStep)}</div>
              </div>
            </>
          ) : (
            <>
              <div className="test">
                <StyledSaveButton>이력서 저장하기</StyledSaveButton>
                <StyledResetButton onClick={handleReset}>
                  다시 작성하기
                </StyledResetButton>
              </div>
              <div>템플릿 적용하여 작성된 이력서 보여주기</div>
            </>
          )}
        </div>
      </Box>
    </Container>
  );
}

const Container = styled.div`
  margin: 0 auto;
  width: 63rem;
  margin-top: 2rem;
  border: 1px solid green;

  .test {
    border: 1px solid green;
    display: flex;
    justify-content: center;
    align-items: center;
  }
`;

const StyledButton = styled.button`
  width: 5rem;
  height: 2rem;
  font-size: 0.8rem;
  cursor: pointer;
  margin: 1rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: black;

  &:hover {
    background-color: var(--puple300);
  }
`;

const StyledSaveButton = styled.button`
  width: 7rem;
  height: 2rem;
  font-size: 0.8rem;
  cursor: pointer;
  margin: 1rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: black;

  &:hover {
    background-color: var(--puple300);
  }
`;

const StyledResetButton = styled.button`
  width: 7rem;
  height: 2rem;
  font-size: 0.8rem;
  cursor: pointer;
  margin: 1rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: black;

  &:hover {
    background-color: var(--puple300);
  }
`;
