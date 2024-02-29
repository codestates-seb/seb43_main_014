import React from 'react';
import styles from './Oauth.module.css';
import kakao from '../../../images/kakao.png';
import google from '../../../images/google.png';
import github from '../../../images/github.png';
import { Link } from 'react-router-dom';

export default function Oauth() {
  return (
    <>
      <div className={styles.Oauth_title}>
        <p>다른 계정으로 로그인 하기</p>
      </div>
      <div className={styles.Oauth_img}>
        <Link to="http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/oauth2/authorization/google">
          <img src={google} alt="구글" />
          <div className={styles.Oauth_name}>Google</div>
        </Link>
        <Link to="http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/oauth2/authorization/kakao">
          <img src={kakao} alt="카카오" />
          <div className={styles.Oauth_name}>KaKao</div>
        </Link>
        <Link to="http://ec2-13-209-35-225.ap-northeast-2.compute.amazonaws.com:8080/oauth2/authorization/github">
          <img src={github} alt="깃허브" />
          <div className={styles.Oauth_name}>Github</div>
        </Link>
      </div>
    </>
  );
}
