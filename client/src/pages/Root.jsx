import React from 'react';
import { Outlet } from 'react-router-dom';

export default function Root() {
  return (
    <div>
      <nav>Navbar 컴포넌트 들어올 자리</nav>
      <Outlet />
      <footer>
        Footer 컴포넌트 들어올 자리
        <a href="https://www.flaticon.com/kr/free-icons/" title="로켓 아이콘">
          로켓 아이콘 제작자: Freepik - Flaticon
        </a>
        {/* 푸터에 위에 a링크 추가해주세요! 아이콘 저작권 입니다!!! 꼭!! */}
      </footer>
    </div>
  );
}
