import React from 'react';
import styles from './mypage.module.css';
import PwChange from '../../components/MyPage/PwChange/PwChange';
import Profile from '../../components/MyPage/Profile/Profile';
import DeleteAccount from '../../components/MyPage/DeleteAccount';

const MyPage = () => {
  return (
    <div className={styles.container}>
      <div>
        <h3 className={styles.profileTitle}>기본정보</h3>
        <Profile />
      </div>
      <div>
        <h3>비밀번호</h3>
        <PwChange />
      </div>
      <div>
        <h3>계정 삭제</h3>
        <DeleteAccount />
      </div>
    </div>
  );
};

export default MyPage;
