import React from 'react';
import styled from 'styled-components';
import EventTap from '../components/EventTap';
import ReviewCard from '../components/ReviewCard';
import CvExample from '../components/CvExample';
import CvPage from '../components/CvPage';

const Main = () => {
  return (
    <>
      <Container>
        <CvPage />
        <span>신입 개발자의 이력서 작성, 막막하기만 하다구요?</span>
        <span>이력서를 가장 빠르고 쉽게 제작하는 방법!</span>

        <StyledButton>이력서 작성하기</StyledButton>
        <h3>사용자가 말하는 로켓CV 사용기!</h3>
        <div className="row">
          <ReviewCard />
        </div>
        <EventTap />
        <CvExample />
      </Container>
    </>
  );
};

export default Main;

const StyledButton = styled.button`
  width: 10rem;
  height: 3.5rem;
  margin: 2rem 0 10rem 0;
  font-size: 0, 8rem;
  cursor: pointer;
  padding: 1rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: white;

  &:hover {
    background-color: var(--puple300);
  }
`;

const Container = styled.main`
  margin-top: 8rem;
  width: 100%;
  max-width: 100%;
  height: 100%;
  background-color: white;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  border: 1px solid gray;
  span {
    font-size: 1.5rem;
  }
  .row {
    display: flex;
    flex-direction: row;
  }
  h3 {
    font-size: 1.8rem;
  }
`;
