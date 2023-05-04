import React from 'react';
import Profile from '../components/MyPage/Profile';
import styles from './mypage.module.css';

const MyPage = () => {
  return (
    <div className={styles.container}>
      <Profile />
    </div>
  );
};

export default MyPage;
