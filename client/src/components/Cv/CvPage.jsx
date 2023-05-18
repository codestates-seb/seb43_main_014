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
import CompletePage from './CompletePage';
import CvTemplate from './CvTemplate';
import axios from 'axios';
import { CvContentAtom } from '../../recoil/CvContentAtom';
import { useRecoilState } from 'recoil';
import { useNavigate } from 'react-router-dom';

export default function CvPage() {
  const navigate = useNavigate();
  const [activeStep, setActiveStep] = useState(0);
  const [cvContent, setCvContent] = useRecoilState(CvContentAtom);
  const steps = ['기본 정보', '경력 및 프로젝트', '작성 완료'];
  const getStepContent = (stepNumber) => {
    switch (stepNumber) {
      case 0:
        return <CvBasicInfo />;
      case 1:
        return <CvCareer />;

      case 2:
        return <CompletePage />;
    }
  };
  const handleClickSave = () => {
    alert('이력서 저장이 완료되었습니다.');
    navigate(`/`);
  };
  console.log('최종 이력서 데이터', cvContent);
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
                <StyledSaveButton name="submit" onClick={handleClickSave}>
                  이력서 저장하기
                </StyledSaveButton>
                <StyledResetButton onClick={handleReset}>
                  다시 작성하기
                </StyledResetButton>
              </div>
              <CvTemplate />
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

  .test {
    display: flex;
    justify-content: center;
    align-items: center;
  }
`;

const StyledButton = styled.button`
  width: 5rem;
  height: 2rem;
  font-size: 0.8rem;
  font-weight: bold;
  cursor: pointer;
  margin: 2rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: var(--puple100);
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;

  &:hover {
    background-color: var(--puple300);
  }
`;

const StyledSaveButton = styled.button`
  width: 7rem;
  height: 2rem;
  margin: 2rem;
  font-size: 0.8rem;
  font-weight: bold;
  cursor: pointer;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: var(--puple100);
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;

  &:hover {
    background-color: var(--puple300);
  }
`;

const StyledResetButton = styled.button`
  width: 7rem;
  height: 2rem;
  font-size: 0.8rem;
  font-weight: bold;
  cursor: pointer;
  margin: 1rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: var(--puple100);
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;

  &:hover {
    background-color: var(--puple300);
  }
`;
