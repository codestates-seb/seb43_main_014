import React from 'react';
import { Outlet } from 'react-router-dom';

export default function Root() {
  return (
    <div>
      <nav>Navbar 컴포넌트 들어올 자리</nav>
      <Outlet />
    </div>
  );
}
