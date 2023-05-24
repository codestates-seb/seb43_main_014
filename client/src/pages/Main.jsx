import React from 'react';
import styled from 'styled-components';
import EventTap from '../components/Cv/EventTap';
import ReviewCard from '../components/Cv/ReviewCard';
import Logo from '../images/rocket.png';
import { Link } from 'react-router-dom';
import CvSample from '../components/Cv/CvSample';
import { useRecoilState } from 'recoil';
import { isLoginState } from '../recoil/AuthAtom';

const Main = () => {
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  if (isLogin)
    return (
      <>
        <Container>
          <Link to="/">
            <img className="Logo" src={Logo} alt="로고 이미지" />
          </Link>
          <span>신입 개발자의 이력서 작성, 막막하기만 하다구요?</span>
          <span>이력서를 가장 빠르고 쉽게 제작하는 방법!</span>
          <Link to="/create-cv">
            <StyledButton>이력서 작성하기</StyledButton>
          </Link>
          <h3>사용자가 말하는 로켓CV 사용기!</h3>
          <div className="row">
            <ReviewCard />
          </div>
          <EventTap />
          <div className="tem"></div>
          <h3>전문적인 이력서 템플릿을 이용해보세요!</h3>
          <CvSample />
        </Container>
      </>
    );
  else {
    return (
      <>
        <Container>
          <Link to="/">
            <img className="Logo" src={Logo} alt="로고 이미지" />
          </Link>
          <span>신입 개발자의 이력서 작성, 막막하기만 하다구요?</span>
          <span>이력서를 가장 빠르고 쉽게 제작하는 방법!</span>
          <Link to="/login">
            <StyledButton>이력서 작성하기</StyledButton>
          </Link>
          <h3>사용자가 말하는 로켓CV 사용기!</h3>
          <div className="row">
            <ReviewCard />
          </div>
          <EventTap />
          <div className="tem"></div>
          <h3>전문적인 이력서 템플릿을 이용해보세요!</h3>
          <CvSample />
        </Container>
      </>
    );
  }
};

export default Main;

const StyledButton = styled.button`
  width: 10rem;
  height: 3.5rem;
  margin: 2rem 0 10rem 0;
  font-size: 0, 8rem;
  cursor: pointer;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--bgColor);
  color: white;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  &:hover {
    background-color: var(--puple300);
  }
`;

const Container = styled.main`
  margin-top: 3rem;
  width: 100%;
  max-width: 100%;
  height: 100%;
  background-color: white;
  display: flex;

  flex-direction: column;
  justify-content: center;
  align-items: center;
  span {
    font-size: 1.5rem;
  }
  .row {
    display: flex;
    flex-direction: row;
    margin-top: 2rem;
  }
  h3 {
    font-size: 1.8rem;
  }
  .Logo {
    margin: 2rem 0 3rem 0;
    width: 20rem;
  }
  .tem {
    margin-top: 8rem;
  }
`;
