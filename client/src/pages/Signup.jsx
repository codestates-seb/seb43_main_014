import React from 'react';
import styles from './Signup.module.css';
import HelloBox from '../components/Login,SignUp/HelloBox/HelloBox';
import FormBox from '../components/Login,SignUp/FormBox/FormBox';

export default function Signup() {
  return (
    <main className={styles.container}>
      <HelloBox />
      <FormBox />
    </main>
  );
}
