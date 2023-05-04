import React from 'react';
import styles from './Login.module.css';
import { Link } from 'react-router-dom';
import logo from '../images/rocket.png';
import kakao from '../images/kakao.png';
import google from '../images/google.png';
import github from '../images/github.png';

export default function Login() {
  return (
    <main>
      <div className={styles.container}>
        <div className={styles.left}>
          <div className={styles.hello}>
            반가워요, 이력서 작성을 돕는 로켓CV입니다.
          </div>
          <img src={logo} alt="로켓CV로고" className={styles.logo_img} />
        </div>

        <div className={styles.right}>
          <form className={styles.form}>
            <h1>로그인</h1>
            <div className={styles.box}>
              <label htmlFor="" className={styles.label}>
                이메일
              </label>
              <input
                type="text"
                className={styles.input}
                placeholder="이메일을 입력해주세요. 😁"
              />
            </div>
            <div className={styles.box}>
              <label htmlFor="" className={styles.label}>
                패스워드
              </label>
              <input
                type="password"
                className={styles.input}
                placeholder="패스워드를 입력해주세요. 😁"
              />
            </div>
            <button className={styles.button}>로그인</button>
          </form>
          <div className={styles.Oauth_title}>
            <h3>다른 계정으로 로그인 하기</h3>
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
        </div>
      </div>
    </main>
  );
}
