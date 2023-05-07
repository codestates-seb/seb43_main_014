import React, { useState } from 'react';
import styles from './Login.module.css';
import HelloBox from '../../components/Login,SignUp,ResetPassword/HelloBox/HelloBox';
import FormBox from '../../components/Login,SignUp,ResetPassword/FormBox/FormBox';
import Button from '../../components/Login,SignUp,ResetPassword/Button/Button';
import LabelInput from '../../components/Login,SignUp,ResetPassword/LabelInput/LabelInput';
import { Link } from 'react-router-dom';
import Oauth from '../../components/Login,SignUp,ResetPassword/Oauth/Oauth';

export default function Login() {
  const [form, setForm] = useState({
    email: '',
    password: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    // console.log(name, e.target.value);
    console.log(form);
    setForm({ ...form, [name]: value });
  };

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
          <LabelInput
            labelText="이메일"
            type="email"
            name="email"
            placeholder="이메일을 입력해주세요."
            value={form.email}
            handleChange={handleChange}
          />
          <LabelInput
            labelText="비밀번호"
            type="password"
            name="password"
            placeholder="비밀번호를 입력해주세요."
            resetButton={true}
            value={form.password}
            handleChange={handleChange}
          />
          <Button text="로그인" />
        </form>
        <Oauth />
      </FormBox>
    </main>
  );
}
