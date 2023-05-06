import React from 'react';
import styles from './Signup.module.css';
import HelloBox from '../components/Login,SignUp/HelloBox/HelloBox';
import FormBox from '../components/Login,SignUp/FormBox/FormBox';
import LabelInput from '../components/Login,SignUp/LabelInput/LabelInput';
import Button from '../components/Login,SignUp/Button/Button';
import Oauth from '../components/Login,SignUp/Oauth/Oauth';
import { Link } from 'react-router-dom';

export default function Signup() {
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
          <LabelInput labelText="이름" name="name" />
          <LabelInput labelText="휴대폰 번호" name="phone" />
          <LabelInput
            labelText="이메일"
            duplicate={true}
            type="email"
            name="email"
          />
          <LabelInput labelText="비밀번호" signup={true} name="password" />
          <LabelInput isNoLabel={true} name="password-confirm" />
          <Button text="회원가입" />
        </form>
        <Oauth />
      </FormBox>
    </main>
  );
}
