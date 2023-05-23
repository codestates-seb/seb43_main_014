import React, { useState } from 'react';
import styled from 'styled-components';
import Logo from '../../images/rocket.png';
import { useRecoilState } from 'recoil';
import {
  isLoginState,
  refreshTokenState,
  tokenState,
  userState,
} from '../../recoil/AuthAtom';
import { Link, useNavigate } from 'react-router-dom';
import Confirm from '../common/Confirm/Confirm';
import axios from 'axios';
import { localStorageRemove } from '../../utils/localstorageFunc';

const Header = () => {
  const navigate = useNavigate();
  const [token, setToken] = useRecoilState(tokenState);
  const [refreshToken, setRefreshToken] = useRecoilState(refreshTokenState);
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [userInfo, setUserInfo] = useRecoilState(userState);
  const userData = JSON.parse(localStorage.getItem('user_info'));
  const jwt_token = localStorage.getItem('jwt_token');
  const refresh_token = localStorage.getItem('refresh_token');

  const [isOpenConfirm, setIsOpenConfirm] = useState(false);

  const handleLogout = () => {
    axios
      .post(
        'http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/user/logout',
        {
          accessToken: jwt_token,
          refreshToken: refresh_token,
        },
      )
      .then((res) => {
        console.log(res);
        localStorageRemove();

        setToken(null);
        setRefreshToken(null);
        setUserInfo(null);
        setIsLogin(false);
        setIsOpenConfirm(false);
        navigate('/login');
      });
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
                <p className="confirm_content">정말 로그아웃 하시겠습니까?</p>
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
    font-size: 1rem;
  }

  .auth_menu a {
    margin-right: 1rem;
  }

  .logo_img {
    width: 1.2rem;
    height: 1.2rem;
  }

  .confirm_content {
    font-size: larger;
  }
`;
