import React from 'react';
import HelloBox from '../components/Login,SignUp/HelloBox/HelloBox';
import Button from '../components/Login,SignUp/Button/Button';
import styles from './ResetPassword.module.css';
import LabelInput from '../components/Login,SignUp/LabelInput/LabelInput';

export default function ResetPassword() {
  return (
    <main className={styles.container}>
      <HelloBox />
      <section className={styles.right}>
        <form className={styles.form}>
          <div className={styles.title}>비밀번호 재설정</div>
          <div className={styles.explanation}>
            <p>회원가입 할 때 입력한 휴대폰 번호를 입력해주세요.</p>
            <p>올바른 번호면 비밀번호를 알려드립니다.</p>
          </div>
          <LabelInput label="휴대폰 번호" />
          <br />
          <br />
          <br />
          <br />
          <Button text="비밀번호 재설정" className={styles.button} />
        </form>
      </section>
    </main>
  );
}
