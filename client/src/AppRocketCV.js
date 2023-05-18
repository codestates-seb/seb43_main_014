import React, { useEffect } from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Root from './pages/Root';
import NotFound from './pages/NotFound';
import MyPage from './pages/MyPage/MyPage';
import './GlobalStyles.css';
import Login from './pages/Login/Login';
import Signup from './pages/Signup/Signup';
import Main from './pages/Main';
import ResetPassword from './pages/ResetPassword/ResetPassword';
import CvPage from './components/Cv/CvPage';
import { isLoginState, userState } from './recoil/AuthAtom';
import { useRecoilState } from 'recoil';
import OAuthLogin from './pages/\bOAuthLogin/OAuthLogin';

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
        path: '/receive-token.html/',
        element: <OAuthLogin />,
      },
    ],
  },
]);

function AppRocKetCV() {
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [userInfo, setUserInfo] = useRecoilState(userState);
  console.log(isLogin); // 로그인시 : false -> true
  console.log('userInfo : ', userInfo);

  const token = localStorage.getItem('jwt_token');
  const userData = localStorage.getItem('user_info');

  useEffect(() => {
    // 앱이 로드될 때 로컬 스토리지에서 토큰을 확인하여 로그인 상태를 복원
    console.log('액세스토큰 :', token);
    console.log(userData);

    if (token && userData) {
      setUserInfo(JSON.parse(userData));
      setIsLogin(true);
    }
  }, [setUserInfo]);
  return <RouterProvider router={router}>AppRocketCV</RouterProvider>;
}

export default AppRocKetCV;
