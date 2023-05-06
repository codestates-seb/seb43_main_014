import React, { useState } from 'react';
import styles from './Signup.module.css';
import HelloBox from '../components/Login,SignUp,ResetPassword/HelloBox/HelloBox';
import FormBox from '../components/Login,SignUp,ResetPassword/FormBox/FormBox';
import LabelInput from '../components/Login,SignUp,ResetPassword/LabelInput/LabelInput';
import Button from '../components/Login,SignUp,ResetPassword/Button/Button';
import Oauth from '../components/Login,SignUp,ResetPassword/Oauth/Oauth';
import { Link } from 'react-router-dom';

export default function Signup() {
  const [form, setForm] = useState({
    name: '',
    phone: '',
    email: '',
    password: '',
    password_confirm: '',
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
            labelText="이름"
            type="text"
            name="name"
            placeholder="이름을 입력해주세요."
            value={form.name}
            handleChange={handleChange}
          />
          <LabelInput
            labelText="휴대폰 번호"
            type="text"
            name="phone"
            placeholder="휴대폰 번호를 입력해주세요."
            value={form.phone}
            handleChange={handleChange}
          />
          <LabelInput
            labelText="이메일"
            type="email"
            name="email"
            placeholder="이메일을 입력해주세요."
            duplicate={true}
            value={form.email}
            handleChange={handleChange}
          />
          <LabelInput
            labelText="비밀번호"
            type="password"
            name="password"
            placeholder="영문자, 숫자, 특수문자 포함 8글자 이상 입력해주세요."
            value={form.password}
            handleChange={handleChange}
          />
          <LabelInput
            isNoLabel={true}
            type="password"
            name="password_confirm"
            placeholder="위에 입력하신 비밀번호를 똑같이 입력해주세요."
            value={form.password_confirm}
            handleChange={handleChange}
          />
          <Button text="회원가입" />
        </form>
        <Oauth />
      </FormBox>
    </main>
  );
}
