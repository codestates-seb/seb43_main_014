import React from 'react';
import { Link } from 'react-router-dom';
import styles from './FormBox.module.css';
import LabelInput from '../LabelInput/LabelInput';
import Button from '../Button/Button';
import Oauth from '../Oauth/Oauth';

export default function FormBox() {
  const currentUrl = window.location.href;

  return (
    <section className={styles.right}>
      <form className={styles.form}>
        <div className={styles.tapMenu}>
          <Link to="/login" className={styles.login}>
            <h1>로그인</h1>
          </Link>
          <Link to="/signup" className={styles.signup}>
            <h1>회원가입</h1>
          </Link>
        </div>
        {currentUrl === 'http://localhost:3000/signup' && (
          <LabelInput label="이름" />
        )}
        {currentUrl === 'http://localhost:3000/signup' && (
          <LabelInput label="휴대폰 번호" />
        )}
        <LabelInput label="이메일" duplicate={true} />
        <LabelInput label="비밀번호" />
        {currentUrl === 'http://localhost:3000/signup' && (
          <LabelInput label="비밀번호 확인" />
        )}
        {currentUrl === 'http://localhost:3000/login' && (
          <Button text="로그인" />
        )}
        {currentUrl === 'http://localhost:3000/signup' && (
          <Button text="회원가입" />
        )}
      </form>
      <Oauth />
    </section>
  );
}
