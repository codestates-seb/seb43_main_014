import React from 'react';
import Profile from '../components/MyPage/Profile';
import styles from './mypage.module.css';
import Info from '../components/MyPage/Info';

const MyPage = () => {
  return (
    <div className={styles.container}>
      <Profile />
      <Info />
    </div>
  );
};

export default MyPage;
