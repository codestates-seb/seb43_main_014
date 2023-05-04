import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Header = () => {
  const navigate = useNavigate();
  const isLogin = storage.get('login');

  if (isLogin) {
    return (
      <>
        <BasicHeader>
          <Logo src="" alt="logo" onClick={() => navigate('/')} />
          <Link to="/cv">이력서 작성</Link>
          <Link to="/project/matching">프로젝트 매칭</Link>

          <AccountCircleIcon
            onClick={() => {
              navigate('/mypage');
            }}
          />
          <Button
            text="Logout"
            onClick={() => {
              navigate('/users/logout');
            }}
          />
        </BasicHeader>
      </>
    );
  } else {
    return (
      <>
        <BasicHeader>
          <Logo src="" alt="logo" onClick={() => navigate('/')} />
          <Link to="/cv">이력서 작성</Link>
          <Link to="/project/matching">프로젝트 매칭</Link>
          <Link to="/users/login">로그인</Link>
          <LoginBtn
            onClick={() => {
              navigate('/users/login');
            }}
          >
            Log in
          </LoginBtn>
          <Button
            text="Sign up"
            onClick={() => {
              navigate('/users/signup');
            }}
          />
        </BasicHeader>
      </>
    );
  }
};

export default Header;
