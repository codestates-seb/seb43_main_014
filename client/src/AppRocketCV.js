import React from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Root from './pages/Root';
import NotFound from './pages/NotFound';
import MyPage from './pages/MyPage';
import './GlobalStyles.css';
const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    errorElement: <NotFound />,
    children: [
      { index: true, element: <div>Home 홈 페이지 컴포넌트 들어올 자리</div> },
      { path: '/login', element: <div>Login 컴포넌트 들어올자리</div> },
      { path: '/signup', element: <div>회원가입 페이지</div> },
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
