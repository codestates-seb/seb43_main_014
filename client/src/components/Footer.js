import React from 'react';
import styled from 'styled-components';

const Footer = () => {
  return (
    <Container>
      <div>
        <h3>
          <img src="/images/rocket.png" alt="로고 이미지" />
          로켓CV
        </h3>
        <text>신입 개발자들의 이력서 작성을 손쉽게 도와주는 서비스입니다.</text>
        <text>
          개발: 김희진, 강동욱, 김도현, 박은희, 권채연, 김재윤, 유성영
        </text>
        <span>
          2023 Copyright (c) 으니네코딩노예양성소. All rights reserved.
        </span>
      </div>
    </Container>
  );
};

export default Footer;

const Container = styled.footer`
  position: absolute;
  display: flex;
  bottom: 0;
  background-color: var(--puple100);
  min-height: 15rem;
  height: auto;
  width: 100%;
  color: gray;
  div {
    display: flex;
    flex-direction: column;
    margin-left: 5rem;
  }
  h3 {
    text-transform: uppercase;
    margin-bottom: 1.5rem;
    color: black;
    font-weight: 700;
    font-size: 1.3rem;
  }
  span {
    margin-top: 3rem;
  }
  text {
  }
  img {
    width: 5rem;
    height: 5rem;
  }
`;
