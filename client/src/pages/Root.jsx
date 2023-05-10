import React from 'react';
import { Outlet } from 'react-router-dom';
import Header from '../components/Cv/Header';
import Footer from '../components/Cv/Footer';

export default function Root() {
  return (
    <div>
      <Header />
      <Outlet />
      <Footer />
    </div>
  );
}
