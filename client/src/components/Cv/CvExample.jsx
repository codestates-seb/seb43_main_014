import React from 'react';
import styled from 'styled-components';

const CvExample = () => {
  return (
    <>
      <Container>
        <h3>전문적인 이력서 템플릿을 이용해보세요!</h3>
        <span>이력서 넣자</span>
      </Container>
    </>
  );
};

export default CvExample;

const Container = styled.main`
  margin: 8rem 0rem 4rem 0;
  width: 75rem;
  max-width: 100%;
  background-color: white;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  border: 1px solid tomato;
  span {
    font-size: 0.9rem;
  }
  h3 {
    font-size: 1.3rem;
  }
`;
