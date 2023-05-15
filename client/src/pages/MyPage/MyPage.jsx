import React, { useEffect, useState } from 'react';
import styles from './mypage.module.css';
import PwChange from '../../components/MyPage/PwChange/PwChange';
import Profile from '../../components/MyPage/Profile/Profile';
import DeleteAccount from '../../components/MyPage/DeleteAccount';
import CvList from '../../components/MyPage/CvList';
import axios from 'axios';

const MyPage = () => {
  const name = '도현';
  const email = `kdohyn98@github.com`;
  const phone = '01012341234';
  const [inputs, setInputs] = useState({
    name: name,
    email: email,
    phone: phone,
  });

  useEffect(() => {
    axios
      .get(
        `http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/user/2`,
      )
      .then((response) => console.log(response));
  }, []);
  return (
    <div className={styles.container}>
      <div className={styles.mypageItem}>
        <h2 className={styles.profileTitle}>기본정보</h2>
        <Profile inputs={inputs} setInputs={setInputs} />
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
        <DeleteAccount inputs={inputs} />
      </div>
    </div>
  );
};

export default MyPage;
