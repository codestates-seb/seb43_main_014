import React, { useEffect } from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Root from './pages/Root';
import NotFound from './pages/NotFound/NotFound';
import MyPage from './pages/MyPage/MyPage';
import './GlobalStyles.css';
import Login from './pages/Login/Login';
import Signup from './pages/Signup/Signup';
import Main from './pages/Main';
import ResetPassword from './pages/ResetPassword/ResetPassword';
import CvPage from './components/Cv/CvPage';
import { isLoginState, tokenState, userState } from './recoil/AuthAtom';
import { useRecoilState } from 'recoil';
import OAuthLogin from './pages/OAuthLogin/OAuthLogin';
import { extractAccessToken } from './utils/extractAccessToken';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    errorElement: <NotFound />,
    children: [
      { index: true, element: <Main /> },
      { path: '/login', element: <Login /> },
      { path: '/signup', element: <Signup /> },
      {
        path: '/reset_password',
        element: <ResetPassword />,
      },
      {
        path: '/mypage',
        element: <MyPage />,
      },
      {
        path: '/profile/:userId',
        element: <div>유저 오픈 프로필 컴포넌트 들어올 자리</div>,
      },
      {
        path: '/project-match',
        element: <div>프로젝트 매칭 페이지 컴포넌트 들어올 자리</div>,
      },
      {
        path: '/create-project-match/',
        element: <div>프로젝트 매칭 생성 페이지 컴포넌트 올 자리</div>,
      },
      {
        path: '/create-cv',
        element: <CvPage />,
      },
      {
        path: '/login/oauth2/',
        element: <OAuthLogin />,
      },
      {
        path: '/login/oauth2/already/',
        element: <Main />,
      },
    ],
  },
]);

function AppRocKetCV() {
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [userInfo, setUserInfo] = useRecoilState(userState);
  const [accessToken, setAccessToken] = useRecoilState(tokenState);

  const token = localStorage.getItem('jwt_token');
  const userData = localStorage.getItem('user_info');

  useEffect(() => {
    // 앱이 새로고침돼도 로컬 스토리지에서 토큰을 확인하여 리코일 상태를 복원
    if (token && userData) {
      setAccessToken(token);
      setUserInfo(JSON.parse(userData));
      setIsLogin(true);
    }
  }, [setUserInfo]);

  useEffect(() => {
    console.log('OAuth 재로그인');
    const currentUrl = window.location.href;
    const AccessToken = extractAccessToken(currentUrl);
    console.log(AccessToken);
    // if(oauth 재로그인 url) {

    // }
  }, []);
  return <RouterProvider router={router}>AppRocketCV</RouterProvider>;
}

export default AppRocKetCV;
