import React from 'react';
import styles from './mypage.module.css';
import PwChange from '../../components/MyPage/PwChange/PwChange';
import Profile from '../../components/MyPage/Profile/Profile';
import DeleteAccount from '../../components/MyPage/DeleteAccount';
import CvList from '../../components/MyPage/CvList';

const MyPage = () => {
  return (
    <div className={styles.container}>
      <div className={styles.mypageItem}>
        <h2 className={styles.profileTitle}>기본정보</h2>
        <Profile />
      </div>
      <div className={styles.mypageCv}>
        <h2>이력서</h2>
        <CvList />
        <div className={styles.cvList}>
          <button>새이력서 추가</button>
        </div>
      </div>
      <div className={styles.mypageItem}>
        <h2>비밀번호</h2>
        <PwChange />
      </div>
      <div className={styles.mypageItem}>
        <h2>계정 삭제</h2>
        <DeleteAccount />
      </div>
    </div>
  );
};

export default MyPage;
