import React from 'react';
import styles from './Footer.module.css';

const Footer = () => {
  return (
    <>
      <div>
        <img src="" alt="로고 이미지" />
      </div>
      <div className={styles.h3}>
        <h3>로켓CV</h3>
        <text>저희는 어쩌구 저쩌구</text>
      </div>
      <div>
        <h3>Copy right</h3>
        <text>저쩌구 어쩌구</text>
      </div>
    </>
  );
};

export default Footer;
