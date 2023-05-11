import React from 'react';
import styled from 'styled-components';

const EventTap = () => {
  return (
    <Container>
      <h3>로켓CV 오픈 기념 이벤트!</h3>
      <span>로켓CV를 이용하여 취업을 하신 분들의 후기를 들려주세요!</span>
      <span>추첨을 통해 500만 메소를 드립니다.</span>
      <StyledButton>후기 작성하기</StyledButton>
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
  margin: 3rem 0 3rem 0;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  span {
    font-size: 1rem;
    color: gray;
  }
  h3 {
    font-size: 1.3rem;
  }
`;

const StyledButton = styled.button`
  width: 10rem;
  height: 3.5rem;
  margin: 1rem 0 1rem 0;
  font-size: 0, 8rem;
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
