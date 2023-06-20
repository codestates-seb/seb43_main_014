import React from 'react';
import styled from 'styled-components';
import EventTap from '../components/Cv/EventTap';
import ReviewCard from '../components/Cv/ReviewCard';
import Logo from '../images/rocket.png';
import { Link } from 'react-router-dom';
import CvSample from '../components/Cv/CvSample';
import { useRecoilState } from 'recoil';
import { isLoginState } from '../recoil/AuthAtom';
import { CvContentAtom } from '../recoil/CvContentAtom';
import { SectionsContainer, Section } from 'react-fullpage';
import Footer from '../components/Cv/Footer';

const Main = () => {
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [cvContent, setCvContent] = useRecoilState(CvContentAtom);
  const options = {
    activeClass: 'active', // the class that is appended to the sections links
    anchors: ['One', 'Two', 'Three'], // the anchors for each sections
    arrowNavigation: true, // use arrow keys
    delay: 900, // the scroll animation speed
    navigation: false, // use dots navigatio
    scrollBar: false, // use the browser default scrollbar
  };

  setCvContent('');
  if (isLogin)
    return (
      <>
        <Container>
          <SectionsContainer {...options}>
            <Section>
              <div className="top">
                <img className="Logo" src={Logo} alt="로고 이미지" />

                <div>
                  <span>신입 개발자의 이력서 작성, 막막하기만 하다구요?</span>
                </div>
                <div>
                  <span>이력서를 가장 빠르고 쉽게 제작하는 방법!</span>
                </div>
                <div>
                  <Link to="/create-cv">
                    <StyledButton>이력서 작성하기</StyledButton>
                  </Link>
                </div>
              </div>
            </Section>
            <Section>
              <div className="mid">
                <h3>사용자가 말하는 로켓CV 사용기!</h3>
                <div className="row">
                  <ReviewCard />
                </div>
                <EventTap />
              </div>
            </Section>
            <Section>
              <div className="tem"></div>
              <h3>전문적인 이력서 템플릿을 이용해보세요!</h3>
              <CvSample />
            </Section>
          </SectionsContainer>
        </Container>
      </>
    );
  else {
    return (
      <>
        <Container>
          <SectionsContainer {...options}>
            <Section>
              <Link to="/">
                <img className="Logo" src={Logo} alt="로고 이미지" />
              </Link>
              <span>신입 개발자의 이력서 작성, 막막하기만 하다구요?</span>
              <span>이력서를 가장 빠르고 쉽게 제작하는 방법!</span>
              <Link to="/login">
                <StyledButton>이력서 작성하기</StyledButton>
              </Link>
            </Section>
            <Section>
              <div className="mid">
                <h3>사용자가 말하는 로켓CV 사용기!</h3>
                <div className="row">
                  <ReviewCard />
                </div>
                <EventTap />
              </div>
            </Section>
            <Section>
              <div className="tem">
                <h3>전문적인 이력서 템플릿을 이용해보세요!</h3>
                <CvSample />
              </div>
            </Section>
          </SectionsContainer>
        </Container>
      </>
    );
  }
};

export default Main;

const StyledButton = styled.button`
  width: 15rem;
  height: 6rem;
  margin: 10rem 0 0 18rem;
  font-size: 1.2rem;
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

const Container = styled.div`
  overflow: hidden;
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
  }
  .top {
    margin-left: 5rem;
    span {
      margin-left: 10rem;
    }
  }
  .mid {
    margin-left: 7rem;
    margin-top: 10rem;
  }
  h3 {
    font-size: 1.8rem;
  }

  .Logo {
    margin-top: 15rem;
    margin-left: 15rem;
    margin-bottom: 8rem;
    width: 20rem;
    opacity: 0.8;
    transition: 0.3s ease-in-out;

    :hover {
      transform: rotateY(-20deg);
    }
  }
`;
