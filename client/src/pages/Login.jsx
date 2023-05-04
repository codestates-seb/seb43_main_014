import React from 'react';
import styles from './Login.module.css';
import HelloBox from '../components/Login,SignUp/HelloBox/HelloBox';
import FormBox from '../components/Login,SignUp/FormBox/FormBox';

export default function Login() {
  return (
    <main className={styles.container}>
      <HelloBox />
      <FormBox />
    </main>
  );
}
