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
import CvPreview from './CvPreview';
import axios from 'axios';
import { CvContentAtom } from '../../recoil/CvContentAtom';
import { useRecoilState } from 'recoil';
import { useNavigate } from 'react-router-dom';
import { API } from '../../utils/API';

export default function CvPage() {
  const [check, setCheck] = useState(false);
  const navigate = useNavigate();
  const [activeStep, setActiveStep] = useState(0);
  const [cvContent, setCvContent] = useRecoilState(CvContentAtom);
  const steps = ['기본 정보', '경력 및 프로젝트', '작성 완료'];
  const token = localStorage.getItem('jwt_token');
  const getStepContent = (stepNumber) => {
    switch (stepNumber) {
      case 0:
        return <CvBasicInfo check={check} setCheck={setCheck} />;
      case 1:
        return <CvCareer setCheck={setCheck} />;

      case 2:
        return <CompletePage setCheck={setCheck} />;
    }
  };
  console.log(cvContent);

  const handleClickSave = () => {
    axios
      .post(`${API}/cv/`, cvContent, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        console.log(res);
        alert('이력서 저장이 완료되었습니다.');
        navigate('/');
      })
      .catch((error) => {
        console.log(error);
        alert('이력서 저장이 실패하였습니다.');
      });
    localStorage.removeItem('CvBasicContent');
  };

  const handleNext = () => {
    if (check) {
      setActiveStep((preActiveStep) => preActiveStep + 1);
      setCheck(false);
    } else {
      alert('임시저장 버튼을 눌러주세요.');
    }
  };

  const handleBack = () => {
    setActiveStep((preActiveStep) => preActiveStep - 1);
  };
  const handleReset = () => {
    setActiveStep(0);
    setCvContent(null);
  };
  console.log(2, check);

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
              <CvPreview />
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
