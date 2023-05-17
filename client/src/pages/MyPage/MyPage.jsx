import React, { useEffect, useState } from 'react';
import styles from './mypage.module.css';
import PwChange from '../../components/MyPage/PwChange/PwChange';
import Profile from '../../components/MyPage/Profile/Profile';
import DeleteAccount from '../../components/MyPage/DeleteAccount';
import CvList from '../../components/MyPage/CvList';
import axios from 'axios';

const MyPage = () => {
  const token = localStorage.getItem('jwt_token');
  const user = localStorage.getItem('user_info');
  const { userId } = JSON.parse(user);
  const [userData, setUserData] = useState('');
  console.log('userId', userId);

  useEffect(() => {
    axios
      .get(
        `http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/user/mypage/${userId}?page=1`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      )
      .then((response) => setUserData(response.data))
      .catch((error) => {
        console.log(error);
      });
  }, [token, userId]);
  console.log(userData);
  return (
    <div className={styles.container}>
      <div className={styles.mypageItem}>
        <h2 className={styles.profileTitle}>기본정보</h2>
        <Profile userData={userData} setUserData={setUserData} />
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
