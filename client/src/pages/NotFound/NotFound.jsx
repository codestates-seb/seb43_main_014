import React from 'react';
import logo from '../../images/rocket.png';
import styles from './NotFound.module.css';
import { Link } from 'react-router-dom';
import Btn from '../../components/MyPage/Btn/Btn';

export default function NotFound() {
  return (
    <div className={styles.container}>
      <h1>로켓CV</h1>
      <img src={logo} alt="로켓CV로고" />
      <h1>아직 준비중인 페이지에요! 😅</h1>
      <Link to="/">
        <Btn btnName="홈 돌아가기" />
      </Link>
    </div>
  );
}
