import React from 'react';
import HelloBox from '../components/Login,SignUp/HelloBox/HelloBox';
import Button from '../components/Login,SignUp/Button/Button';
import styles from './ResetPassword.module.css';
import LabelInput from '../components/Login,SignUp/LabelInput/LabelInput';
import FormBox from '../components/Login,SignUp/FormBox/FormBox';

export default function ResetPassword() {
  return (
    <main className={styles.container}>
      <HelloBox />
      <FormBox>
        <form className={styles.form}>
          <div className={styles.title}>비밀번호 재설정</div>
          <div className={styles.explanation}>
            <p>회원가입 할 때 입력한 휴대폰 번호를 입력해주세요.</p>
            <p>올바른 번호면 비밀번호를 알려드립니다.</p>
          </div>
          <LabelInput labelText="휴대폰 번호" name="phone" />
          <br />
          <br />
          <br />
          <Button text="비밀번호 재설정" />
        </form>
      </FormBox>
    </main>
  );
}