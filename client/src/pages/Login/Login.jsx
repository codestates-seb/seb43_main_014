import React, { useState } from 'react';
import styles from './Login.module.css';
import HelloBox from '../../components/Login,SignUp,ResetPassword/HelloBox/HelloBox';
import FormBox from '../../components/Login,SignUp,ResetPassword/FormBox/FormBox';
import Button from '../../components/Login,SignUp,ResetPassword/Button/Button';
import LabelInput from '../../components/Login,SignUp,ResetPassword/LabelInput/LabelInput';
import { Link } from 'react-router-dom';
import Oauth from '../../components/Login,SignUp,ResetPassword/Oauth/Oauth';
import { useRecoilState } from 'recoil';
import { IsLoginAtom } from '../../recoil/IsLoginAtom';

export default function Login() {
  const [form, setForm] = useState({
    email: '',
    password: '',
  });

  const [errors, setErrors] = useState({
    name: '',
    phone: '',
    email: '',
    password: '',
    password_confirm: '',
  });

  const [valid, setValid] = useState({
    email: false,
    password: false,
  });

  const [isLogin, setIsLogin] = useRecoilState(IsLoginAtom);

  const validate = (form) => {
    const newErrors = {
      email: '',
      password: '',
    };

    if (form.email.trim() === '') {
      newErrors.email = '이메일을 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, email: false }));
    } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(form.email)) {
      newErrors.email = '올바른 이메일 형식이 아닙니다.';
      setValid((prevValid) => ({ ...prevValid, email: false }));
    } else {
      newErrors.email = '올바른 형식입니다.';
      setValid((prevValid) => ({ ...prevValid, email: true }));
    }

    if (form.password.trim() === '') {
      newErrors.password = '비밀번호를 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, password: false }));
    } else if (form.password.length < 8) {
      newErrors.password = '비밀번호는 8자리 이상이어야 합니다.';
      setValid((prevValid) => ({ ...prevValid, password: false }));
    } else if (
      !/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$/.test(form.password)
    ) {
      newErrors.password =
        '영문자, 숫자, 특수문자를 포함한 8글자 이상의 비밀번호를 입력해주세요.';
      setValid((prevValid) => ({ ...prevValid, password: false }));
    } else {
      newErrors.password = '올바른 형식입니다.';
      setValid((prevValid) => ({ ...prevValid, password: true }));
    }
    setErrors(newErrors);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const allTrue = Object.values(valid).every((value) => value === true);
    if (allTrue) {
      // 유효성 검사에 성공하면 폼 데이터를 서버로 보냅니다.
      console.log('Form data:', form);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    console.log(form);
    setForm((prevForm) => ({ ...prevForm, [name]: value }));
    validate({ ...form, [name]: value });
  };

  return (
    <main className={styles.container}>
      <HelloBox />
      <FormBox>
        <form className={styles.form} onSubmit={handleSubmit}>
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
          <div className={valid.email ? styles.successMsg : styles.errorsMsg}>
            {errors.email}
          </div>
          <LabelInput
            labelText="비밀번호"
            type="password"
            name="password"
            placeholder="비밀번호를 입력해주세요."
            resetButton={true}
            value={form.password}
            handleChange={handleChange}
          />
          <div
            className={valid.password ? styles.successMsg : styles.errorsMsg}
          >
            {errors.password}
          </div>
          <Button text="로그인" />
        </form>
        <Oauth />
      </FormBox>
    </main>
  );
}
