import React from 'react';
import styled from 'styled-components';
import Logo from '../../images/rocket.png';

const Footer = () => {
  return (
    <Container>
      <div>
        <h3>
          <img className="Logo" src={Logo} alt="로고 이미지" />
          로켓CV
        </h3>
        <span>신입 개발자들의 이력서 작성을 손쉽게 도와주는 서비스입니다.</span>
        <span>
          개발: 김희진, 강동욱, 김도현, 박은희, 권채연, 김재윤, 유성영
        </span>
        <p>2023 Copyright (c) 으니네코딩노예양성소. All rights reserved.</p>
      </div>
    </Container>
  );
};

export default Footer;

const Container = styled.footer`
  display: flex;
  background-color: var(--puple100);
  min-height: 15rem;
  justify-content: center;
  height: auto;
  width: 100%;
  color: gray;
  box-shadow: 0 -1px 2px hsla(0, 0%, 0%, 0.05), 0 -1px 4px hsla(0, 0%, 0%, 0.05),
    0 -2px 8px hsla(0, 0%, 0%, 0.05);
  text-align: center;

  div {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
  h3 {
    margin-bottom: 1.5rem;
    color: black;
    font-size: 1rem;
  }
  span {
    font-size: 0.7rem;
  }
  text {
    font-size: 0.7rem;
  }
  img {
    width: 10rem;
    height: 1rem;
  }
  p {
    font-size: 0.7rem;
  }
  .Logo {
    width: 1rem;
  }
`;
