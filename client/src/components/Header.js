import React from 'react';
// import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

const Header = () => {
  //   const navigate = useNavigate();
  //   const isLogin = storage.get('login');

  //   if (isLogin) {
  return (
    <>
      <BasicHeader>
        <Logo
          src="/images/rocket.png"
          alt="logo"
          //   onClick={() => navigate('/')}
        />
        <StyledButton>이력서 작성</StyledButton>
        <StyledButton>프로젝트 매칭</StyledButton>

        <AccountCircleIcon
        //   onClick={() => {
        //     navigate('/mypage');
        //   }}
        />
        <StyledButton text="Logout">Logout</StyledButton>
      </BasicHeader>
    </>
  );
  //   } else {
  //     return (
  //       <>
  //         <BasicHeader>
  //           <Logo src="" alt="logo" onClick={() => navigate('/')} />
  //           <Link to="/cv">이력서 작성</Link>
  //           <Link to="/project/matching">프로젝트 매칭</Link>
  //           <Link to="/users/login">로그인</Link>
  //           <LoginBtn
  //             onClick={() => {
  //               navigate('/users/login');
  //             }}
  //           >
  //             Log in
  //           </LoginBtn>
  //           <Button
  //             text="Sign up"
  //             onClick={() => {
  //               navigate('/users/signup');
  //             }}
  //           />
  //         </BasicHeader>
  //       </>
  // );
  //   }
};

export default Header;

const BasicHeader = styled.header`
  z-index: 1;
  position: fixed;
  top: 0;
  width: 100%;
  max-width: 100%;
  height: 4rem;
  background-color: var(--puple100);
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.5rem;
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
    0 2px 8px hsla(0, 0%, 0%, 0.05);
`;

const StyledButton = styled.button`
  min-width: 7rem;
  margin-left: 0.5rem;
  font-size: 0, 8rem;
  cursor: pointer;
  padding: 1rem;
  border: none;
  border-radius: 0.3rem var(--puple100);
  background-color: var(--puple100);
  color: black;

  &:hover {
    background-color: var(--puple100);
  }
`;

const Logo = styled.img`
  width: 5rem;
  cursor: pointer;
  margin-right: 5rem;
`;
