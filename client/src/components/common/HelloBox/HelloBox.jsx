import React from 'react';
import styles from './HelloBox.module.css';
import logo from '../../../images/rocket.png';

export default function Hello() {
  return (
    <section className={styles.left}>
      <div className={styles.hello}>반가워요!</div>
      <div className={styles.hello}>이력서 작성을 돕는 로켓CV입니다.</div>
      <img src={logo} alt="로켓CV로고" className={styles.logo_img} />
    </section>
  );
}
