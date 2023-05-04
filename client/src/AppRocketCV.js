import React from 'react';
import Main from './pages/Main';
import './GlobalStyles.css';
import Root from './pages/Root';
import NotFound from './pages/NotFound';
import Login from './pages/Login';
import Signup from './pages/Signup';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    errorElement: <NotFound />,
    children: [
      { index: true, element: <div>Home 홈 페이지 컴포넌트 들어올 자리</div> },
      { path: '/login', element: <Login /> },
      { path: '/signup', element: <Signup /> },
      {
        path: '/mypage/:userId',
        element: <div>마이페이지 컴포넌트 들어올 자리</div>,
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

export default function AppRocKetCV() {
  return <RouterProvider router={router}>AppRocketCV</RouterProvider>;
}
