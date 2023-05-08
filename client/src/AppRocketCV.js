import React from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Root from './pages/Root';
import NotFound from './pages/NotFound';
import MyPage from './pages/MyPage/MyPage';
import './GlobalStyles.css';
import Login from './pages/Login/Login';
import Signup from './pages/Signup/Signup';
import Main from './pages/Main';
import ResetPassword from './pages/ResetPassword/ResetPassword';

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
        element: <div>이력서 작성 페이지 컴포넌트 올 자리</div>,
      },
    ],
  },
]);

function AppRocKetCV() {
  return <RouterProvider router={router}>AppRocketCV</RouterProvider>;
}

export default AppRocKetCV;
