import React from 'react';
import styled from 'styled-components';
import Logo from '../../images/rocket.png';
import GitHubIcon from '@mui/icons-material/GitHub';

const Footer = () => {
  return (
    <Container>
      <div className="wrapper">
        <div className="left">
          <div className="logo">
            <img className="logo_img" src={Logo} alt="로고 이미지" />
            <h1>로켓CV</h1>
          </div>
          <div className="explain">신입 개발자들의 이력서 작성을</div>
          <div className="explain">손쉽게 도와주는 서비스입니다.</div>
        </div>
        <div className="right">
          <ul>
            <li>
              <a href="https://github.com/heejinki">
                <GitHubIcon />
                <div>FE팀장</div>
                <div>김희진</div>
              </a>
            </li>
            <li>
              <a href="https://github.com/dongwook98">
                <GitHubIcon />
                <div>FE팀원</div>
                <div>강동욱</div>
              </a>
            </li>
            <li>
              <a href="https://github.com/dohyun98">
                <GitHubIcon />
                <div>FE팀원</div>
                <div>김도현</div>
              </a>
            </li>
            <li>
              <a href="https://github.com/9ruem2">
                <GitHubIcon />
                <div>BE팀장</div>
                <div>박은희</div>
              </a>
            </li>
            <li>
              <a href="https://github.com/tkfkdgowksel">
                <GitHubIcon />
                <div>BE팀원</div>
                <div>유성영</div>
              </a>
            </li>
            <li>
              <a href="https://github.com/chaeyeon0130">
                <GitHubIcon />
                <div>BE팀원</div>
                <div>권채연</div>
              </a>
            </li>
            <li>
              <a href="https://github.com/jaeyumn">
                <GitHubIcon />
                <div>BE팀원</div>
                <div>김재윤</div>
              </a>
            </li>
          </ul>
          <h2>2023 Copyright (c) 으니네코딩노예양성소 All rights reserved</h2>
        </div>
      </div>
    </Container>
  );
};

export default Footer;

const Container = styled.footer`
  background-color: var(--puple100);
  color: var(--bgColor);
  box-shadow: 0 -1px 2px hsla(0, 0%, 0%, 0.05), 0 -1px 4px hsla(0, 0%, 0%, 0.05),
    0 -2px 8px hsla(0, 0%, 0%, 0.05);
  text-align: center;

  a {
    color: var(--bgColor);
  }

  a:hover {
    text-decoration: none;
  }

  .wrapper {
    width: 70rem;
    height: 15rem;
    margin: 0 auto;
    display: flex;
    align-items: center;
  }

  .logo {
    display: flex;
    align-items: center;
  }

  .logo img {
    width: 2.5rem;
    height: 2.5rem;
  }

  .logo h1 {
    margin-left: 0.5rem;
  }

  .left {
    width: 30%;
    display: flex;
    flex-direction: column;
    align-items: baseline;
    margin-bottom: 2.5rem;
  }

  .right {
    width: 70%;
  }

  .right h2 {
    margin-left: 3.5rem;
  }

  .explain {
    font-size: 1.2rem;
  }

  ul {
    display: flex;
    list-style: none;
    justify-content: space-around;
  }
`;
