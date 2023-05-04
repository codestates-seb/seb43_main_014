import React from 'react';
import styles from './Oauth.module.css';
import kakao from '../../images/kakao.png';
import google from '../../images/google.png';
import github from '../../images/github.png';

export default function Oauth() {
  return (
    <>
      <div className={styles.Oauth_title}>
        <p>다른 계정으로 로그인 하기</p>
      </div>
      <div className={styles.Oauth_img}>
        <div>
          <img src={google} alt="구글" />
          <div className={styles.Oauth_name}>Google</div>
        </div>
        <div>
          <img src={kakao} alt="카카오" />
          <div className={styles.Oauth_name}>KaKao</div>
        </div>
        <div>
          <img src={github} alt="깃허브" />
          <div className={styles.Oauth_name}>Github</div>
        </div>
      </div>
    </>
  );
}
