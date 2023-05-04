import React from 'react';
import styles from './Signup.module.css';
import Hello from '../components/Login,SignUp/Hello';
import FormBox from '../components/Login,SignUp/FormBox';

export default function Signup() {
  return (
    <main className={styles.container}>
      <Hello />
      <FormBox />
    </main>
  );
}
