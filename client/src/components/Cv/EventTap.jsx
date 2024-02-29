import React from 'react';
import styled from 'styled-components';

const EventTap = () => {
  return (
    <Container>
      <h3>로켓CV 오픈 기념 이벤트!</h3>
      <Content>로켓CV를 이용하여 취업을 하신 분들의 후기를 들려주세요!</Content>
      <Content>여러분들의 소중한 후기는 로켓CV에 큰 힘이 됩니다!</Content>
      <StyledButton
        onClick={() =>
          window.open(
            'https://docs.google.com/forms/d/e/1FAIpQLSdFuq072eqWAuvA-yUYCwamc2jPrnlvfX32EsV4KislcGGIOQ/viewform?usp=sf_link',
          )
        }
      >
        후기 작성하기
      </StyledButton>
    </Container>
  );
};

export default EventTap;

const Container = styled.div`
  display: flex;
  width: 45rem;
  background-color: var(--puple300);
  justify-content: center;
  flex-direction: column;
  align-items: center;
  height: 15rem;
  text-align: center;
  margin: 10rem 0 0rem 1rem;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  h3 {
    font-size: 1.3rem;
  }
`;

const Content = styled.div`
  font-size: 1rem;
  color: gray;
`;

const StyledButton = styled.button`
  width: 10rem;
  height: 3.5rem;
  margin: 1rem 0 1rem 0;
  font-size: 0.8rem;
  cursor: pointer;
  padding: 1rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: white;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;

  &:hover {
    background-color: var(--puple300);
  }
`;
