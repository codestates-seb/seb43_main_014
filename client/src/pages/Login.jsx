import React from 'react';
import styles from './Login.module.css';
import HelloBox from '../components/Login,SignUp/HelloBox/HelloBox';
import FormBox from '../components/Login,SignUp/FormBox/FormBox';
import Button from '../components/Login,SignUp/Button/Button';
import LabelInput from '../components/Login,SignUp/LabelInput/LabelInput';
import { Link } from 'react-router-dom';
import Oauth from '../components/Login,SignUp/Oauth/Oauth';

export default function Login() {
  return (
    <main className={styles.container}>
      <HelloBox />
      <FormBox>
        <form className={styles.form}>
          <div className={styles.tapMenu}>
            <Link to="/login" className={styles.login}>
              <h1>로그인</h1>
            </Link>
            <Link to="/signup" className={styles.signup}>
              <h1>회원가입</h1>
            </Link>
          </div>
          <LabelInput labelText="이메일" type="email" name="email" />
          <LabelInput labelText="비밀번호" login={true} name="password" />
          <Button text="로그인" />
        </form>
        <Oauth />
      </FormBox>
    </main>
  );
}
