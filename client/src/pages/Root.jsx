import React from 'react';
import { Outlet } from 'react-router-dom';
import Header from '../components/Cv/Header';
import Footer from '../components/Cv/Footer';
import TokenRefreshTimer from '../components/common/TokenRefreshTimer';

export default function Root() {
  return (
    <>
      <Header />
      <TokenRefreshTimer />
      <Outlet />
      <Footer />
    </>
  );
}
