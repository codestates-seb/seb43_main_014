import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import styled from 'styled-components';

const Main = () => {
  return (
    <>
      <Header />
      <Container>
        <h1>이력서를 가장 빠르고 쉽게 제작하는 방법</h1>

        <StyledButton>이력서 작성하기</StyledButton>
      </Container>
      <Footer />
    </>
  );
};

export default Main;

const StyledButton = styled.button`
  min-width: 7rem;
  margin-left: 0.5rem;
  font-size: 0, 8rem;
  cursor: pointer;
  padding: 1rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: var(--puple100);

  &:hover {
    background-color: var(--puple100);
  }
`;

const Container = styled.main`
  margin-top: 7rem;
  width: 100%;
  max-width: 100%;
  background-color: white;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  font-size: 1.5rem;
`;
