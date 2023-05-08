import React from 'react';
// import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import Logo from '../images/rocket.png';

const Header = () => {
  //   const navigate = useNavigate();
  //   const isLogin = storage.get('login');

  //   if (isLogin) {
  return (
    <>
      <BasicHeader>
        <img className="Logo" src={Logo} alt="로고 이미지" />

        {/* onClick={() => navigate('/')} */}

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
  position: sticky;
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
  border: 1px solid tomato;
  .Logo {
    width: 2rem;
    height: 2rem;
    margin-right: 28rem;
    cursor: pointer;
  }
`;

const StyledButton = styled.button`
  min-width: 7rem;
  margin: 0rem 0rem 0rem 3rem;
  font-size: 1rem;
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
