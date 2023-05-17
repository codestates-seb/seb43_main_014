import React, { useState } from 'react';
import styled from 'styled-components';
import Logo from '../../images/rocket.png';
import { useRecoilState } from 'recoil';
import { isLoginState, tokenState, userState } from '../../recoil/AuthAtom';
import { Link, useNavigate } from 'react-router-dom';
import Confirm from '../common/Confirm/Confirm';

const Header = () => {
  const navigate = useNavigate();
  const [token, setToken] = useRecoilState(tokenState);
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [userInfo, setUserInfo] = useRecoilState(userState);
  const userData = JSON.parse(localStorage.getItem('user_info'));

  const [isOpenConfirm, setIsOpenConfirm] = useState(false);
  console.log(isOpenConfirm);

  const handleLogout = () => {
    localStorage.removeItem('jwt_token');
    localStorage.removeItem('user_info');
    setIsLogin(false);
    setToken(null);
    setUserInfo(null);
    setIsOpenConfirm(false);
    navigate('/');
  };
  if (isLogin) {
    return (
      <BasicHeader>
        <div className="container">
          <div className="logo_menu">
            <Link to="/">
              <img className="logo_img" src={Logo} alt="로켓CV로고" />
              <span>로켓CV</span>
            </Link>
          </div>
          <div className="contents_menu">
            <Link to="/create-cv">이력서 작성</Link>
            <Link to="/project/matching">프로젝트 매칭</Link>
            <Link to="/team">팀원 소개</Link>
            <Link to="/team">문의 하기</Link>
          </div>
          <div className="auth_menu">
            <Link to="/mypage">{userData.name}님</Link>
            <span
              onClick={() => {
                setIsOpenConfirm(true);
              }}
            >
              로그아웃
            </span>
            {isOpenConfirm && (
              <Confirm
                setIsOpenConfirm={setIsOpenConfirm}
                handleLogout={handleLogout}
              >
                <p>정말 로그아웃 하시겠습니까?</p>
              </Confirm>
            )}
          </div>
        </div>
      </BasicHeader>
    );
  } else {
    return (
      <BasicHeader>
        <div className="container">
          <div className="logo_menu">
            <Link to="/">
              <img className="logo_img" src={Logo} alt="로켓CV로고" />
              <span>로켓CV</span>
            </Link>
          </div>
          <div className="contents_menu">
            <Link to="/create-cv">이력서 작성</Link>
            <Link to="/project/matching">프로젝트 매칭</Link>
            <Link to="/team">팀원 소개</Link>
            <Link to="/team">문의 하기</Link>
          </div>
          <div className="auth_menu">
            <Link to="/login">로그인</Link>
            <Link to="/signup">회원가입</Link>
          </div>
        </div>
      </BasicHeader>
    );
  }
};

export default Header;

const BasicHeader = styled.header`
  background-color: var(--puple100);
  font-size: 1.4rem;
  font-weight: 500;
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
    0 2px 8px hsla(0, 0%, 0%, 0.05);

  span {
    cursor: pointer;
  }

  a {
    color: black;
    text-decoration: none;
  }
  .container {
    width: 65rem;
    margin: 0 auto;
    height: 3.2rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .logo_menu * {
    margin-right: 0.4rem;
  }

  .contents_menu {
    margin-left: 3rem;
  }

  .contents_menu * {
    margin-right: 4rem;
    font-size: 1.2rem;
  }

  .auth_menu * {
    margin-left: 1rem;
    font-size: 1rem;
  }

  .logo_img {
    width: 1.2rem;
    height: 1.2rem;
  }
`;
